import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static JSONObject mskMetroJson = new JSONObject();
    public static JSONObject mskStations = new JSONObject();
    public static final int TBODY_IN_HTML = 3;
    public static final int TD_FOR_STATION = 1;
    public static final int TD_FOR_LINE = 0;
    public static final int SPAN_FOR_LINE = 0;
    public static final int SPAN_FOR_NAME = 1;
    public static final int SPAN_FOR_SECOND_LINE_NMB = 2;
    public static final int A_FOR_STATION = 0;
    public static final String LINE_8A = "8А";
    public static final String LINE_11 = "11";

    public static void main(String[] args) {

        String wikiFile = parseFile("data/wiki.html");
        Document wikiDoc = Jsoup.parse(wikiFile);
        mskMetroJson.put("stations", mkStationsInJSON(wikiDoc));
        mskMetroJson.put("lines", mkLinesInJSON(wikiDoc));
        try{
            FileWriter file = new FileWriter("data/msk.json");
            file.write(mskMetroJson.toJSONString().replace(",", ",\n"));
            file.flush();
            file.close();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static JSONArray mkLinesInJSON(Document wikiDoc){
        JSONArray linesArrayJson = new JSONArray();
        JSONObject lineJson = new JSONObject();
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
            if (lineNmb.equals("011А")){
                lineJson.put("number", lineNmb);
                lineJson.put("name", lineName);
                lineJson.put("color", getColorByHex("background:#82C0C0"));
                if (!linesArrayJson.contains(lineJson))
                    linesArrayJson.add(lineJson.clone());
                lineJson.clear();
                continue;
            }
            if (lineNmb.equals("8А")){
                lineJson.put("number", lineNmb);
                lineJson.put("name", lineName);
                lineJson.put("color", getColorByHex("background:#FFD702"));
                if (!linesArrayJson.contains(lineJson))
                    linesArrayJson.add(lineJson.clone());
                lineJson.clear();
                continue;
            }
            lineJson.put("number", lineNmb);
            lineJson.put("name", lineName);
            lineJson.put("color",getColorByHex(lineColor));
            if(!linesArrayJson.contains(lineJson)){
                linesArrayJson.add(lineJson.clone());
            }
            lineJson.clear();
        }
        return linesArrayJson;
    }
    
    public static String getColorByHex(String hex){ 
        HashMap<String, String> colorDictionary = new HashMap<>();
        colorDictionary.put("background:#EF161E", "red");
        colorDictionary.put("background:#2DBE2C", "green");
        colorDictionary.put("background:#0078BE", "blue");
        colorDictionary.put("background:#00BFFF", "light blue");
        colorDictionary.put("background:#8D5B2D", "brown");
        colorDictionary.put("background:#ED9121", "orange");
        colorDictionary.put("background:#800080", "purple");
        colorDictionary.put("background:#FFD702", "yellow");
        colorDictionary.put("background:#999999", "grey");
        colorDictionary.put("background:#99CC00", "light green");
        colorDictionary.put("background:#82C0C0", "light gray blue");
        colorDictionary.put("background:#A1B3D4", "Very light blue");
        colorDictionary.put("background:#DE64A1", "pink");
        return colorDictionary.get(hex);
    }
    
    public static JSONObject mkStationsInJSON(Document wikiDoc){
        JSONArray stationLineTmp = new JSONArray();
        JSONArray stationLine8a = new JSONArray();
        JSONArray stationLine11 = new JSONArray();
        String flagLine = "01";
        int trSize = wikiDoc.select("tbody")
                .eq(TBODY_IN_HTML)
                .select("tr").size();

        for(int i = 0; i < trSize; i++) {
            String stationName = wikiDoc.select("tbody")
                    .eq(TBODY_IN_HTML).select("tr")
                    .eq(i).select("td")
                    .eq(TD_FOR_STATION)
                    .select("a[href]")
                    .eq(A_FOR_STATION)
                    .text();

            if (wikiDoc.select("tbody")
                    .eq(TBODY_IN_HTML)
                    .select("tr")
                    .eq(i).select("td")
                    .eq(TD_FOR_LINE)
                    .select("span")
                    .size() > 3){
                String secondLineNmb = wikiDoc.select("tbody")
                        .eq(TBODY_IN_HTML)
                        .select("tr")
                        .eq(i)
                        .select("td")
                        .eq(TD_FOR_LINE)
                        .select("span")
                        .eq(SPAN_FOR_SECOND_LINE_NMB).text();
                if (secondLineNmb.equals(LINE_8A)){
                    stationLine8a.add(stationName);
                }

                if (secondLineNmb.equals(LINE_11)){
                    stationLine11.add(stationName);
                }
            }

            String lineNmb = wikiDoc.select("tbody")
                    .eq(TBODY_IN_HTML)
                    .select("tr")
                    .eq(i)
                    .select("td")
                    .eq(TD_FOR_LINE)
                    .select("span")
                    .eq(SPAN_FOR_LINE).text();

            if (lineNmb.equals(LINE_8A)){
                stationLine8a.add(stationName);
            }

            if (lineNmb.equals(LINE_8A)){
                stationLine11.add(stationName);
            }

            String tmpLine = lineNmb;
            if (!flagLine.equals(tmpLine)) {
                mskStations.put(flagLine, stationLineTmp.clone());
                flagLine = tmpLine;
                stationLineTmp.clear();
                stationLineTmp.add(stationName);
            }else{
                stationLineTmp.add(stationName);
            }
        }

        mskStations.put(LINE_11, stationLine11.clone());
        mskStations.put(LINE_8A, stationLine8a.clone());
        return mskStations;
    }

    public static String parseFile(String path) {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            lines.forEach(line -> builder.append(line + "\n"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }

}