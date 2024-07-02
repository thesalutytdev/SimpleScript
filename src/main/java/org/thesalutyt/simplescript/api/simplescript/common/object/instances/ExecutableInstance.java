package org.thesalutyt.simplescript.api.simplescript.common.object.instances;

import org.thesalutyt.simplescript.api.simplescript.common.object.DefaultTypes;

import java.awt.*;
import java.lang.reflect.Method;

public class ExecutableInstance extends ObjectInstance {
    protected Boolean executable = true;
    protected Runnable method;
    public ExecutableInstance(String name, DefaultTypes type, boolean isFinal, Object value) {
        super(name, type, isFinal, value);
    }
    public ExecutableInstance(String name, DefaultTypes type, boolean isFinal, Object value, Boolean executable) {
        super(name, type, isFinal, value);
        this.executable = executable;
    }

    public void setExecutable(boolean executable) {
        this.executable = executable;
    }

    public void setMethod(Runnable method) {
        this.method = method;
    }

    public boolean isExecutable() {
        return executable;
    }
}
