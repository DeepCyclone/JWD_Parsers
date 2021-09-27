package by.epamtc.jwd.validation;

import javax.servlet.http.Part;

public class ExtensionChecker {
    public static boolean isCorrectFile(Part part, String extension) {
        boolean state = false;
        String partName = part.getSubmittedFileName();
        int partSize = partName.length();
        if(partSize >= 4 && partName.endsWith(extension)){
            state = true;
        }
        return state;
    }
}
