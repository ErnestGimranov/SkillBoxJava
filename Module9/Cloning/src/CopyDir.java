import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class CopyDir{

    public static boolean copyDir(String sourceDirName, String destDirName){
        File srcFile = new File(sourceDirName);
        File dstFile = new File(destDirName);
        if(srcFile.exists() && srcFile.isDirectory() && !dstFile.exists()){
            dstFile.mkdir();
            File nextSrcFile;
            String nextSrcFileName, nextDstFileName;
            for(String fileName : srcFile.list()){
                nextSrcFileName = srcFile.getAbsolutePath() + File.separator + fileName;
                nextDstFileName = dstFile.getAbsolutePath() + File.separator + fileName;
                nextSrcFile = new File(nextSrcFileName);
                if(nextSrcFile.isDirectory()){
                    copyDir(nextSrcFileName, nextDstFileName);
                }else{
                    copyFile(nextSrcFileName, nextDstFileName);
                }
            }
            return true;
        }{
            System.out.println("Введенный каталог уже существет, попробуйте снова!");
            return false;
        }
    }

    private static void copyFile(String sourceDirName, String destDirName){
        File srcFile = new File(sourceDirName);
        File dstFile = new File(destDirName);
        if(srcFile.exists() && srcFile.isFile()){
            try {
                Files.copy(srcFile.toPath(), dstFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }catch (Exception ex){
                System.out.println(ex.getStackTrace());
            }
        }
    }
}
