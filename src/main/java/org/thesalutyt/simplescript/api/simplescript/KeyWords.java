package org.thesalutyt.simplescript.api.simplescript;

import org.thesalutyt.simplescript.interpreter.error.Error;
import org.thesalutyt.simplescript.interpreter.scope.Scope;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public class KeyWords {
    public static HashMap<String, HashMap<String, HashMap<ArrayList<Object>, Method>>> functions = new HashMap<>();
    public static ArrayList<HashMap<String, HashMap<Method, ArrayList<Object>>>> methods = new ArrayList<>();
    public static HashMap<String, Scope> scopes = new HashMap<>();
    public static void add(String name, HashMap<String, HashMap<String, HashMap<ArrayList<Object>, Method>>> attributes) {}
    public static void addScope(String name, Scope scope) {
        scopes.put(name, scope);
    }
    public static boolean analyze(String code) {
        String[] line = code.split(" >> ");
        String scope_name = code.split("\\.")[0].trim();
        String m_name = line[0].split("\\.")[1].trim();
        String m_args = line[1].trim();
        try {
            String[] args = m_args.split(",");
            ArrayList<Object> list = new ArrayList<>();
            for (String arg : args) {
                list.add(arg.trim().replace(";", ""));
            }
            scopes.get(scope_name).getMethod(m_name).execute(list);
            return true;
        } catch (Exception e) {
            Error.sendError(e.getMessage());
            return false;
        }
    }
    public static boolean analyze(String[] line) {
        String scope_name = line[0].split("\\.")[0].trim();
        String m_name = line[0].split("\\.")[1].trim();
        String m_args = line[1].trim();
        try {
            String[] args = m_args.split(",");
            ArrayList<Object> list = new ArrayList<>();
            for (String arg : args) {
                list.add(arg.trim().replace(";", ""));
            }
            scopes.get(scope_name).getMethod(m_name).execute(list);
            return true;
        } catch (Exception e) {
            Error.sendError(e.getMessage());
            return false;
        }
    }
}
