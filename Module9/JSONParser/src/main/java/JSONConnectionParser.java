import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.nodes.Document;

import java.util.ArrayList;

public class JSONConnectionParser {
    public static final int TBODY_IN_HTML = 3;
    public static final int TD_FOR_STATION = 1;
    public static final int TD_FOR_LINE = 0;
    public static final int TD_FOR_CONNECTION = 3;
    public static final int SPAN_FOR_LINE = 0;
    public static final int A_FOR_STATION = 0;

    public static JSONArray connectionsArray = new JSONArray();
    public static JSONArray oneConnection = new JSONArray();
    public static JSONObject stationToConnection = new JSONObject();
    public static ArrayList<String> stationsConnected = new ArrayList<>();

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
                if(stationsConnected.contains(stationName)){
                    continue;
                }
                stationsConnected.add(stationName);
                stationToConnection.put("line", lineNmb);
                stationToConnection.put("station", stationName);
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
                        stationToConnection.put("line", connectionLineNmb);
                    }else {
                        String[] titleElements =  wikiDoc.select("tbody")
                                .eq(TBODY_IN_HTML).select("tr")
                                .eq(i)
                                .select("td")
                                .eq(TD_FOR_CONNECTION)
                                .select("span").eq(j)
                                .attr("title")
                                .split(" ");
                        if(titleElements[titleElements.length -2].equals("кольцевой")) {
                            String connectionLineName = wikiDoc.select("tbody")
                                    .eq(TBODY_IN_HTML).select("tr")
                                    .eq(i)
                                    .select("td")
                                    .eq(TD_FOR_CONNECTION)
                                    .select("span").eq(j)
                                    .attr("title").replace("Большой кольцевой линии", "");
                            connectionLineName = connectionLineName.replace("Переход на станцию ", "").trim();
                            stationsConnected.add(connectionLineName);
                            stationToConnection.put("station", connectionLineName);
                            oneConnection.add(stationToConnection.clone());
                            stationToConnection.clear();
                        }else if (titleElements[titleElements.length -2].equals("центрального")){
                            String connectionLineName = wikiDoc.select("tbody")
                                    .eq(TBODY_IN_HTML).select("tr")
                                    .eq(i)
                                    .select("td")
                                    .eq(TD_FOR_CONNECTION)
                                    .select("span").eq(j)
                                    .attr("title").replace("Московского центрального кольца", "");
                            connectionLineName = connectionLineName.replace("Переход на станцию ", "").trim();
                            stationsConnected.add(connectionLineName);
                            stationToConnection.put("station", connectionLineName);
                            oneConnection.add(stationToConnection.clone());
                            stationToConnection.clear();
                        }else
                        {
                            String connectionLineName = wikiDoc.select("tbody")
                                    .eq(TBODY_IN_HTML).select("tr")
                                    .eq(i)
                                    .select("td")
                                    .eq(TD_FOR_CONNECTION)
                                    .select("span").eq(j)
                                    .attr("title").replace(titleElements[titleElements.length - 1], "");
                            connectionLineName = connectionLineName.replace(titleElements[titleElements.length - 2], "");
                            connectionLineName = connectionLineName.replace("Кросс-платформенная пересадка на станцию ", "").trim();
                            connectionLineName = connectionLineName.replace("Переход на станцию ", "").trim();
                            stationsConnected.add(connectionLineName);
                            stationToConnection.put("station", connectionLineName);
                            oneConnection.add(stationToConnection.clone());
                            stationToConnection.clear();
                        }
                    }
                }
                connectionsArray.add(oneConnection.clone());
                oneConnection.clear();
            }
        }
        return connectionsArray;
    }
}
