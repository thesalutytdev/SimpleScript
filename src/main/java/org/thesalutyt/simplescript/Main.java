package org.thesalutyt.simplescript;

import org.thesalutyt.simplescript.common.info.Info;
import org.thesalutyt.simplescript.interpreter.Interpreter;
import org.thesalutyt.simplescript.interpreter.source.SourceType;
import org.thesalutyt.simplescript.interpreter.type.TypeConverter;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Interpreter interpreter = new Interpreter();
        interpreter.import_file(Info.rootDir + "script.ssc");
    }
}