package org.thesalutyt.simplescript.interpreter.source;

public enum SourceType {
    SCRIPT("SCRIPT"),
    CONSOLE("CMD"),
    FILE("FILE"),
    OTHER("OTHER"),
    EXECUTABLE("EXECUTABLE");
    private String type;
    SourceType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
}
