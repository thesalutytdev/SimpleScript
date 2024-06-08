package org.thesalutyt.simplescript.common.file;

import org.thesalutyt.simplescript.common.info.Info;

public class FileManager {
    public static void import_file(String path) {
        if (path.endsWith(Info.FILE_FORMAT)) {
            System.out.println("Importing file: " + path);
        }
        else {
            return;
        }
    }
}
