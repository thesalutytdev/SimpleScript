package org.thesalutyt.simplescript.interpreter.error;

public class Error {
    public static enum ErrorType {
        UNKNOWN_WORD("UnknownWord"),
        ATTRIBUTE_ERROR("AttributeError"),
        TYPE_ERROR("TypeError"),
        SYNTAX_ERROR("SyntaxError");

        ErrorType(String typeError) {
        }
    }
    public static void exit(int exit_code) {
        System.exit(exit_code);
    }
    public static void sendError(String error) {
        throw new RuntimeException(error);
    }
    public static void error(Error.ErrorType type, String error) {
        throw new RuntimeException(String.format("[%s] %s", type, error));
    }
}
