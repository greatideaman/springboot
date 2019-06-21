(function() {
    function App() {

    }

    App.prototype.getData = function() {
        $.ajax('',{

        }).then(function(data) {

        })
    };

    App.prototype.init = function() {
        $('#configTable').DataTable({
            scrollY: 300,
            paging: false,
            sorting: false,
            searching: false,
            info: false,
            columns: [
                { "data": "configId" },
                { "data": "configName" },
            ]
        });
    };

    window.app = new App;
})($);