package org.thesalutyt.simplescript.interpreter;

public class InterpreterInstance implements InterpreterResource {
    protected Boolean isActive;
    protected Boolean isDebug = false;
    protected Boolean allowLog = true;
    public InterpreterInstance(String rootDir) {
        this.isActive = true;
    }
    public void close() {
        this.isActive = false;
        System.out.println("Interpreter and interpreter instance closed");
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
