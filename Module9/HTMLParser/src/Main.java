import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String lentaFile = parseFile("data/lenta.html");
        Document doc = Jsoup.parse(lentaFile);
        Elements elements = doc.select("img");
        elements.forEach(element -> {
            element.attributes().get("src").;
        });
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
        URLConnection connection = url.openConnection(); //устанавливаем соединение

//получаем OutputStream, чтобы писать в него данные запроса
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(1);
        outputStream.flush();

//получаем InputStream, чтобы читать из него данные ответа
        InputStream inputStream = connection.getInputStream();
        Files.copy(inputStream, new File("data/" + path.split("/")[path.split("/").length - 1]).toPath());
    }
}
