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
            sorting: true,
            ordering: true,
            order: [],
            searching: false,
            info: false,
            columns: [
            	 { "data": "configId" },
                 { "data": "configName" },
            	],           
        });
    };

    window.app = new App;
})($);