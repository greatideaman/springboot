(function () {
    function App() {}

    App.prototype.getData = function () {
        $.ajax({
            url: "/configuration/" + $("#timePeriod").val(),
            success: function (result) {
                $("#configdata").empty();
                var tableContent = "";
                for (var i = 0; i < result.length; i++) {
                    tableContent += "<tr>";
                    tableContent += "<td>" + result[i].configId + "</td>";
                    tableContent += "<td>" + result[i].configName + "</td>";
                    tableContent += "<td>" + '<button id='+ result[i].configId +' onclick="app.deleteSpecificData(this.id)"><i>Remove</i></button>' + "</td>";
                    tableContent += "</tr>";
                }
                $("#configdata").append(tableContent);
            },
        });
    };

    App.prototype.insertData = function () {
        $.ajax({
            headers: {
                Accept: "application/json",
                "Content-Type": "application/json",
            },
            url: "/configuration/" + $("#timePeriod").val(),
            dataType: "text",
            method: "POST",
            data: JSON.stringify({
                configName: $("#configname").val(),
                configId: $("#configid").val(),
            }),
            success: function (data) {
                $("#configname").val("");
                $("#configid").val("");
                app.getData();
            },
        });
    };

    App.prototype.deleteData = function () {
        $.ajax({
            url: "/configuration/" + $("#timePeriod").val(),
            method: "DELETE",
            success: function (data) {
                app.getData();
            },
        });
    };

    App.prototype.deleteSpecificData = function (input) {
        $.ajax({
            url: "/configuration/singleConfig/" + $("#timePeriod").val() + '?configId=' + input,
            method: "DELETE",
            success: function (data) {
                app.getData();
            },
        });
    };

    App.prototype.init = function () {
        $("#configTable").DataTable({
            scrollY: 300,
            paging: false,
            sorting: false,
            searching: false,
            info: false
        });
        app.getData();
    };

    window.app = new App();
})($);