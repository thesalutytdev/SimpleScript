package org.thesalutyt.simplescript.api.simplescript;

import org.thesalutyt.simplescript.interpreter.scope.Scope;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public class KeyWords {
    public static HashMap<String, HashMap<Method, ArrayList<Object>>> functions = new HashMap<>();
    public static ArrayList<HashMap<String, HashMap<Method, ArrayList<Object>>>> methods = new ArrayList<>();
    public static ArrayList<Scope> scopes = new ArrayList<>();
    public static void add(String name, ArrayList<HashMap<String, HashMap<Method, ArrayList<Object>>>> attributes) {}
    public static void addScope(Scope scope) {
        scopes.add(scope);
    }
}
