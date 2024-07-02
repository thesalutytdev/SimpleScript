package org.thesalutyt.simplescript.api.simplescript.common.variables;

import org.thesalutyt.simplescript.api.simplescript.common.object.DefaultTypes;
import org.thesalutyt.simplescript.api.simplescript.common.object.Object;
import org.thesalutyt.simplescript.api.simplescript.common.object.ObjectList;
import org.thesalutyt.simplescript.api.simplescript.common.object.instances.VariableInstance;

import java.util.ArrayList;
import java.util.HashMap;

public class Variables extends VariableInstance {
    public String name;
    public boolean isFinal;
    public java.lang.Object value;
    public DefaultTypes type;
    public static HashMap<String, Variables> variables = new HashMap<>();
    public static ArrayList<Variables> list = new ArrayList<>();
    public Variables(String name, boolean isFinal, java.lang.Object value, DefaultTypes type) {
        super(name, type, isFinal, value);
        this.name = name;
        this.value = value;
        this.isFinal = isFinal;
        this.type = type;
        variables.put(name, this);
        list.add(this);
        ObjectList.add(name, new Object(name, isFinal, value, type));
    }
    public static Variables getVar(String name) {
        for (Variables variable : list) {
            if (variable.name.equals(name)) {
                return variable;
            }
        }
        return variables.get(name);
    }
    public void update() {
        variables.remove(this.name);
        variables.put(this.name, this);
    }
}
