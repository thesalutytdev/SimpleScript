package org.thesalutyt.simplescript.interpreter.type;

import java.util.Objects;

public class TypeConverter {
    public static int toInt(String value) {
        String[] literals = value.split("");
        Integer final_number = 0;
        Integer now_at = 0;
        for (int i = 0; i < literals.length / 2; i++) {
            String j = literals[i];
            literals[i] = literals[literals.length - i - 1];
            literals[literals.length - i - 1] = j;
        }
        for (int i = 0; i < literals.length; i++) {
            if (!Objects.equals(Integer.parseInt(literals[i]), null)) {
                if (Objects.equals(now_at, 0)) {
                    final_number += Integer.parseInt(literals[i]);
                    now_at++;
                } else {
                    StringBuilder _str = new StringBuilder();
                    _str.append(literals[i]);
                    for (int k = 0; k<now_at; k++) {
                        _str.append("0");
                    }
                    final_number += Integer.parseInt(_str.toString());
                    now_at++;
                }
            }
        }
        return final_number;
    }
    public static float toFloat(String value) {
        Integer int_val = toInt(value);
        String _f = String.format("%s.1", int_val);
        return Float.parseFloat(_f);
    }
}
