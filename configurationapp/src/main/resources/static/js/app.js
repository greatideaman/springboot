(function() {
    function App() {

    }

    App.prototype.getData = function() {
        var yearMo = $('#timePeriod').val();
        $.ajax({
            url: "/configuration/"+yearMo,
            success: function(result){
                $('#tbody').empty();
                $.each(result, function(key, val) {
                    $('#tbody').append(htmlRow(yearMo, val.configId, val.configName))
                })
            }
        }).then(function(data) {

        })
    };

    App.prototype.saveConfiguration = function() {
        var yearMonth = $('#timePeriod').val();
        var configName = $('#configName').val();
        $.ajax({
           url: '/configuration/'+ yearMonth,
                type: 'POST',
           data: {configName: configName},
           success: function(data) {
               app.getData();
               $('#configName').val('');
                    alert('Configuration added');
                },
                error: function(data) {
                    alert('Something went wrong');
            }
        });
    };
    
    App.prototype.deleteRow = function(yearMonth, id) {
        $.ajax({
                url: '/configuration/'+ yearMonth + '/' + id,
                type: 'DELETE',
                success: function(data) {
                    app.getData();
                    alert('Configuration removed');
                },
                error: function(data) {
                    alert('Something went wrong');
                }
        });
    };
    
    App.prototype.deleteAll = function() {
        var yearMonth = $('#timePeriod').val();
        $.ajax({
            url: '/configuration/'+ yearMonth,
                type: 'DELETE',
            success: function(data) {
                app.getData();
                alert('All Configuration Deleted');
            },
            error: function(data) {
                alert('Something went wrong');
            }
        });
    };

    htmlRow = function(yearmo, configId, configName) {
        return '<tr><td class="tdstyle">' + yearmo + '</td>' + '<td class="tdstyle">' + configId + '</td><td class="tdstyle">' 
               + configName + '</td><td class="tdstyle" ><a alt="Delete row"" href="javascript:app.deleteRow(\''+ yearmo + '\', ' + configId + ');">Delete</a>' +'</td></tr>';
    }
    
    App.prototype.init = function() {
        $('#configTable').DataTable({
            scrollY: 400,
            paging: false,
            sorting: false,
            searching: false,
            info: false
        });
        app.getData();
        $('#timePeriod').change(function() {
            app.getData();
        });
        $('#saveButton').click(function() {
            app.saveConfiguration();
        }); 
        $('#deleteButton').click(function() {
            app.deleteAll();
        }); 
    };

    window.app = new App;
})($);