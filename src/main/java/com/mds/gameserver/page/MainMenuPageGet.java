package com.mds.gameserver.page;

public class MainMenuPageGet {
    private String page;

    public String getPage(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\" />\n" +
                "    <title>Main menu</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div id=\"test\">\n" +
                "    <p><button type=\"submit\" name=\"butScore\" onclick=\"goToScore();\">Scores</button></p>\n" +
                "    <p><button type=\"submit\" name=\"butChat\" onclick=\"goToChat();\">Chat</button></p>\n" +
                "    <p><button type=\"submit\" name=\"butChatTest\" onclick=\"goToChatTest();\">Test</button></p>\n" +
                "    <p><button type=\"submit\" name=\"butLogout\" onclick=\"logout();\">Out</button></p>\n" +
                "</div>\n" +
                "<script>\n" +
                "    function goToChatTest(){\n" +
                "    document.location.href = \"http://188.243.224.61:8080/chat/test\";\n" +
                "    }\n" +
                "    function goToChat(){\n" +
                "        document.location.href = \"http://188.243.224.61:8080/chat\";\n" +
                "    }\n" +
                "    function goToScore(){\n" +
                "        document.location.href = \"http://188.243.224.61:8080/scores/top10\";\n" +
                "    }\n" +
                "    function logout(){\n" +
                "        let xhr = new XMLHttpRequest();\n" +
                "        xhr.open('GET', 'http://188.243.224.61:8080/out');\n" +
                "        xhr.send();\n" +
                "        xhr.onload = function() {\n" +
                "            if (xhr.status != 200) {\n" +
                "                alert(`Ошибка ${xhr.status}: ${xhr.statusText}`);\n" +
                "            } else {\n" +
                "                document.location.href = \"http://188.243.224.61:8080/login\";\n" +
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
