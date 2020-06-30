(function() {
    function App() {
    	var datatable;
    }

    App.prototype.getData = function() {
        $.ajax('/configurations/' + $('#timePeriod').val()).then(function(data) {
        	datatable.clear().rows.add(data).draw();
        },
        function(error) {
        	alert(error);
        });
    };
    
    App.prototype.addConfig = function() {

        $.ajax({
        	type: "POST", 
        	url : '/configurations/' + $('#timePeriod').val(), 
        	data: JSON.stringify({ configId: -1, configName: 'A'}),
        	contentType: 'application/json'
        		}).
        then(function(data) {
    		app.getData();
        },
        function(error) {
        	alert(error);
        });
    };
    
    App.prototype.deleteConfig = function() {
    	
    	if (datatable.rows('.selected').data().length == 0) {
    		alert('Please select atleast 1 row for deletion');
    		return;
    	}
    	
        var configId = $.map(datatable.rows('.selected').data(), function (item) {
            return item.configId;
        });
        
        $.ajax({
        	type: "DELETE", 
        	url : '/configurations/' + $('#timePeriod').val() + '/' + configId
        	})
        	.then(function(data) {
    		app.getData();
        },
        function(error) {
        	alert(error);
        });
    	
    };
    
    App.prototype.deleteAllConfigs = function() {
        $.ajax({
        	type: "DELETE", 
        	url : '/configurations/' + $('#timePeriod').val()
        	})
        	.then(function(data) {
    		app.getData();
        },
        function(error) {
        	alert(error);
        })
    };

    App.prototype.init = function() {

    	/* Init Datatable */
    	
    	datatable = $('#configTable').DataTable({
            scrollY: 300,
            paging: false,
            sorting: false,
            searching: false,
            info: false,
            columns: [
                { data: "configId" },
                { data: "configName" }
            ]
        });
    	
    	// Add click events 
        $('#configTable tbody').on( 'click', 'tr', function () {
            if ( $(this).hasClass('selected') ) {
                $(this).removeClass('selected');
            }
            else {
            	datatable.$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
            }
        });
    	
    	/* Event Bindings */
    	
    	$('#timePeriod').on( 'change', function () {
    		app.getData();
    	});
    	
    	$('#addConfig').on( 'click', function () {
    		app.addConfig();
    	});
    	  	
    	$('#deleteConfig').on( 'click', function () {
    		app.deleteConfig();
    	});
    	
    	$('#deleteAllConfigs').on( 'click', function () {
    		app.deleteAllConfigs();
    	});
    	
        this.getData();
    };

    window.app = new App;
})($);