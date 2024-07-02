package org.thesalutyt.simplescript.api.simplescript.common.object.executable;

import org.thesalutyt.simplescript.api.simplescript.common.object.DefaultTypes;

import java.util.ArrayList;

public class Method extends IExecutable {
    public static ArrayList<Object> args = new ArrayList<>();
    public Method(String name, boolean isFinal, Runnable value, ArrayList<Object> args) {
        super(name, DefaultTypes.Object, isFinal, value);
    }
    public void execute(ArrayList<Object> args) {
        Method.args = args;
        this.getValue().run();
    }
}