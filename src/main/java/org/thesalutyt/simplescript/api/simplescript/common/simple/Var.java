package org.thesalutyt.simplescript.api.simplescript.common.simple;

import org.thesalutyt.simplescript.api.simplescript.KeyWords;
import org.thesalutyt.simplescript.api.simplescript.common.object.DefaultTypes;
import org.thesalutyt.simplescript.api.simplescript.common.object.executable.Method;
import org.thesalutyt.simplescript.api.simplescript.common.variables.Variables;
import org.thesalutyt.simplescript.common.info.Info;
import org.thesalutyt.simplescript.interpreter.Interpreter;
import org.thesalutyt.simplescript.interpreter.scope.Scope;
import org.thesalutyt.simplescript.interpreter.type.TypeChecker;

public class Var extends Scope {
    public static Method create = new Method("create", false, () -> {
        String name = Method.args.get(0).toString();
        String type = Method.args.get(1).toString().toLowerCase();
        String value = Method.args.get(2).toString();
        DefaultTypes var_type;
        switch (type) {
            case "str", "string" -> {
                boolean isCorrect = TypeChecker.checkType(value, DefaultTypes.Str);
                if (isCorrect) {
                    var_type = DefaultTypes.Str;
                } else {
                    throw new RuntimeException("[TypeError] Wrong data type assignment to a variable " + name);
                }
            }
            case "bool", "boolean" -> {
                boolean isCorrect = TypeChecker.checkType(value, DefaultTypes.Bool);
                if (isCorrect) {
                    var_type = DefaultTypes.Bool;
                } else {
                    throw new RuntimeException("[TypeError] Wrong data type assignment to a variable " + name);
                }
            }
            case "int", "integer" -> var_type = DefaultTypes.Int;
            case "float" -> var_type = DefaultTypes.Float;
            case "double" -> var_type = DefaultTypes.Double;
            default -> var_type = DefaultTypes.Object;
        }
    }, Method.args);
    public Var() {
        super("var");
        addMethod(create);
    }
}
