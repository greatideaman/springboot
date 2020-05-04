(function() {
    function App() {
        this.table = null;
        this.curMY = "012018";
    }

    // SORRY!!! hard-coded urls!

    App.prototype.getData = function(id) {
        this.curMY = id;
        $.ajax({ 
            url:'/configuration/'+id, method:'GET'
        }).then(function(data) {
            window.app.table.clear();
            window.app.table.rows.add(data);
            window.app.table.draw();
        })
    };

    App.prototype.delItem = function (id, value) {
        if (confirm("Are you sure you want to delete " + value + "?")) {
            $.ajax({ 
                url:'/configuration/'+window.app.curMY+'/'+id, method:'DELETE'
            }).then(function(data) {
                window.app.getData(window.app.curMY);
            });
        }
    };

    App.prototype.addItem = function () {
        var v = prompt("Enter the new configuration:", "");
        if (v != "") {
            $.ajax({ 
                url:'/configuration/'+window.app.curMY, method:'POST',
                contentType:'text/plain',
                data:v
            }).then(function(data) {
                window.app.getData(window.app.curMY);
            })
        }
    };

    App.prototype.clearAll = function () {
        if (confirm("Are you sure you want to delete all the configurations for " + this.curMY + "?")) {
            $.ajax({ 
                url:'/configuration/'+window.app.curMY, method:'DELETE'
             }).then(function(data) {
                window.app.getData(window.app.curMY);
            })
        }
    };

    App.prototype.init = function() {
        this.table = $('#configTable').DataTable({
            scrollY: 300,
            paging: false,
            sorting: false,
            searching: false,
            info: false,
            columns:[
                {data:'configId'},
                {data:'configName'},
                {'render': function ( data, type, row ) {
                        return '<span onclick="window.app.delItem(' + row.configId + ', \'' + row.configName + '\');" style="color:red;cursor:pointer;"><b>X</b></span>';
                    }
                }
            ]
        });
        $("#timePeriod").change(
            function() {
                window.app.getData(this.options[this.selectedIndex].value);
            }
        );
        $("#addConfigurationItem").click(
            function() {
                window.app.addItem();
            }
        );
        $("#clearConfiguration").click(
            function() {
                window.app.clearAll();
            }
        );
        window.app.getData(this.curMY);
    };

    window.app = new App;
})($);