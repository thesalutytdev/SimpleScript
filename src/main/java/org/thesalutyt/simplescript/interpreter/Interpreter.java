package org.thesalutyt.simplescript.interpreter;

import org.thesalutyt.simplescript.api.simplescript.common.line.ILine;
import org.thesalutyt.simplescript.api.simplescript.common.variables.Variables;
import org.thesalutyt.simplescript.api.simplescript.lang.LangAnalyzer;
import org.thesalutyt.simplescript.common.info.Info;
import org.thesalutyt.simplescript.interpreter.source.SourceType;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Interpreter extends InterpreterInstance implements InterpreterResource {
    protected InterpreterInstance myInstance;
    protected LangAnalyzer analyzer;
    public Interpreter() {
        super (Info.rootDir);
        this.myInstance = new InterpreterInstance(Info.rootDir);
        analyzer = new LangAnalyzer(this);
    }

    public Interpreter getInterpreter() {
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
                        if (!code.endsWith(";") && !(code.startsWith("//"))) {
                            throw new RuntimeException("[ScriptError] EOL token expected");
                        }
                        if (code.startsWith("//")) {
                            return;
                        }
                        String[] lines = code.split(";");
                        for (String line : lines) {
                            if (line.length() > 0) {
                                execute(SourceType.SCRIPT, line);
                            } else {
                                continue;
                            }
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public void execute(SourceType sourceType, String code) throws FileNotFoundException {
        ILine line = new ILine(code);
        analyzer.analyze(line);
    }
    public String dataParser(String data) {
        if (data.startsWith("$")) {
            Variables var = Variables.getVar(data.split(";")[0].replace("$", ""));
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
