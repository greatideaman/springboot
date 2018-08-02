(function() {

    window.onload = function() {

        $('#timePeriod').change(function(){
            var yearMonth = document.getElementById("timePeriod").value;
            app.getData(yearMonth);
        });

        document.getElementById('newButton').onclick = function() {
            var yearMonth = document.getElementById("timePeriod").value;
            var configId = document.getElementById("configId").value;
            document.getElementById("configId").value = ""
            var configName = document.getElementById("configName").value;
            document.getElementById("configName").value= "";
             app.putData(yearMonth, configId, configName);                      
        };

        document.getElementById('deleteButton').onclick = function() {
            var yearMonth = document.getElementById("timePeriod").value;
             app.deleteData(yearMonth);                      
        };
    }

    function App() {
    }

    App.prototype.putData = function(yearMonth, configId, configName) {
        var dTable = $('#configTable').DataTable();
        var value = [ configName, configId ];
        var valueJson = JSON.stringify({configValueIn: value});
        dTable.clear().draw();
        $.ajax({
            contentType: "application/json; charset=utf-8",
            type: "POST",
            url: "/configuration/addConfigurationForYearMonth/"+yearMonth,
            data: valueJson
        }).then(function(data) {
            dTable.row.add([
                configId,
                configName
            ]).draw();
        })
    };

    App.prototype.deletetData = function(yearMonth) {
        var dTable = $('#configTable').DataTable();
        dTable.clear().draw();
        $.ajax({
            type: "DELETE",
            url: "/configuration/deleteConfigurationsForYearMonth/"+yearMonth
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

    App.prototype.getData = function(yearMonth) {
        var dTable = $('#configTable').DataTable();
        dTable.clear().draw();
        $.ajax({
            type: "GET",
            url: "/configuration/getConfigurationsForYearMonth/"+yearMonth
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
            scrollY: 300,
            paging: false,
            sorting: false,
            searching: false,
            info: false
        });
    };

    window.app = new App;
})($);