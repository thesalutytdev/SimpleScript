package org.thesalutyt.simplescript.common.checker;

import java.util.Objects;

public class Checker {
    public static boolean wordIn(String sentence, String word) {
        String[] line = sentence.split(" ");
        for (String w : line) {
            if (Objects.equals(w, word)) {
                return true;
            } else {
                continue;
            }
        }
        return false;
    }
    public static boolean symbolIn(String line, String symbol) {
        String[] ch_l = line.split("");
        for (String s : ch_l) {
            if (Objects.equals(s, symbol)) {
                return true;
            } else {
                continue;
            }
        }
        return false;
    }
}
