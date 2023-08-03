<%--
Created by IntelliJ IDEA.
User: Admin
Date: 2023/1/1
Time: 0:44
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Geek Admin</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <link rel="stylesheet" type="text/css" href="../style/nprogress.css">
        <link rel="stylesheet" type="text/css" href="../style/geek.css">
        <link rel="shortcut icon" href="favicon.ico">
    </head>
    <body>
        <div class="geek-main geek-flex">
            <details open>
                <summary>Final assignment</summary>
                <details open>
                    <summary>style</summary>
                        <details>
                            <summary>css</summary>
                            <p>index.css</p>
                            <p>all.css</p>
                        </details>
                        <details>
                            <summary>js</summary>
                            <p>index.js</p>
                            <p>effect.js</p>
                            <p>all.js</p>
                        </details>
                        <details>
                            <summary>img</summary>
                            <p>background.png</p>
                            <p>logo.png</p>
                        </details>
                    </details>
                    <details open>
                        <summary>file</summary>
                        <div class="folder">
                            <p>Implementation principle.docx</p>
                            <p>element.xlsx</p>
                        </div>
                    </details>
            </details>
        </div>
    </body>
    <script type="text/javascript" src="../script/nprogress.js"></script>
    <script type="text/javascript">
        NProgress.start();
        NProgress.done();
    </script>
</html>