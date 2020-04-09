(function() {
    function App() {

        this.init = function() {
            $('#configTable').DataTable({
                serverSide: true,
                ajax: {
                    url: '/configuration/022018',
                    type: 'GET',
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
        };

    }

    window.app = new App;
})($);