package org.thesalutyt.simplescript.api.simplescript.common.simple;

import org.thesalutyt.simplescript.api.simplescript.common.object.executable.Method;
import org.thesalutyt.simplescript.interpreter.InterpreterInstance;
import org.thesalutyt.simplescript.interpreter.scope.Scope;

public class Lang extends Scope {
    public Method setDebug = new Method("setDebug", false, () -> {
        InterpreterInstance.isDebug = Boolean.parseBoolean(Method.args.get(0).toString());
    }, Method.args);
    public Method allowLogs = new Method("allowLogs", false, () -> {
        InterpreterInstance.allowLog = Boolean.parseBoolean(Method.args.get(0).toString());
    }, Method.args);
    public Lang() {
        super("lang");
        addMethod(setDebug);
        addMethod(allowLogs);
    }
}
