(function() {
    function App() {

        this.selectedResourcePath = function () {
            return '/configuration/' + this.selectedTimePeriod();
        }

        this.selectedTimePeriod = function () {
            return $('#timePeriod').val();
        }

        this.init = function() {

            $('#configTable').DataTable({
                serverSide: true,
                ajax: {
                    url: this.selectedResourcePath(),
                    type: 'GET',
                    // Necessary because root is not an object with a "data" key
                    dataSrc: ''
                },
                columns: [
                    { data: 'configId'},
                    { data: 'configName'}
                ],
                scrollY: 300,
                paging: false,
                sorting: false,
                searching: false,
                info: false
            });

            $('#addButton').click(function() {
                let name = prompt("Enter a name for the new configuration:");
                $.ajax({
                    type: "POST",
                    url: app.selectedResourcePath(),
                    data: JSON.stringify({
                        configId: -1,
                        configName: name
                    }),
                    contentType: "application/json; charset=utf-8",
                    dataType: "json",
                    success:function (successResponse,textStatus,jqXHR) {
                        $('#configTable').DataTable().ajax.reload();
                    },
                    error: function (errorResponse1) {
                        $('#configTable').DataTable().ajax.reload();
                    }
                });
            });

            $('#deleteButton').click(function() {
                $.ajax({
                    url: app.selectedResourcePath(),
                    type: 'DELETE',
                    success: function() {
                        $('#configTable').DataTable().ajax.reload();
                    }
                });
            });

            $('#refreshButton').click(function() {
                $('#configTable').DataTable().ajax.reload();
            });

            $('#timePeriod').change(function() {
                $('#configTable').DataTable().ajax.url(app.selectedResourcePath()).load();
            });
        }

    }

    window.app = new App;
})($);