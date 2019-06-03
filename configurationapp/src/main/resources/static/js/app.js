(function () {
    function App () {

    }

    App.prototype.getData = function () {
        $.ajax('', {}).then(function (data) {

        })
    };

    App.prototype.init = function () {
        var table = $('#configTable').DataTable({
            scrollY: 300,
            paging: false,
            sorting: false,
            searching: false,
            info: false,
            columns: [
                {'data': 'configId'},
                {'data': 'configName'},
                {
                    'data': null,
                    'defaultContent': '<button>Delete</button>',
                    'targets': -1
                }
            ]
        });

        $('#configTable tbody').on('click', 'button', function () {
            var data = table.row($(this).parents('tr')).data();
            deleteSelectedConfigForMonthYear(data.configId);
        });
    };

    window.app = new App;
})($);

function getConfigMonthYear () {
    var selectedTimePeriod = $('#timePeriod option:selected').val();
    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: 'configuration/' + selectedTimePeriod,
        dataType: 'json',
        cache: false,
        success: function (data) {
            updateConfigTable(data);
        },
        error: function (data) {
            console.log('Error fetching configurations for selected month and year. ' + JSON.stringify(data));
        }
    });
}

function updateConfigTable (data) {
    $ConfigTable = $('#configTable').DataTable();
    $ConfigTable.clear();
    $ConfigTable.rows.add(data);
    $ConfigTable.draw();
}

function addNewConfigurationForYearMonth () {
    var selectedTimePeriod = $('#timePeriod option:selected').val();
    var configName = $('#newConfigName').val();

    if (configName != null && configName.trim().length > 0) {

        var configValue = {};
        configValue['configName'] = configName;

        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: 'configuration/' + selectedTimePeriod,
            data: JSON.stringify(configValue),
            cache: false,
            success: function (data) {
                getConfigMonthYear();
            },
            error: function (data) {
                alert('Error adding configuration to selected year and method');
                console.log('Error adding configuration to selected year and method. ' + JSON.stringify(data));
            }
        });
    } else {
        alert('Please enter configuration name');
    }
}

function deleteAllConfigForMonthYear () {
    var selectedTimePeriod = $('#timePeriod option:selected').val();

    $.ajax({
        type: 'DELETE',
        contentType: 'application/json',
        url: 'configuration/' + selectedTimePeriod,
        cache: false,
        success: function (data) {
            getConfigMonthYear();
        },
        error: function (e) {
            alert("Error deleting configuration for selected year and method");
            console.log('Error deleting configurations for selected year and month.' + JSON.stringify(e));
        }
    });
}

function deleteSelectedConfigForMonthYear (configId) {
    var selectedTimePeriod = $('#timePeriod option:selected').val();

    $.ajax({
        type: 'DELETE',
        url: 'configuration/' + selectedTimePeriod + '/' + configId,
        cache: false,
        success: function (data) {
            getConfigMonthYear();
        },
        error: function (e) {
            alert("Error deleting selected configuration");
            console.log('Error deleting selected configurations for selected year and month.' + JSON.stringify(e));
        }
    });
}