import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.nodes.Document;

public class JSONConnectionParser {
    public static final int TBODY_IN_HTML = 3;
    public static final int TD_FOR_STATION = 1;
    public static final int TD_FOR_LINE = 0;
    public static final int TD_FOR_CONNECTION = 3;
    public static final int SPAN_FOR_LINE = 0;
    public static final int SPAN_FOR_NAME = 1;
    public static final int SPAN_FOR_SECOND_LINE_NMB = 2;
    public static final int A_FOR_STATION = 0;
    public static final String LINE_8A = "8А";
    public static final String LINE_11 = "11";

    public static JSONArray connectionsArray = new JSONArray();
    public static JSONArray oneConnection = new JSONArray();
    public static JSONObject stationToConnection = new JSONObject();

    public static JSONArray mkConnectionsJSON(Document wikiDoc){
        int trSize = wikiDoc.select("tbody")
                .eq(TBODY_IN_HTML)
                .select("tr")
                .size();

        for(int i = 0; i < trSize; i++) {
            String existConnection = wikiDoc.select("tbody")
                .eq(TBODY_IN_HTML).select("tr")
                .eq(i).select("td")
                .eq(TD_FOR_CONNECTION)
                .attr("data-sort-value");
            if (!existConnection.equals("Infinity")){
                String lineNmb = wikiDoc.select("tbody")
                        .eq(TBODY_IN_HTML)
                        .select("tr")
                        .eq(i)
                        .select("td")
                        .eq(TD_FOR_LINE)
                        .select("span")
                        .eq(SPAN_FOR_LINE).text();
                String stationName = wikiDoc.select("tbody")
                        .eq(TBODY_IN_HTML).select("tr")
                        .eq(i).select("td")
                        .eq(TD_FOR_STATION)
                        .select("a[href]")
                        .eq(A_FOR_STATION)
                        .text();
                stationToConnection.put("line", lineNmb);
                oneConnection.add(stationToConnection.clone());
                stationToConnection.clear();

                stationToConnection.put("name", stationName);
                oneConnection.add(stationToConnection.clone());
                stationToConnection.clear();

                int tdSize = wikiDoc.select("tbody")
                        .eq(TBODY_IN_HTML).select("tr")
                        .eq(i)
                        .select("td")
                        .eq(TD_FOR_CONNECTION)
                        .select("span")
                        .size();
                for (int j = 0; j < tdSize; j++){
                    if (j %2 == 0){
                        String connectionLineNmb = wikiDoc.select("tbody")
                                .eq(TBODY_IN_HTML).select("tr")
                                .eq(i)
                                .select("td")
                                .eq(TD_FOR_CONNECTION)
                                .select("span").eq(j)
                                .text();
                    }else {
                        String connectionLineName = wikiDoc.select("tbody")
                                .eq(TBODY_IN_HTML).select("tr")
                                .eq(i)
                                .select("td")
                                .eq(TD_FOR_CONNECTION)
                                .select("span").eq(j)
                                .text();
                    }
                }
            }
        }
        return connectionsArray;
    }
}
