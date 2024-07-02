package org.thesalutyt.simplescript.playground;

import org.thesalutyt.simplescript.api.simplescript.KeyWords;
import org.thesalutyt.simplescript.api.simplescript.common.simple.*;
import org.thesalutyt.simplescript.common.info.Info;
import org.thesalutyt.simplescript.interpreter.Interpreter;
import org.thesalutyt.simplescript.interpreter.scope.Scope;
import org.thesalutyt.simplescript.interpreter.start.WordAdder;
import org.thesalutyt.simplescript.playground.screen.window.IWindow;
import org.thesalutyt.simplescript.playground.screen.window.WindowSettings;
import org.thesalutyt.simplescript.playground.screen.window.key.KeyListen;
import org.thesalutyt.simplescript.playground.screen.window.widget.IButton;
import org.thesalutyt.simplescript.playground.screen.window.widget.ILabel;
import org.thesalutyt.simplescript.playground.screen.window.widget.ITextArea;
import org.thesalutyt.simplescript.playground.screen.window.widget.IWidgetPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.ColorModel;
import java.io.Console;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Main extends IWindow {
    public Main() {
        super(new WindowSettings("SimpleScript", 800, 600, false, true, Info.DEFAULT_ICON_PATH));
        KeyWords.addScope("simple", new Simple());
        KeyWords.addScope("log", new Logger());
        KeyWords.addScope("lang", new Lang());
        KeyWords.addScope("file", new File());
        Info.interpreter = new Interpreter();
        WordAdder wordAdder = new WordAdder();
        ITextArea textArea = new ITextArea("simple.print >> Hello World!;", 10, 10);
        IWidgetPanel.addTextArea(textArea, BorderLayout.LINE_START);
        IButton button = new IButton("Save");
        button.addClickAction(() -> {
            save(textArea);
        });
        IWidgetPanel.addButton(button, BorderLayout.PAGE_END);
        IButton run = new IButton("Run");
        run.addClickAction(() -> {
            save(textArea);
            Interpreter interpreter = new Interpreter();
            try {
                interpreter.import_file(Info.rootDir + "test.ssc");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            ILabel label = new ILabel("Ran", 10, 10);
            IWidgetPanel.addLabel(label, BorderLayout.LINE_END);
        });
        IWidgetPanel.addButton(run, BorderLayout.PAGE_END);
        IButton close = new IButton("Close");
        close.addClickAction(() -> {
            this.save(textArea);
            this.close();
        });
        IWidgetPanel.addButton(close, BorderLayout.PAGE_END);
        IButton clear = new IButton("Clear");
        clear.addClickAction(() -> {
            textArea.setDefaultText("");
            ILabel label = new ILabel("Cleared", 10, 10);
            IWidgetPanel.addLabel(label, BorderLayout.LINE_END);
        });
        IWidgetPanel.addButton(clear, BorderLayout.PAGE_END);
        IButton open = new IButton("Open");
        open.addClickAction(() -> {
            ITextArea textArea1 = new ITextArea("print >> Hello World!;", 10, 10);
            IWidgetPanel.addTextArea(textArea1, BorderLayout.LINE_START);
        });
        IWidgetPanel.addButton(open, BorderLayout.PAGE_END);
        this.add(IWidgetPanel.panel);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
    private void save(ITextArea textArea) {
        try {
            FileWriter fileWriter = new FileWriter(Info.rootDir + "test.ssc");
            fileWriter.write(textArea.getText());
            fileWriter.close();
            ILabel label = new ILabel("Saved", 10, 10);
            IWidgetPanel.addLabel(label, BorderLayout.LINE_END);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
