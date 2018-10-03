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
            "columnDefs": [{
                "targets": 2,
                "data": null,
                "defaultContent": "<button>Delete</button>"
                	}],
            scrollY: 300,
            paging: false,
            sorting: false,
            searching: false,
            info: false
          });     
               
    };

   window.app = new App;
})($);