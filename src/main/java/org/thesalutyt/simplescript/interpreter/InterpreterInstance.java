package org.thesalutyt.simplescript.interpreter;

import org.thesalutyt.simplescript.api.simplescript.common.loop.LoopType;
import org.thesalutyt.simplescript.api.simplescript.common.object.Object;
import org.thesalutyt.simplescript.api.simplescript.common.object.instances.ObjectInstance;

public class InterpreterInstance implements InterpreterResource {
    protected Boolean isActive;
    public static Boolean isDebug = false;
    public static Boolean allowLog = true;
    public Boolean inLoop = false;
    public String currentLoop;
    public String rootDir;
    public ObjectInstance currentObject;
    public LoopType currentLoopType = LoopType.NONE;
    public InterpreterInstance(String rootDir) {
        this.isActive = true;
        this.rootDir = rootDir;
    }
    public void close() {
        this.isActive = false;
        System.out.println("Interpreter and interpreter instance closed");
    }
    public static void setDebug(boolean value) {
        isDebug = value;
    }
    public static void allowLog(boolean value) {
        allowLog = value;
    }
    @Override
    public String getResourceId() {
        return "InterpreterInstance";
    }

    @Override
    public Boolean isActive() {
        return isActive;
    }
}
