(function() {
    function App() {

    }

    App.prototype.getData = function() {
        $.ajax('http://localhost:9000/api/configuration/findBy/012018',{

        }).then(function(data) {

        })
    };

    App.prototype.init = function() {
        $('#configTable').DataTable({
            scrollY: 300,
            paging: false,
            sorting: false,
            searching: false,
            info: false
        });
    };

    window.app = new App;
})($);