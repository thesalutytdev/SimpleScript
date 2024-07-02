package org.thesalutyt.simplescript.interpreter.type;

import org.thesalutyt.simplescript.api.simplescript.common.object.DefaultTypes;
import org.thesalutyt.simplescript.common.checker.Checker;

import java.util.Objects;

public class TypeChecker {
    public static boolean checkType(String value_, DefaultTypes expectedType) {
        switch (expectedType) {
            case Str:
                if (value_.startsWith("\"") && value_.endsWith("\"")) {
                    return true;
                }
                return false;
            case Bool:
                return Objects.equals(value_, "true") || Objects.equals(value_, "false");
            case Int:
                if (checkType(value_, DefaultTypes.Str) || checkType(value_, DefaultTypes.Bool)) {
                    return false;
                }
                try {
                    int test = Integer.parseInt(value_);
                    System.out.println(test);
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage());
                }
                return true;
            case Float:
                if (checkType(value_, DefaultTypes.Str) || checkType(value_, DefaultTypes.Bool)) {
                    return false;
                }
                try {
                    float test = Float.parseFloat(value_);
                    System.out.println(test);
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage());
                }
                return true;
            case Double:
                if (checkType(value_, DefaultTypes.Str) || checkType(value_, DefaultTypes.Bool)) {
                    return false;
                }
                try {
                    double test = Double.parseDouble(value_);
                    System.out.println(test);
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage());
                }
                return true;
            default:
                return false;
        }
    }
}

