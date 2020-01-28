import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;

public class FileSizeReader {

    private long size;

    FileSizeReader(Path path) throws IOException {
        this.size = Files.walk(path)
                .map(Path::toFile)
                .filter(File::isFile)
                .mapToLong(File::length)
                .sum();
    }
    public String getDirectorySize(){
        String[] units = new String[]{ "B", "KB", "MB", "GB", "TB" };
        int unitIndex = 0;
        long tmpSize = this.size;
        while (tmpSize > 1024){
            tmpSize /= 1024;
            unitIndex++;
        }
        double unitValue = 1 << (unitIndex *  10);
        String readableSize = new DecimalFormat("#,##0.#")
                .format(this.size/unitValue) + " "
                + units[unitIndex];
        return readableSize;
    }
}
