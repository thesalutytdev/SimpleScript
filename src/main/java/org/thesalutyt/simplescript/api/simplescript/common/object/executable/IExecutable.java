package org.thesalutyt.simplescript.api.simplescript.common.object.executable;

import org.thesalutyt.simplescript.api.simplescript.common.object.DefaultTypes;
import org.thesalutyt.simplescript.api.simplescript.common.object.instances.ExecutableInstance;

public class IExecutable extends ExecutableInstance {
    private Runnable value;
    public IExecutable(String name, DefaultTypes type, boolean isFinal, Runnable value) {
        super(name, type, isFinal, value);
        this.value = value;
    }

    public Runnable getValue() {
        return value;
    }
    protected void execute() {
        value.run();
    }
}
