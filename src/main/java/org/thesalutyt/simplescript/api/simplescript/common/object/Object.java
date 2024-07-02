package org.thesalutyt.simplescript.api.simplescript.common.object;

import org.thesalutyt.simplescript.api.simplescript.common.object.instances.ObjectInstance;

public class Object extends ObjectInstance {
    public Object(String name, boolean isFinal, java.lang.Object value) {
        super(name, DefaultTypes.Object, isFinal, value);
    }
    public Object(String name, boolean isFinal, java.lang.Object value, DefaultTypes type) {
        super(name, type, isFinal, value);
    }
}
