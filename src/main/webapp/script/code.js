var index = 0;

$('#connect').click(function () {
    if (check()) {
        $.ajax({
            url: "http://localhost:8080/GeekAdmin/CodeServlet",
            type: "post",
            dataType: "json",
            data: {
                username: $('#username').val(),
                password: $('#password').val(),
                ip: $('#ip0').val() + "." + $('#ip1').val() + "." + $('#ip2').val() + "." + $('#ip3').val(),
                version: $('#version').val()
            }, success: function (result) {
                if (result.dataBasesName !== "" && result.dataBasesName != null) {
                    createToast("success", "连接成功！", "../");
                    card = $("div[class='geek-card row']:last");
                    var div = $("<div class='geek-card row' id=block-" + index++ + " onclick='onprint(this.id)'></div>")
                    var table =
                        $("<table class='geek-table' id='database' style='display: none;'>" +
                            "<tr>\n" +
                            "<th>表名</th>\n" +
                            "<th>备注</th>\n" +
                            "<th>列名</th>\n" +
                            "<th>Java类型</th>\n" +
                            "<th>数据库类型</th>\n" +
                            "<th>长度</th>\n" +
                            "<th>注释</th>\n" +
                            "</tr>" +
                            "</table>");
                    table.show();
                    // 遍历数据并生成表格行
                    for (let i = 0; i < result.tables.length; i++) {
                        let tb = result.tables[i];
                        console.log(tb)
                        for (let j = 0; j < tb.columns.length; j++) {
                            let cn = tb.columns[j];
                            console.log(cn)
                            let row = $("<tr>");
                            row.append($("<td>").text(tb.tableName));
                            row.append($("<td>").text(tb.tableRemarksName));
                            row.append($("<td>").text(cn.columnName));
                            row.append($("<td>").text(cn.columnClassName));
                            row.append($("<td>").text(cn.columnTypeName));
                            row.append($("<td>").text(cn.columnDisplaySize));
                            row.append($("<td>").text(cn.columnRemarksName));
                            table.append(row);
                        }
                    }
                    div.append(table);
                    card.after(div)
                } else {
                    createToast("warning", "连接超时，请检查mysql数据库是否开启远程连接！", "../");
                }
            }
        })
    } else {
        createToast("error", "请输入用户名、密码、IP和数据库版本！", "../");
    }
})

function printHtml(html) {
    var bodyHtml = document.body.innerHTML;
    document.body.innerHTML = html;
    window.print();
    document.body.innerHTML = bodyHtml;
}

function onprint(id) {
    var html = $("#"+id).html();
    printHtml(html);
}

function check() {
    var username = $('#username').val();
    var password = $('#password').val();
    var ip0 = $('#ip0').val();
    var ip1 = $('#ip1').val();
    var ip2 = $('#ip2').val();
    var ip3 = $('#ip3').val();
    var version = $('#version').val();
    if (username == null || username.length === 0) {
        console.log(username);
        return false;
    } else if (password == null) {
        console.log(password);
        return false;
    } else if ((ip0 == null || ip0.length === 0) || (ip1 == null || ip1.length === 0) || (ip2 == null || ip2.length === 0) || (ip3 == null || ip3.length === 0)) {
        console.log(ip0 + "." + ip1 + "." + ip2 + "." + ip3);
        return false;
    } else if (version == null || version.length === 0) {
        console.log(version);
        return false;
    }
    return true;
}