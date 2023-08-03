var index = 1;

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
                console.log(result)
                database = result.data;
                zipName = result.zip;
                if (result.code === 200) {
                    createToast("success", "连接成功！", "../");
                    card = $("div[class='geek-card row']:last");
                    var div = $("<div class='geek-card row' id=block-" + index++ + "'></div>")
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
                    for (let i = 0; i < database.tables.length; i++) {
                        let tb = database.tables[i];
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
                    div.append($("<button class='geek-button-green right code-download' onclick='download(zipName)'>下载</button>"))
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

function download(zipName) {

    var a = document.createElement("a");
    a.href =  "../DownloadServlet?zipName=" + zipName;
    a.style.display = 'none'
    a.click();
    a.remove();

    // ajax支持的服务器返回数据类型有：xml、json、script、html，
    // 其他类型(例如二进制流)将被作为String返回，无法触发浏览器的下载处理机制和程序。
    // $.ajax({
    //     url: "../DownloadServlet",
    //     method: "get",
    //     data: {
    //         zipName: zipName,
    //     },
    //     success: function (result, state, xhr) {//3个参数
    //         //result:请求到的结果数据
    //         //state:请求状态（success）
    //         //xhr:XMLHttpRequest对象
    //
    //         // 从Response Headers中获取fileName
    //         let fileName = xhr.getResponseHeader('Content-Disposition').split(';')[1].split('=')[1].replace(/\"/g, '')
    //         //获取下载文件的类型
    //         let type = xhr.getResponseHeader("content-type")
    //         //结果数据类型处理
    //         let blob = new Blob([result], { type: type })
    //
    //         //对于<a>标签，只有 Firefox 和 Chrome（内核）支持 download 属性
    //         //IE10以上支持blob，但是依然不支持download
    //         if ('download' in document.createElement('a')) {//支持a标签download的浏览器
    //             //通过创建a标签实现
    //             let link = document.createElement("a");
    //             //文件名
    //             link.download = fileName;
    //             link.style.display = "none"
    //             link.href = URL.createObjectURL(blob);
    //             document.body.appendChild(link);
    //             link.click();//执行下载
    //             URL.revokeObjectURL(link.href);//释放url
    //             document.body.removeChild(link);//释放标签
    //         } else {//不支持
    //             if (window.navigator.msSaveOrOpenBlob) {
    //                 window.navigator.msSaveOrOpenBlob(blob, fileName)
    //             }
    //         }
    //     },
    // })
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