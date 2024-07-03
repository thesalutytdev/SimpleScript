package org.thesalutyt.simplescript.api.simplescript.lang.object.executable;

import org.thesalutyt.simplescript.api.simplescript.common.object.DefaultTypes;
import org.thesalutyt.simplescript.api.simplescript.common.object.instances.ExecutableInstance;
import org.thesalutyt.simplescript.common.info.Info;
import org.thesalutyt.simplescript.interpreter.source.SourceType;
import org.thesalutyt.simplescript.api.simplescript.common.object.Object;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Executable extends ExecutableInstance {
    ArrayList<java.lang.Object> args = new ArrayList<>();
    public Executable(String name, boolean isFinal, Object value, ArrayList<java.lang.Object> args) {
        super(name, DefaultTypes.Object, isFinal, value);
        this.args = args;
        ExecutableList.functions.put(name, this);
    }
    public void run() throws FileNotFoundException {
        Info.interpreter.execute(SourceType.EXECUTABLE, this.executable.toString());
    }
    public void run(ArrayList<java.lang.Object> args) throws FileNotFoundException {
        this.args = args;
        Info.interpreter.execute(SourceType.EXECUTABLE, this.executable.toString());
    }
}
