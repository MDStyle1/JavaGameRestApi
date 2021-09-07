package com.mds.gameserver.page;

public class TestPageGet {
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
                "    <p id =\"t1\">azazaTest</p>\n" +
                "</div>\n" +
                "<script type=\"text/javascript\" src=\"test1.js\"></script>\n" +
                "<script>\n" +
                "\n" +
                "</script>\n" +
                "</body>\n" +
                "</html>");
        page = stringBuilder.toString();
        return page;
    }
}
