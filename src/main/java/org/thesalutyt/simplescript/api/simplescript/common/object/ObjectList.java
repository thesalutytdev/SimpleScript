package org.thesalutyt.simplescript.api.simplescript.common.object;

import org.thesalutyt.simplescript.api.simplescript.common.object.instances.ObjectInstance;

import java.util.HashMap;

public class ObjectList {
    public static HashMap<String, Object> objects = new HashMap<>();
    public static void add(String name, Object obj) {
        objects.put(name, obj);
    }
}
