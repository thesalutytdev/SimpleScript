package org.thesalutyt.simplescript.api.simplescript.lang;

import org.thesalutyt.simplescript.api.simplescript.KeyWords;
import org.thesalutyt.simplescript.api.simplescript.common.line.ILine;
import org.thesalutyt.simplescript.api.simplescript.common.loop.LoopType;
import org.thesalutyt.simplescript.api.simplescript.common.object.DefaultTypes;
import org.thesalutyt.simplescript.api.simplescript.common.object.Object;
import org.thesalutyt.simplescript.api.simplescript.common.object.ObjectList;
import org.thesalutyt.simplescript.api.simplescript.common.variables.Variables;
import org.thesalutyt.simplescript.api.simplescript.lang.object.executable.Executable;
import org.thesalutyt.simplescript.api.simplescript.lang.object.executable.ExecutableList;
import org.thesalutyt.simplescript.api.simplescript.lang.type.Type;
import org.thesalutyt.simplescript.common.info.Info;
import org.thesalutyt.simplescript.interpreter.Interpreter;
import org.thesalutyt.simplescript.interpreter.InterpreterInstance;
import org.thesalutyt.simplescript.interpreter.error.Error;
import org.thesalutyt.simplescript.interpreter.source.SourceType;
import org.thesalutyt.simplescript.interpreter.type.TypeChecker;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class LangAnalyzer {
    protected Interpreter i;
    protected InterpreterInstance instance;
    public LangAnalyzer(Interpreter i) {
        this.i = i;
        this.instance = i.getInstance();
    }
    public boolean analyze(ILine line) throws FileNotFoundException {
        String[] code = line.code.split(" >> ");
        if (firstStep(code)) {
            for (int i = 0; i < code.length; i++) {
                if (code[i].trim().startsWith("$")) {
                    code = varAnalyzer(code, i);
                    // System.out.println(code);
                }
            }
            return deep(code);
        } else {
            Error.error(Error.ErrorType.SYNTAX_ERROR, "Unexpected usage of '" + code[0] + "' as key word");
            return false;
        }
    }
    protected boolean firstStep(String[] line) {
        if (line.length >= 2) {
            return true;
        } else {
            return false;
        }
    }
    protected boolean deep(String[] code) throws FileNotFoundException {
        String firstWord = code[0];
        if (ReservedWord.isReservedWord(firstWord)) {
            switch (firstWord) {
                case "for": {
                    instance.inLoop = true;
                    instance.currentLoop = firstWord;
                    instance.currentLoopType = LoopType.FOR;
                    break;
                }
                case "while": {
                    instance.inLoop = true;
                    instance.currentLoop = firstWord;
                    instance.currentLoopType = LoopType.WHILE;
                    break;
                }
                case "func": {
                    String otherSegment = code[1].trim();
                    String funcName = otherSegment.split(":")[0].trim();
                    String returnType = otherSegment.split(":")[1].trim();
                    String[] funcArgs = otherSegment.split(":")[2].split(",");
                    String funcBody = otherSegment.split(":")[3].trim();
                    ArrayList<java.lang.Object> argL = new ArrayList<>(Arrays.asList(funcArgs));
                    Executable fExObj = new Executable(funcName, false, new Object(funcName, false, funcBody), argL);
                    if (InterpreterInstance.isDebug) {
                        System.out.println("Func created: " + funcName);
                    }
                    break;
                }
                case "var": {
                    String otherSegment = code[1].trim();
                    String rawType = otherSegment.split(":")[0].trim();
                    String varName = otherSegment.split(":")[1].split("=")[0].trim();
                    String rawValue = otherSegment.split("=")[1].trim();
                    String varValue = valueAnalyzer(rawValue);
                    // System.out.println(varValue);
                    DefaultTypes varType = Type.getType(rawType);
                    assert varType != null;
                    if (!TypeChecker.checkType(varValue, varType)) {
                        Error.error(Error.ErrorType.SYNTAX_ERROR, "Var value does not match " + varType + " type");
                        return false;
                    }
                    if (varName.contains(" ")) {
                        Error.error(Error.ErrorType.SYNTAX_ERROR, "Variable name cannot contain spaces");
                        return false;
                    } else {
                        Variables var = new Variables(varName, false, varValue, varType);
                        var.update();
                        if (InterpreterInstance.isDebug) {
                            System.out.println("Var created: " + varName + " = " + varValue + " type: " + varType);
                        }
                    }
                    break;
                }
                case "const": {
                    String otherSegment = code[1].trim();
                    String rawType = otherSegment.split(":")[0].trim();
                    String varName = otherSegment.split(":")[1].split("=")[0].trim();
                    String rawValue = otherSegment.split("=")[1].trim();
                    String varValue = valueAnalyzer(rawValue);
                    DefaultTypes varType = Type.getType(rawType);
                    assert varType != null;
                    if (!TypeChecker.checkType(varValue, varType)) {
                        Error.error(Error.ErrorType.SYNTAX_ERROR, "Constant value does not match " + varType + " type");
                        return false;
                    }
                    if (varName.contains(" ")) {
                        Error.error(Error.ErrorType.SYNTAX_ERROR, "Constant name cannot contain spaces");
                        return false;
                    } else {
                        Variables var = new Variables(varName, true, varValue, varType);
                        var.update();
                        if (InterpreterInstance.isDebug) {
                            System.out.println("Constant created: " + varName + " = " + varValue + " type: " + varType);
                        }
                    }
                    break;
                }
                case "object": {
                    int objWordId = 0;
                    for (int i = 0; i < code.length; i++) {
                        if (Objects.equals(code[i], "object")) {
                            objWordId = i;
                        }
                    }
                    String name = code[objWordId + 1].split("=")[0].trim();
                    String rawValue = code[objWordId + 1].split("=")[1].trim();
                    String value = valueAnalyzer(rawValue);
                    // System.out.println(name);
                    // System.out.println(Arrays.toString(code));
                    // System.out.println(value);
                    // System.out.println(ObjectList.objects);
                    if (name.contains(" ")) {
                        Error.error(Error.ErrorType.SYNTAX_ERROR, "Object name cannot contain spaces");
                        return false;
                    }
                    if (ObjectList.objects.get(name) != null) {
                        instance.currentObject = ObjectList.objects.get(name);
                        if (ObjectList.objects.get(name).isFinal()) {
                            Error.error(Error.ErrorType.SYNTAX_ERROR, "Object '" + name + "' is final");
                            return false;
                        }
                        if (!TypeChecker.checkType(value, instance.currentObject.getType())) {
                            Error.error(Error.ErrorType.SYNTAX_ERROR, "Object value does not match " + instance.currentObject.getType() + " type");
                            return false;
                        } else {
                            instance.currentObject.value = value;
                            if (InterpreterInstance.isDebug) {
                                System.out.println("Object changed: " + name + " = " + value + " type: " + instance.currentObject.getType());
                            }
                            return true;
                        }
                    } else {
                        Error.error(Error.ErrorType.SYNTAX_ERROR, "Object '" + name + "' does not exist");
                        return false;
                    }
                }
                case "run": {
                    if (InterpreterInstance.isDebug) {
                        System.out.println("Running func: " + Arrays.toString(code));
                    }
                    String otherSegment = code[1].trim();
                    String funcName = otherSegment.split(":")[0].trim();
                    String rawArgs = otherSegment.split(":")[1].trim();
                    String[] args = rawArgs.split(",");
                    for (int i = 0; i < args.length; i++) {
                        args[i] = args[i].trim();
                        args[i] = args[i].replace("$", "").replace(";", "");
                    }
                    ArrayList<java.lang.Object> argL = new ArrayList<>(Arrays.asList(args));
                    ExecutableList.functions.get(funcName).run(argL);
                    return true;
                }
                default:
                    return false;
            }
        } else {
            return KeyWords.analyze(code);
        }
        return true;
    }
    public String[] varAnalyzer(String[] code, int id) {
        code[id] = (String) Variables.getVar(code[id].trim().replace("$", "").replace(";", "")).value;
        System.out.println(code[id]);
        return code;
    }
    public String valueAnalyzer(String raw) {
        if (raw.contains("+")) {
            String[] split = raw.split("\\+");
            String v0 = split[0].trim();
            String v1 = split[1].trim();
            if (TypeChecker.checkType(v0, DefaultTypes.Int) && TypeChecker.checkType(v1, DefaultTypes.Int)) {
                int sum = Integer.parseInt(v0) + Integer.parseInt(v1);
                return String.valueOf(sum);
            } else if (TypeChecker.checkType(v0, DefaultTypes.Float) && TypeChecker.checkType(v1, DefaultTypes.Float)) {
                return String.valueOf(Float.parseFloat(v0) + Float.parseFloat(v1));
            } else if (TypeChecker.checkType(v0, DefaultTypes.Double) && TypeChecker.checkType(v1, DefaultTypes.Double)) {
                return String.valueOf(Double.parseDouble(v0) + Double.parseDouble(v1));
            } else if (TypeChecker.checkType(v0, DefaultTypes.Str) && TypeChecker.checkType(v1, DefaultTypes.Str)) {
                return String.valueOf(v0 + v1);
            }
        } else if (raw.contains("-")) {
            String[] split = raw.split("-");
            String v0 = split[0].trim();
            String v1 = split[1].trim();
            if (TypeChecker.checkType(v0, DefaultTypes.Int) && TypeChecker.checkType(v1, DefaultTypes.Int)) {
                return String.valueOf(Integer.parseInt(v0) - Integer.parseInt(v1));
            } else if (TypeChecker.checkType(v0, DefaultTypes.Float) && TypeChecker.checkType(v1, DefaultTypes.Float)) {
                return String.valueOf(Float.parseFloat(v0) - Float.parseFloat(v1));
            } else if (TypeChecker.checkType(v0, DefaultTypes.Double) && TypeChecker.checkType(v1, DefaultTypes.Double)) {
                return String.valueOf(Double.parseDouble(v0) - Double.parseDouble(v1));
            } else {
                Error.error(Error.ErrorType.SYNTAX_ERROR, "Subtraction operation is not available for " + v0 + " and " + v1 + " types");
                return null;
            }
        } else if (raw.contains("*")) {
            String[] split = raw.split("\\*");
            String v0 = split[0].trim();
            String v1 = split[1].trim();
            if (TypeChecker.checkType(v0, DefaultTypes.Int) && TypeChecker.checkType(v1, DefaultTypes.Int)) {
                return String.valueOf(Integer.parseInt(v0) * Integer.parseInt(v1));
            } else if (TypeChecker.checkType(v0, DefaultTypes.Float) && TypeChecker.checkType(v1, DefaultTypes.Float)) {
                return String.valueOf(Float.parseFloat(v0) * Float.parseFloat(v1));
            } else if (TypeChecker.checkType(v0, DefaultTypes.Double) && TypeChecker.checkType(v1, DefaultTypes.Double)) {
                return String.valueOf(Double.parseDouble(v0) * Double.parseDouble(v1));
            } else {
                Error.error(Error.ErrorType.SYNTAX_ERROR, "Multiplication operation is not available for " + v0 + " and " + v1 + " types");
                return null;
            }
        } else if (raw.contains("/")) {
            String[] split = raw.split("/");
            String v0 = split[0].trim();
            String v1 = split[1].trim();
            if (TypeChecker.checkType(v0, DefaultTypes.Int) && TypeChecker.checkType(v1, DefaultTypes.Int)) {
                return String.valueOf(Integer.parseInt(v0) / Integer.parseInt(v1));
            } else if (TypeChecker.checkType(v0, DefaultTypes.Float) && TypeChecker.checkType(v1, DefaultTypes.Float)) {
                return String.valueOf(Float.parseFloat(v0) / Float.parseFloat(v1));
            } else if (TypeChecker.checkType(v0, DefaultTypes.Double) && TypeChecker.checkType(v1, DefaultTypes.Double)) {
                return String.valueOf(Double.parseDouble(v0) / Double.parseDouble(v1));
            } else {
                Error.error(Error.ErrorType.SYNTAX_ERROR, "Division operation is not available for " + v0 + " and " + v1 + " types");
                return null;
            }
        } else {
            return raw.replace(";", "").replace("=", "").trim();
        }
        return null;
    }
}
