package org.thesalutyt.simplescript.api.simplescript.common.variables;

import org.thesalutyt.simplescript.api.simplescript.common.object.DefaultTypes;
import org.thesalutyt.simplescript.api.simplescript.common.object.instances.VariableInstance;

import java.util.HashMap;

public class Variables extends VariableInstance {
    public String name;
    public boolean isFinal;
    public Object value;
    public DefaultTypes type;
    public static HashMap<String, Variables> variables = new HashMap<>();
    public Variables(String name, boolean isFinal, Object value, DefaultTypes type) {
        super(name, type, isFinal, value);
        this.name = name;
        this.value = value;
        this.isFinal = isFinal;
        this.type = type;
        variables.put(name, this);
    }
    public static Variables getVar(String name) {
        return variables.get(name);
    }
    public void update() {
        variables.remove(this.name);
        variables.put(this.name, this);
    }
}
