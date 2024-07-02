package org.thesalutyt.simplescript.api.simplescript.common.object.instances;

import org.thesalutyt.simplescript.api.simplescript.common.object.DefaultTypes;
import org.thesalutyt.simplescript.api.simplescript.common.object.ObjectList;

public class ObjectInstance {
    protected DefaultTypes type;
    public final String name;
    protected boolean isFinal;
    public Object value;
    public ObjectInstance(String name, DefaultTypes type, boolean isFinal, Object value) {
        this.name = name;
        this.type = type;
        this.isFinal = isFinal;
        this.value = value;
    }
    public String getName() {
        return this.name;
    }
    public DefaultTypes getType() {
        return this.type;
    }
    public Object getValue() {
        return this.value;
    }
    public boolean isFinal() {
        return this.isFinal;
    }
    public void set(String field, Object value) {
        if (isFinal) {
            throw new RuntimeException("Object is final!");
        } else {
            switch (field) {
                case "type" -> this.type = (DefaultTypes) value;
                case "name" -> throw new RuntimeException("Name is final field!");
                case "isFinal" -> this.isFinal = Boolean.parseBoolean(value.toString());
                case "value" -> this.value = value;
                default -> throw new RuntimeException("Invalid field " + field);
            }
        }
    }
}
