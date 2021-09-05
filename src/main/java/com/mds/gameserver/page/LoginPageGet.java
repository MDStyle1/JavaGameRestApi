package com.mds.gameserver.page;

public class LoginPageGet {
    private String page;

    public String getPage(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\" />\n" +
                "</head>\n" +
                "<body>\n" +
                "<div id=\"output\"></div>\n" +
                "<div id=\"test\">\n" +
                "    <p>Login<input type=\"text\" id=\"login\"></p>\n" +
                "    <p>Password<input type=\"text\" id=\"password\"></p>\n" +
                "    <p><button type=\"submit\" name=\"butLogin\" onclick=\"sendRequest();\">Login</button></p>\n" +
                "</div>\n" +
                "<script>\n" +
                "    let divTest = document.getElementById(\"test\");\n" +
                "    let username = document.getElementById(\"login\");\n" +
                "    let userPassword = document.getElementById(\"password\");\n" +
                "    function sendRequest(event){\n" +
                "        let xhr = new XMLHttpRequest();\n" +
                "        let json = JSON.stringify({\n" +
                "            name: username.value,\n" +
                "            password: userPassword.value\n" +
                "        });\n" +
                "        xhr.open('POST', 'http://localhost:8080/login');\n" +
                "        xhr.setRequestHeader('Accept', 'application/json');\n" +
                "        xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');\n" +
                "        xhr.send(json);\n" +
                "        xhr.onload = function() {\n" +
                "            if (xhr.status != 200) {\n" +
                "                alert(`Ошибка ${xhr.status}: ${xhr.statusText}`);\n" +
                "            } else {\n" +
                "                document.location.href = \"http://localhost:8080/menu\";\n" +
                "            }\n" +
                "        };\n" +
                "    }\n" +
                "</script>\n" +
                "</body>\n" +
                "</html>");
        page = stringBuilder.toString();
        return page;
    }
}
