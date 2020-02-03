import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String lentaFile = parseFile("data/lenta.html");
        Document doc = Jsoup.parse(lentaFile);
        Elements elements = doc.select("img");
        elements.stream()
                .map(element -> element.attributes().get("src"))
                .forEach(
                    s -> {
                        try {
                            if (s.split("/")[0].equals("https:")) {
                                downloadFile(s);
                            } else {
                                downloadFile("https:" + s);
                            }
                        }catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                );
    }

    public static String parseFile(String path){
        StringBuilder builder = new StringBuilder();
        try
        {
            List<String> lines = Files.readAllLines(Paths.get(path));
            lines.forEach(line -> builder.append(line + "\n"));
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return builder.toString();
    }

    public static void downloadFile(String path) throws IOException {
        URL url = new URL(path);

        InputStream inputStream = url.openStream();
        Files.copy(inputStream, new File("data/img/" + path.split("/")[path.split("/").length - 1]).toPath());

        inputStream.close();
    }
}
