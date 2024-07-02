package org.thesalutyt.simplescript.api.simplescript.lang;

import java.util.ArrayList;

public class ReservedWord {
    public static ArrayList<String> reservedWords = new ArrayList<>();
    public static boolean isReservedWord(String word) {
        return reservedWords.contains(word);
    }

    public static void addReservedWord(String word) {
        reservedWords.add(word);
    }

    public static void removeReservedWord(String word) {
        reservedWords.remove(word);
    }

    public static void clearReservedWords() {
        reservedWords.clear();
    }
}
