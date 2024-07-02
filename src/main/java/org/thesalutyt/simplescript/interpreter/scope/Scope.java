package org.thesalutyt.simplescript.interpreter.scope;

import org.thesalutyt.simplescript.api.simplescript.common.object.executable.Method;
import org.thesalutyt.simplescript.api.simplescript.common.simple.Simple;

import java.util.ArrayList;
import java.util.HashMap;

public class Scope {
    public HashMap<String, Method> methods = new HashMap<>();
    public ArrayList<Scope> children = new ArrayList<>();
    public Scope parent;
    public String name;
    public Scope(String name) {
        this.name = name;
    }
    public void addMethod(Method method) {
        methods.put(method.name, method);
    }
    public void addChild(Scope scope) {
        children.add(scope);
        scope.parent = this;
    }
    public void removeChild(Scope scope) {
        children.remove(scope);
        scope.parent = null;
    }
    public Scope getParent() {
        return parent;
    }
    public HashMap<String, Method> getMethods() {
        return methods;
    }
    public ArrayList<Scope> getChildren() {
        return children;
    }
    public String getName() {
        return name;
    }
    public void removeMethod(String name) {
        methods.remove(name);
    }
    public Method getMethod(String name) {
        return methods.get(name);
    }
}
