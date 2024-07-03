package org.thesalutyt.simplescript.interpreter.start;

import org.thesalutyt.simplescript.api.simplescript.lang.ReservedWord;

public class WordAdder {
    public WordAdder() {
        ReservedWord.addReservedWord("var");
        ReservedWord.addReservedWord("for");
        ReservedWord.addReservedWord("while");
        ReservedWord.addReservedWord("object");
        ReservedWord.addReservedWord("const");
        ReservedWord.addReservedWord("run");
        ReservedWord.addReservedWord("func");
    }
}
