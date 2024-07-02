package org.thesalutyt.simplescript.api.simplescript.common.simple;

import org.thesalutyt.simplescript.api.simplescript.KeyWords;
import org.thesalutyt.simplescript.interpreter.scope.Scope;

import org.thesalutyt.simplescript.api.simplescript.common.object.executable.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Simple extends Scope {
    public Method print = new Method("print", false, () -> {
        for (Object arg : Method.args) {
            System.out.println(arg);
        }
    }, Method.args);
    public Method sum = new Method("sum", false, () -> {
        int sum = 0;
        for (Object arg : Method.args) {
            sum += Integer.parseInt(arg.toString());
        }
        System.out.println(sum);}, Method.args
    );

    public Simple() {
        super("simple");
        this.addMethod(print);
        this.addMethod(sum);
    }
    public void printF() {
        System.out.println("text");
    }
    public static void putIntoScope() {
        ArrayList<java.lang.reflect.Method> methodsToAdd = new ArrayList<>();
        try {
            java.lang.reflect.Method print = Simple.class.getMethod("print", String.class);
            methodsToAdd.add(print);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        for (java.lang.reflect.Method m : methodsToAdd) {
//            HashMap<String, HashMap<Method, ArrayList<Object>>> methodInstance = new HashMap<>();
//            HashMap<Method, ArrayList<Object>> methodAttributes = new HashMap<>();
//            ArrayList<Object> allAttributes = new ArrayList<>();
//            allAttributes.add(m.getParameters());
//            methodAttributes.put(m, allAttributes);
//            methodInstance.put(m.getName(), methodAttributes);
//            methods.put(m.getName(), methodInstance);
        }
        System.out.println("[Debug] Methods added");
    }
}
