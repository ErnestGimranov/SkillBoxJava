import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.nodes.Document;

import java.util.HashMap;

public class JSONLinesParser {
    public static final int TBODY_IN_HTML = 3;
    public static final int TD_FOR_LINE = 0;
    public static final int SPAN_FOR_LINE = 0;
    public static final int SPAN_FOR_NAME = 1;

    public static JSONArray linesArrayJson = new JSONArray();
    public static JSONObject lineJson = new JSONObject();

    public static JSONArray mkLinesInJSON(Document wikiDoc, HashMap<String, String> specialLines){
        int trSize = wikiDoc.select("tbody")
                .eq(TBODY_IN_HTML)
                .select("tr")
                .size();
        for(int i = 0; i < trSize; i++) {
            String lineNmb = wikiDoc.select("tbody")
                    .eq(TBODY_IN_HTML).select("tr")
                    .eq(i).select("td")
                    .eq(TD_FOR_LINE)
                    .select("span")
                    .eq(SPAN_FOR_LINE).text();
            String lineName = wikiDoc.select("tbody")
                    .eq(TBODY_IN_HTML).select("tr")
                    .eq(i).select("td")
                    .eq(TD_FOR_LINE)
                    .select("span")
                    .eq(SPAN_FOR_NAME).attr("title");
            String lineColor = wikiDoc.select("tbody")
                    .eq(TBODY_IN_HTML).select("tr")
                    .eq(i).select("td")
                    .eq(TD_FOR_LINE)
                    .attr("style");

            if (specialLines.containsKey(lineNmb)){
                lineJson.put("number", lineNmb);
                lineJson.put("name", lineName);
                lineJson.put("color", ColorDictionary.getColorByHex(specialLines.get(lineNmb)));
                if (!linesArrayJson.contains(lineJson))
                    linesArrayJson.add(lineJson.clone());
                lineJson.clear();
                continue;
            }

            lineJson.put("number", lineNmb);
            lineJson.put("name", lineName);
            lineJson.put("color", ColorDictionary.getColorByHex(lineColor));
            if(!linesArrayJson.contains(lineJson)){
                linesArrayJson.add(lineJson.clone());
            }
            lineJson.clear();
        }
        return linesArrayJson;
    }
}
