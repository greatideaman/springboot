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
            info: false
        });
    };

    window.app = new App;
})($);

$(document).ready(function () {
	app.init();
	
    $("#save-configurations").click(function (event) {
    	var period = $( "#timePeriod-button option:selected" ).text();
        //stop submit the form, we will post it manually.
    	
        event.preventDefault();
        
        var config;
        var arr = configArry = new Object();
        var i = 0;
        
        // create array of configurations
        $("tr.new").each(function() {
        	config = {}
        	config["yearMonth"] = $('#timePeriod-button :selected').text();
        	config["configId"] = 0;
        	config["configName"] =  this.cells[1].innerHTML;
        	
        	configArry[i++] = JSON.stringify(config);
        });
        
        ajaxAddSubmit(configArry);
    });
    
    $("#add-button").click(function (event) {  
        var configName = $("#config-name").val();
	        if (configName.trim() != "") {
	        var markup = "<tr class='new'><td><input type='checkbox' name='config' >0</td><td>" + configName + "</td></tr>";
	        $("table tbody").append(markup);
	        document.getElementById("config-name").value = "";
	        document.getElementById("config-name").focus();
        }
        else {
        	alert("Please provide a new configuration!");
        }
    });
    
    $("#delete-configuration").click(function(event) {
    	var period = $( "#timePeriod-button option:selected" ).text();

        var config;
        var arr = configArry = new Object();
        var i = 0;
        
        $('input[type=checkbox]').each(function() {
        	config = {}
        	config["yearMonth"] = $('#timePeriod-button :selected').text();
        	config["configId"] = this.id;
        	config["configName"] =  "";
        	if (this.checked) {  
        		configArry[i++] = JSON.stringify(config);
        	}
        });
        
        ajaxDeleteSubmit(configArry);
    });
    
    $("#timePeriod-button").change(function(event) {
    	var period = $('#timePeriod-button :selected').text()         
    	clearTable();
        popTable( period );
    });
   
    
});


function ajaxAddSubmit(configArry) {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/addConfigurations",
        data: JSON.stringify(configArry),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            clearTable();
            popTable( $( "#timePeriod-button option:selected" ).text() );
        },
        error: function (e) {

        }
    });
}   

function ajaxDeleteSubmit(configArry) {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/deleteConfigurations",
        data: JSON.stringify(configArry),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            clearTable();
            popTable( $( "#timePeriod-button option:selected" ).text() );
        },
        error: function (e) {

        }
    });
}   


function clearTable() {
	$("#configTable tr").remove();
}


function popTable(monthYear) {
	
	    $.ajax({
        type: "GET",
        contentType: "application/json; charset=utf-8",
        url: "/getConfigurations",
        data: {"monthYear":monthYear},
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (obj) {
        	var json = obj.data;
        	
        	if (json != "") {
	        	json = JSON.parse(json);
	        	
				for(var i = 0; i < json.length; i++) {
				    var obj = json[i];
				    
				    console.log(obj.configName);
				    
					var markup = "<tr class='configuration'><td><input id='" + obj.configId + "' type='checkbox' name='config' >" + obj.configId + "</td><td>" + obj.configName + "</td></tr>";
					$("table tbody").append(markup);
	
				}
        	}
        },
        error: function (e) {
        	var error = "error";

        }
    });
}


    
