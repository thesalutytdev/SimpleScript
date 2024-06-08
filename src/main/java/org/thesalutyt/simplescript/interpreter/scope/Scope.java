package org.thesalutyt.simplescript.interpreter.scope;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public class Scope {
    protected ArrayList<HashMap<String, HashMap<Method, ArrayList<Object>>>> methods;
    protected String name;
    public Scope(String name, ArrayList<HashMap<String, HashMap<Method, ArrayList<Object>>>> methods) {
        this.methods = methods;
        this.name = name;
    }
    public void addMethod(HashMap<String, HashMap<Method, ArrayList<Object>>> method) {
        this.methods.add(method);
    }
    public HashMap<String, HashMap<Method, ArrayList<Object>>> getMethodsMap(Integer id) {
        return this.methods.get(id);
    }
    public ArrayList<HashMap<String, HashMap<Method, ArrayList<Object>>>> getMethods() {
        return this.methods;
    }
    public String getName() {
        return this.name;
    }
    public Scope getScope() {
        return this;
    }
}
