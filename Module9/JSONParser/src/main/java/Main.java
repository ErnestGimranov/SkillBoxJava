
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static JSONObject mskMetroJson = new JSONObject();
    public static String pathToJSON = "data/msk.json";

    public static void main(String[] args) {

        String wikiFile = parseFile("data/wiki.html");
        Document wikiDoc = Jsoup.parse(wikiFile);

        mskMetroJson.put("stations", JSONStationParser.mkStationsInJSON(wikiDoc));
        mskMetroJson.put("lines", JSONLinesParser.mkLinesInJSON(wikiDoc));
        mskMetroJson.put("connections", JSONConnectionParser.mkConnectionsJSON(wikiDoc));
        String json =
                new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().
                        create().toJson(mskMetroJson);
        try{
            FileWriter file = new FileWriter(pathToJSON);
            file.write(json);
            file.flush();
            file.close();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        try {
            JSONParser parser = new JSONParser();
            JSONObject mskMetro = (JSONObject) parser.parse(getJsonFile());
            JSONObject stationsOnLines = (JSONObject) mskMetro.get("stations");
            stationsOnLines.keySet().forEach(s -> {
                String line = (String) s;
                JSONArray stations = (JSONArray) stationsOnLines.get(s);
                System.out.println("На линии " + line + " - " + stations.size() + " станций");
            });
        }
        catch (Exception ex){
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

    private static String getJsonFile()
    {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(pathToJSON));
            lines.forEach(builder::append);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }

}