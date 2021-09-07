package com.mds.gameserver.page;

import org.springframework.security.core.context.SecurityContextHolder;

public class ChatPageGet {
    private String page;

    public String getPage(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\" />\n" +
                "    <title>Scores</title>\n" +
                "</head>\n" +
                "<style>\n" +
                "    .myMesInChat{\n" +
                "        text-align: right;\n" +
                "        margin-left: auto;\n" +
                "    }\n" +
                "    .otherMesInChat{\n" +
                "        text-align: left;\n" +
                "        margin-right: auto;\n" +
                "    }\n" +
                "    .container {\n" +
                "        height: 300px;\n" +
                "        width: 500px;\n" +
                "        background: #fff;\n" +
                "        border: 1px solid #C1C1C1;\n" +
                "        overflow-y: scroll;\n" +
                "        overflow-x: hidden;\n" +
                "    }\n" +
                "</style>\n" +
                "<body>\n" +
                "<div id=\"test\">\n" +
                "    <p><button type=\"submit\" name=\"butMenu\" onclick=\"goToMenu();\">Menu</button></p>\n" +
                "</div>\n" +
                "<div id=\"container\" class=\"container\">\n" +
                "</div>\n" +
                "<div id=\"chatSend\">\n" +
                "    <p><input type=\"text\" id=\"textMessage\"> <button type=\"submit\" id=\"butSend\" onclick=\"sendMessage();\">Send</button></p>\n" +
                "</div>\n" +
                "<script>\n" +
                "    let name=\""+
                SecurityContextHolder.getContext().getAuthentication().getName()
                +"\";\n" +
                "</script>\n" +
                "<script type=\"text/javascript\" src=\"chat/chat.js\"></script>\n" +
                "</body>\n" +
                "</html>");
        page = stringBuilder.toString();
        return page;
    }
}
