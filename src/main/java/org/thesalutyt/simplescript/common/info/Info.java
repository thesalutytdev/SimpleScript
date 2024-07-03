package org.thesalutyt.simplescript.common.info;

import org.thesalutyt.simplescript.interpreter.Interpreter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Info {
    public static final String FILE_FORMAT = ".ssc";
    public static DateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    public static String rootDir = System.getProperty("user.dir") + "/src/main/resources/test_scripts/";
    public static final String DEFAULT_ICON_PATH = "src\\main\\resources\\assets\\simplescript\\static\\img\\logo_v2.png";
    public static final String ANOTHER_ICON_PATH = "src\\main\\resources\\assets\\simplescript\\static\\img\\logo.png";
    public static Interpreter interpreter;
}
