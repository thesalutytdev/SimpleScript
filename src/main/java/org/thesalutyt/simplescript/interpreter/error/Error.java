package org.thesalutyt.simplescript.interpreter.error;

public class Error {
    public static enum ErrorType {
        UNKNOWN_WORD("UnknownWord"),
        ATTRIBUTE_ERROR("AttributeError"),
        TYPE_ERROR("TypeError");

        ErrorType(String typeError) {
        }
    }
    public static void exit(int exit_code) {
        System.exit(exit_code);
    }
}
