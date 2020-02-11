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



    public static void main(String[] args) {
        String wikiFile = parseFile("data/wiki.html");
        Document doc = Jsoup.parse(wikiFile);
        for(int i = 0; i < doc.select("tbody").eq(3).select("tr").size(); i++) {
            Elements stationElements = doc.select("tbody").eq(3).select("tr").eq(i).select("td").eq(1).select("span").eq(0);
            System.out.println(stationElements.text());
            if (doc.select("tbody").eq(3).select("tr").eq(i).select("td").eq(0).select("span").size() > 3){
                Elements secondLineElement = doc.select("tbody").eq(3).select("tr").eq(i).select("td").eq(0).select("span").eq(2);
                System.out.println(secondLineElement.text());
            }
            Elements lineElements = doc.select("tbody").eq(3).select("tr").eq(i).select("td").eq(0).select("span").eq(0);
            System.out.println(lineElements.text());

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

    public static JSONObject buildJson(String[] line, JSONArray stations) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Лондон");
        jsonObject.put("main", "Солнечно");
        jsonObject.put("description", "Мороз трескучий, На небе ни единой тучи");

        return jsonObject;
    }

}