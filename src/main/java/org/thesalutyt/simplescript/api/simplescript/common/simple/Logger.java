package org.thesalutyt.simplescript.api.simplescript.common.simple;

import org.thesalutyt.simplescript.api.simplescript.KeyWords;
import org.thesalutyt.simplescript.api.simplescript.common.object.executable.Method;
import org.thesalutyt.simplescript.api.simplescript.common.variables.Variables;
import org.thesalutyt.simplescript.common.time.Time;
import org.thesalutyt.simplescript.interpreter.scope.Scope;

import static org.thesalutyt.simplescript.api.simplescript.common.object.executable.Method.args;

public class Logger extends Scope {
    public static Method log = new Method("log", false, () -> {
        for (Object arg : Method.args) {
            if (arg.toString().startsWith("$")) {
                System.out.println("[" + Time.getDate() + "] " + Variables.getVar(arg.toString().replace("$", "").trim()).value);
            } else {
                System.out.println("[" + Time.getDate() + "] " + arg);
            }
        }
    }, args);
    public Logger() {
        super("log");
        addMethod(log);
    }
}
