package org.project.utils;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HtmlRenderer {

    public String renderTable(List<List<String>> tableData) {
        StringBuilder html = new StringBuilder();
        html.append("<table style='border-collapse: collapse; width: 100%;'>");

        for (int i = 0; i < tableData.size(); i++) {
            List<String> row = tableData.get(i);
            html.append("<tr style='").append(i == 0 ? "background-color: #f2f2f2;" : "").append("'>");
            for (String cell : row) {
                html.append("<td style='border: 1px solid #ddd; padding: 8px;'>").append(cell).append("</td>");
            }
            html.append("</tr>");
        }

        html.append("</table>");
        return html.toString();
    }

    public String renderPlot(List<Integer> plotData, List<String> dates) {
        StringBuilder html = new StringBuilder();
        html.append("<div style='width: 100%;'>");

        for (int i = 0; i < plotData.size(); i++) {
            int value = plotData.get(i);
            html.append("<div style='margin: 10px 0; display: flex; align-items: center;'>");
            html.append("<span style='width: 80px;'>").append(dates.get(i)).append("</span>");
            html.append("<div style='background-color: #4CAF50; height: 20px; width: ").append(value * 5).append("px;'></div>");
            html.append("<span style='margin-left: 10px;'>").append(value).append("%</span>");
            html.append("</div>");
        }

        html.append("</div>");
        return html.toString();
    }
}