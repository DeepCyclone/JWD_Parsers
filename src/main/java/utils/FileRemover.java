package utils;

import java.io.File;

public class FileRemover {
    public static void removeFileByPath(String path){
        File a = new File(path);
        a.delete();
    }
}
