package org.thesalutyt.simplescript.api.simplescript.common.simple;

import org.thesalutyt.simplescript.api.simplescript.KeyWords;
import org.thesalutyt.simplescript.interpreter.scope.Scope;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public class Simple extends Scope {
    public static ArrayList<HashMap<String, HashMap<Method, ArrayList<Object>>>> methods = new ArrayList<>();
    public Simple() {
        super("simple", methods);
        KeyWords.add("simple", methods);
        KeyWords.addScope(this);
    }
    public void print(String text) {
        System.out.println(text);
    }
    public static void putIntoScope() {
        ArrayList<Method> methodsToAdd = new ArrayList<>();
        try {
            Method print = Simple.class.getMethod("print", String.class);
            methodsToAdd.add(print);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        for (Method m : methodsToAdd) {
            HashMap<String, HashMap<Method, ArrayList<Object>>> methodInstance = new HashMap<>();
            HashMap<Method, ArrayList<Object>> methodAttributes = new HashMap<>();
            ArrayList<Object> allAttributes = new ArrayList<>();
            allAttributes.add(m.getParameters());
            methodAttributes.put(m, allAttributes);
            methodInstance.put(m.getName(), methodAttributes);
            methods.add(methodInstance);
        }
        System.out.println("[Debug] Methods added");
    }
}
