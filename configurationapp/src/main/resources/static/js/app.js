(function () {
    function App() {

    }

    App.prototype.url = "http://localhost:9000/configuration"
    App.prototype.timePeriod = '#timePeriod';
    App.prototype.configTable = '#configTable';

    App.prototype.getTimePeriod = function () {
        return $(`${this.timePeriod} option:selected`).attr('value')
    }

    App.prototype.getData = function () {
        var timePeriod = this.getTimePeriod();
        $(this.configTable).DataTable().ajax.url(`${this.url}/${timePeriod}`).load()
    };

    App.prototype.onConfigSave = function () {
        var timePeriod = this.getTimePeriod();
        var configName = $('#configName').val();
        $.ajax({
            url: `${this.url}/${timePeriod}`,
            type: 'POST',
            contentType:"application/json",
            dataType:"json",
            data: JSON.stringify({configName: configName}),
            complete: function(){
                App.prototype.getData();
                $('#configName').val("");
                $('#configModal').modal('toggle');

            }
        });
    }

    App.prototype.onDeleteAll = function () {
        var timePeriod = this.getTimePeriod();
        $.ajax({url: `${this.url}/${timePeriod}`, type: 'DELETE'})
            .then(function (data) {
                App.prototype.getData();
            })
    }

    App.prototype.onDelete = function (id) {
        var timePeriod = this.getTimePeriod();
        $.ajax({url: `${this.url}/${timePeriod}/${id}`, type: 'DELETE'})
            .then(function (data) {
                App.prototype.getData();
            })
    }

    App.prototype.init = function () {
        $(this.timePeriod).change((e) => {
            this.getData();
        })

        $('#deleteAllButton').click((e) => {
            this.onDeleteAll();
        })

        $('#addConfigValue').click((e) => {
            this.onConfigSave();
        })

        var timePeriod = this.getTimePeriod();
        $(this.configTable).DataTable({
            ajax: `${this.url}/${timePeriod}`,
            scrollY: 300,
            paging: false,
            sorting: false,
            searching: false,
            info: false,
            columns: [
                {data: 'configId',
                render: function (data, type, row){
                    return '<button class="btn btn-sm btn-danger" onclick="app.onDelete('+data+')"><i class="bi bi-trash"></i></button>';
                }},
                {data: 'configId'},
                {data: 'configName'},
            ]
        });
    };

    window.app = new App;
})($);