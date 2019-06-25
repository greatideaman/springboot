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
            paging: true,
            sorting: true,
            ordering: true,
            select: true,
            searching: false,
            info: false
        });
    };

    window.app = new App;
})($);
