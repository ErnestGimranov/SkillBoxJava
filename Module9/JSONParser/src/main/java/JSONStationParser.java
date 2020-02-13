import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.nodes.Document;

public class JSONStationParser {
    public static final int TBODY_IN_HTML = 3;
    public static final int TD_FOR_STATION = 1;
    public static final int TD_FOR_LINE = 0;
    public static final int SPAN_FOR_LINE = 0;
    public static final int SPAN_FOR_SECOND_LINE_NMB = 2;
    public static final int A_FOR_STATION = 0;
    public static final String LINE_8A = "8А";
    public static final String LINE_11 = "11";

    public static JSONObject mskStations = new JSONObject();

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
}
