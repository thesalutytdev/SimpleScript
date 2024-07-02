package org.thesalutyt.simplescript.api.simplescript.lang.type;

import org.thesalutyt.simplescript.api.simplescript.common.object.DefaultTypes;

public class Type {
    public static DefaultTypes getType(String type) {
        return switch (type) {
            case "string", "str" -> DefaultTypes.Str;
            case "double" -> DefaultTypes.Double;
            case "boolean" -> DefaultTypes.Bool;
            case "any" -> DefaultTypes.Object;
            case "void" -> DefaultTypes.Void;
            case "null" -> DefaultTypes.Null;
            case "int" -> DefaultTypes.Int;
            case "float" -> DefaultTypes.Float;
            default -> null;
        };
    }
}
