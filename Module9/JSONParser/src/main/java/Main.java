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
        mskMetroJson.put("stations", JSONStationParser.mkStationsInJSON(wikiDoc));
        mskMetroJson.put("lines", JSONLinesParser.mkLinesInJSON(wikiDoc));
        try{
            FileWriter file = new FileWriter("data/msk.json");
            file.write(mskMetroJson.toJSONString().replace(",", ",\n"));
            file.flush();
            file.close();
        } catch (Exception ex){
            ex.printStackTrace();
        }
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