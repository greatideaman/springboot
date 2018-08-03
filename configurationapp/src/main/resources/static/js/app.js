(function() {

    window.onload = function() {

        $('#timePeriod').change(function(){
            var yearMonth = document.getElementById("timePeriod").value;
            app.getData(yearMonth);
        });

        document.getElementById('newButton').onclick = function() {
            var yearMonth = document.getElementById("timePeriod").value;
            var configName = document.getElementById("configName").value;
            document.getElementById("configName").value= "";
             app.putData(yearMonth, configName);                      
        };

        document.getElementById('deleteButton').onclick = function() {
            var yearMonth = document.getElementById("timePeriod").value;
             app.deleteData(true, yearMonth, null, null);                      
        };
    }

    function App() {
    }

    App.prototype.putData = function(yearMonth, configName) {
        var dTable = $('#configTable').DataTable();
        var JSONObject= {
            'configId': 0,
            'configName': configName
        };
    
        $.ajax({
            type: 'PUT',
            url: "/configuration/addConfigurationForYearMonth/"+yearMonth,
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(JSONObject),
            async: true,
            // success: function(result) {
            //     alert('Result: ' + result);
            // },
            // error: function(jqXHR, textStatus, errorThrown) {
            //     alert("error"+jqXHR.status + ' ' + jqXHR.responseText);
            // }
        }).then(function(configId) {
                    dTable.row.add([
                        configId,
                        configName
                    ]).draw();
                })
    } 

    App.prototype.deleteData = function(deleteAll,yearMonth, configId, configName) {
        var dTable = $('#configTable').DataTable();
        var JSONObject= {
            'configId': configId,
            'configName': configName
        };
        $.ajax({
            type: "DELETE",
            url: "/configuration/deleteConfigurationsForYearMonth/"+yearMonth+"/"+deleteAll,
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(JSONObject),
            async: true,
        }).then(function(data) {
            if (deleteAll) dTable.clear().draw();
        })
    };

    App.prototype.getData = function(yearMonth) {
        var dTable = $('#configTable').DataTable();
        dTable.clear().draw();
        $.ajax({
            type: "GET",
            url: "configuration/getConfigurationsForYearMonth/"+yearMonth,
        }).then(function(data) {
            console.log("data: "+data);
            $.each(data, function( index, configValue ) {
                dTable.row.add([
                    configValue.configId,
                    configValue.configName
                ]).draw();
            });
        })
    };

    App.prototype.init = function() {
        $('#configTable').DataTable({
            "columnDefs": [ {
                "targets": 2,
                "data": null,
                "defaultContent": "<button>Delete</button>"
            } ],
            scrollY: 300,
            paging: false,
            sorting: false,
            searching: false,
            info: false
        });
        var dTable = $('#configTable').DataTable();
        $('#configTable tbody').on( 'click', 'button', function () {
            var data = dTable.row( $(this).parents('tr') ).data();
            dTable.row( $(this).parents('tr') )
            .remove()
            .draw();
            app.deleteData(false, document.getElementById("timePeriod").value, data[0], data[1]);
        } );
    };


    window.app = new App;
})($);