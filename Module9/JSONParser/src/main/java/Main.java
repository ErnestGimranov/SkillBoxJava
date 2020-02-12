import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static JSONObject mskMetroJson = new JSONObject();
    public static JSONObject mskStations = new JSONObject();
    public static JSONArray mskLInes = new JSONArray();
    public static JSONArray stationLineTmp = new JSONArray();
    public static JSONArray stationLine8a = new JSONArray();
    public static JSONArray stationLine11 = new JSONArray();
    public static final int TBODY_IN_HTML = 3;
    public static final int TD_FOR_STATION = 1;
    public static final int TD_FOR_LINE = 0;
    public static final int SPAN_FOR_LINE = 0;
    public static final int SPAN_FOR_SECOND_LINE = 2;
    public static final int A_FOR_STATION = 0;
    public static final String LINE_8A = "8А";
    public static final String LINE_11 = "11";




    public static void main(String[] args) {
        String wikiFile = parseFile("data/wiki.html");
        Document wikiDoc = Jsoup.parse(wikiFile);
        mkStationsInJSON(wikiDoc);
        
    }
    
    public static JSONObject mkStationsInJSON(Document wikiDoc){
        String flagLine = "01";
        for(int i = 0; i < wikiDoc.select("tbody").eq(TBODY_IN_HTML).select("tr").size(); i++) {
            Elements stationElements = wikiDoc.select("tbody").eq(TBODY_IN_HTML).select("tr").eq(i).select("td").eq(TD_FOR_STATION).select("a[href]").eq(A_FOR_STATION);

            if (wikiDoc.select("tbody").eq(TBODY_IN_HTML).select("tr").eq(i).select("td").eq(TD_FOR_LINE).select("span").size() > 3){
                Elements secondLineElement = wikiDoc.select("tbody").eq(TBODY_IN_HTML).select("tr").eq(i).select("td").eq(TD_FOR_LINE).select("span").eq(SPAN_FOR_SECOND_LINE);
                if (secondLineElement.text().equals(LINE_8A)){
                    stationLine8a.add(stationElements.text());
                }

                if (secondLineElement.text().equals(LINE_11)){
                    stationLine11.add(stationElements.text());
                }
            }

            Elements lineElements = wikiDoc.select("tbody").eq(TBODY_IN_HTML).select("tr").eq(i).select("td").eq(TD_FOR_LINE).select("span").eq(SPAN_FOR_LINE);

            if (lineElements.text().equals(LINE_8A)){
                stationLine8a.add(stationElements.text());
            }

            if (lineElements.text().equals(LINE_8A)){
                stationLine11.add(stationElements.text());
            }

            String tmpLine = lineElements.text();
            if (!flagLine.equals(tmpLine)) {
                mskStations.put(flagLine, stationLineTmp.clone());
                flagLine = tmpLine;
                stationLineTmp.clear();
                stationLineTmp.add(stationElements.text());
            }else{
                stationLineTmp.add(stationElements.text());
            }
        }

        mskStations.put("11", stationLine11.clone());
        mskStations.put("8А", stationLine8a.clone());
//
//        System.out.println();
//        mskStations.forEach((s, s2) ->
//                System.out.println(s + " " + s2)
//        );
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