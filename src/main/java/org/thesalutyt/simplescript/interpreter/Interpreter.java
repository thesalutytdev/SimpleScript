package org.thesalutyt.simplescript.interpreter;

import org.thesalutyt.simplescript.api.simplescript.common.object.DefaultTypes;
import org.thesalutyt.simplescript.api.simplescript.common.variables.Variables;
import org.thesalutyt.simplescript.common.info.Info;
import org.thesalutyt.simplescript.common.time.Time;
import org.thesalutyt.simplescript.interpreter.error.Error;
import org.thesalutyt.simplescript.interpreter.source.SourceType;
import org.thesalutyt.simplescript.interpreter.type.TypeChecker;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Interpreter extends InterpreterInstance implements InterpreterResource {
    protected InterpreterInstance myInstance;
    public ArrayList<Variables> variables = new ArrayList<>();
    public Interpreter() {
        super (Info.rootDir);
        this.myInstance = new InterpreterInstance(Info.rootDir);
    }

    private Interpreter getInterpreter() {
        return this;
    }
    public void import_file(String path) throws FileNotFoundException, RuntimeException {
        if (!(path.startsWith(Info.rootDir) || path.endsWith(Info.FILE_FORMAT))) {
            throw new RuntimeException("[FileImportError] Wrong file format or wrong path(out of root)");
        } else {
            try {
                Scanner scanner = new Scanner(new FileReader(path));
                while (scanner.hasNextLine()) {
                    String code = scanner.nextLine();
                    if (Objects.equals(code, "") || Objects.equals(code, " ")) {
                        continue;
                    }
                    else {
                        execute(SourceType.SCRIPT, code);
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public void execute(SourceType sourceType, String code) throws FileNotFoundException {
        if (!code.endsWith(";") && !(code.startsWith("//"))) {
            throw new RuntimeException("[ScriptError] ; token expected");
        }
        if (code.startsWith("//") || code.startsWith("#")) {
            return;
        }
        String[] line = code.split(" >> ");
        String firstWord = null;
        for (String word : line) {
            if (Objects.equals(word, "pass;")) {
                return;
            }
            if (Objects.equals(word, "print")) {
                firstWord = "print";
            } else if (Objects.equals(word, "log")) {
                firstWord = "log";
            } else if (Objects.equals(firstWord, "print")) {
                if (word.startsWith("$")) {
                    Variables var = getVar(word.split(";")[0].replace("$", ""));
                    System.out.println(var.value);
                } else {
                    System.out.println(word.split(";")[0]);
                }
            } else if (Objects.equals(firstWord, "log")) {
                if (!allowLog) {
                    continue;
                }
                if (word.startsWith("$")) {
                    Variables var = getVar(word.split(";")[0].replace("$", ""));
                    System.out.println("[" + Time.getDate() + "] " + var.value);
                } else {
                    System.out.println("[" + Time.getDate() + "] " + word.split(";")[0]);
                }
            }
            else if (Objects.equals(word, "var")) {
                firstWord = "var";
            } else if (Objects.equals(firstWord, "var")) {
                String var = word.split(";")[0];
                String[] var_split = var.split("=");
                String var_name = word.split(";")[0].split(":")[1].replace(" ", "").split("=")[0];
                String var_value = var_split[1].replace(" ", "");
                String raw_var_type = word.split(";")[0].split(":")[0].replace(" ", "");
                DefaultTypes var_type;
                switch (raw_var_type) {
                    case "str", "string" -> {
                        boolean isCorrect = TypeChecker.checkType(var_value, DefaultTypes.Str);
                        if (isCorrect) {
                            var_type = DefaultTypes.Str;
                        } else {
                            throw new RuntimeException("[TypeError] Wrong data type assignment to a variable " + var_name);
                        }
                    }
                    case "bool", "boolean" -> {
                        boolean isCorrect = TypeChecker.checkType(var_value, DefaultTypes.Bool);
                        if (isCorrect) {
                            var_type = DefaultTypes.Bool;
                        } else {
                            throw new RuntimeException("[TypeError] Wrong data type assignment to a variable " + var_name);
                        }
                    }
                    case "int", "integer" -> var_type = DefaultTypes.Int;
                    case "float" -> var_type = DefaultTypes.Float;
                    case "double" -> var_type = DefaultTypes.Double;
                    default -> var_type = DefaultTypes.Object;
                }
                Variables var_ = new Variables(var_name, false, var_value, var_type);
                variables.add(var_);
                if (this.isDebug) {
                    System.out.println("[!!!!] New variable: " + var_name + " value: " + var_value + " type: " + var_type.toString());
                    System.out.println("[!!!!] Raw type: " + raw_var_type);
                } else {
                    continue;
                }
            }
            else if (Objects.equals(word, "const")) {
                firstWord = "const";
            } else if (Objects.equals(firstWord, "const")) {
                String var = word.split(";")[0];
                String[] var_split = var.split("=");
                String var_name = word.split(";")[0].split(":")[1].replace(" ", "").split("=")[0];
                String var_value = var_split[1].replace(" ", "");
                String raw_var_type = word.split(";")[0].split(":")[0].replace(" ", "");
                DefaultTypes var_type;
                boolean isCorrect;
                switch (raw_var_type) {
                    case "str":
                    case "string":
                        isCorrect = TypeChecker.checkType(var_value, DefaultTypes.Str);
                        if (isCorrect) {
                            var_type = DefaultTypes.Str;
                        } else {
                            throw new RuntimeException("[TypeError] Wrong data type assignment to a variable " + var_name);
                        }

                    case "bool":
                    case "boolean":
                        isCorrect = TypeChecker.checkType(var_value, DefaultTypes.Bool);
                        if (isCorrect) {
                            var_type = DefaultTypes.Bool;
                        } else {
                            throw new RuntimeException("[TypeError] Wrong data type assignment to a variable " + var_name);
                        }

                    case "int":
                    case "integer":
                        if (TypeChecker.checkType(var_value, DefaultTypes.Int)) {
                            var_type = DefaultTypes.Int;
                        } else {
                            throw new RuntimeException("[TypeError] Wrong data type assignment to a variable " + var_name);
                        }
                    case "float":
                        if (TypeChecker.checkType(var_value, DefaultTypes.Float)) {
                            var_type = DefaultTypes.Float;
                        } else {
                            throw new RuntimeException("[TypeError] Wrong data type assignment to a variable " + var_name);
                        }
                    case "double":
                        if (TypeChecker.checkType(var_value, DefaultTypes.Double)) {
                            var_type = DefaultTypes.Double;
                        } else {
                            throw new RuntimeException("[TypeError] Wrong data type assignment to a variable " + var_name);
                        }
                    default:
                        var_type = DefaultTypes.Object;
                }
                Variables var_ = new Variables(var_name, true, var_value, var_type);
                variables.add(var_);
                if (this.isDebug) {
                    System.out.println("[!!!!] New variable: " + var_name + " value: " + var_value + " type: " + var_type.toString());
                    System.out.println("[!!!!] Raw type: " + raw_var_type);
                } else {
                    continue;
                }
            }
            else if (code.startsWith("$")) {
                if (this.isDebug) {
                    String var_name = code.split(";")[0].replace("$", "");
                    System.out.println(var_name);
                    Variables var = Variables.getVar(var_name);
                    System.out.println(Variables.getVar(var_name));
                    System.out.println("[!!!!] Got var: " + var.name);
                } else {
                    getVar(code);
                }
                if (!(Objects.equals(code.split("\\.")[1], "") && Objects.equals(code.split("\\.")[1], ";"))) {
                    String[] args = code.split("\\.");
                    Variables var = getVar(args[0].replace("$", ""));
                    if (var.isFinal) {
                        throw new RuntimeException("Variable is constant!");
                    }
                    switch (args[1].split("=")[0].replace(" ", "")) {
                        case "isFinal":
                            var.isFinal = Boolean.parseBoolean(args[1].split("=")[1].replace(" ", "").replace(";", ""));
                            break;
                        case "value":
                            var.value = args[1].split("=")[1].replace(";", "").replaceFirst(" ", "");
                            break;
                    }
                }
            } else if (code.startsWith("configure")) {
                if (code.startsWith("configure.debug")) {
                    this.isDebug = Boolean.parseBoolean(code.split("=")[1].replace(" ", "").replace(";", ""));
                    //System.out.println(code.split("=")[1].replace(" ", "").replace(";", ""));
                    //System.out.println(Boolean.getBoolean(code.split("=")[1].replace(" ", "").replace(";", "")));
                    //System.out.println(this.isDebug);
                } else if (code.startsWith("configure.allow_log")) {
                    this.allowLog = Boolean.parseBoolean(code.split("=")[1].replace(" ", "").replace(";", ""));
                }
                else if (Objects.equals(code.split("\\.")[1], "")) {
                    throw new RuntimeException("[AttributeError] Empty configure statement.");
                }
            } else if (code.startsWith("!")) {
                if (code.startsWith("!exit = ")) {
                    // System.out.println(code.split("=")[1].replace(" ", "").replace(";", ""));
                    int exitCode = Integer.parseInt(code.split("=")[1].replace(" ", "").replace(";", ""));
                    this.close();
                    Error.exit(exitCode);
                } else {
                    throw new RuntimeException("[AttributeError] Cannot find function or key word " + word);
                }
            }
            else {
                throw new RuntimeException("[AttributeError] Cannot find function or key word " + word);
            }
        }
    }
    public Variables getVar(String input) {
        String var_name = input.split(";")[0].replace("$", "").replace(" ", "");
        return Variables.getVar(var_name);
    }
    public String dataParser(String data) {
        if (data.startsWith("$")) {
            Variables var = getVar(data.split(";")[0].replace("$", ""));
            System.out.println(var.value);
        } else {
            System.out.println(data.split(";")[0]);
        }
        return "";
    }
    public InterpreterInstance getInstance() {
        return myInstance;
    }

    @Override
    public String getResourceId() {
        return "Interpreter";
    }

    @Override
    public Boolean isActive() {
        return this.isActive;
    }
}
