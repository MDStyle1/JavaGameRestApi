package com.mds.gameserver.page;

import com.mds.gameserver.views.Scores;
import com.mds.gameserver.views.ScoresInfo;

import java.util.List;

public class ScoresPageGet {
    private String page;

    public String getPage(Scores scores){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\" />\n" +
                "    <title>Scores</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div id=\"test\">\n" +
                "    <p><button type=\"submit\" name=\"butMenu\" onclick=\"goToMenu();\">Menu</button></p>\n" +
                "</div>\n" +
                "<div id=\"table\">\n" +
                "    <table id=\"tableScore\" border=\"1\">\n" +
                "        <tr><th >name</th><th>score</th></tr>\n");
        addTableScores(stringBuilder,scores);
        stringBuilder.append("    </table>\n" +
                "</div>\n" +
                "<script>\n" +
                "    function goToMenu(){\n" +
                "        document.location.href = \"http://localhost:8080/menu\";\n" +
                "    }\n" +
                "</script>\n" +
                "</body>\n" +
                "</html>");
        page = stringBuilder.toString();
        return page;
    }
    private void addTableScores(StringBuilder stringBuilder,Scores scores){
        List<ScoresInfo> scoresInfoList = scores.getList();
        if(scores!=null) scoresInfoList.forEach(item->{
            stringBuilder.append("<tr><td>"+item.getName()+"</td><td>"+item.getScore()+"</td></tr>");
        });

    }
}
