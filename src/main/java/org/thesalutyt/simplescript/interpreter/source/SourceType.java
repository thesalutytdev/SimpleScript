package org.thesalutyt.simplescript.interpreter.source;

public enum SourceType {
    SCRIPT("SCRIPT"),
    CONSOLE("CMD"),
    FILE("FILE");
    private String type;
    SourceType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
}
