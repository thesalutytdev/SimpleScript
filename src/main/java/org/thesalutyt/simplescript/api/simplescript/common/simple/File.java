package org.thesalutyt.simplescript.api.simplescript.common.simple;

import org.thesalutyt.simplescript.api.simplescript.common.object.executable.Method;
import org.thesalutyt.simplescript.common.info.Info;
import org.thesalutyt.simplescript.interpreter.scope.Scope;

import java.io.FileNotFoundException;

public class File extends Scope {
    public Method run = new Method("run", false, () -> {
        try {
            Info.interpreter.import_file(Method.args.get(0).toString());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }, Method.args);
    public File() {
        super("file");
        addMethod(run);
    }
}
