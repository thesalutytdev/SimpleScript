package org.thesalutyt.simplescript.playground.screen.window.widget;

import javax.swing.*;
import java.awt.*;

public class IWidgetPanel extends JPanel {
    public static JPanel panel = new JPanel();
    public static void addButton(IButton widget, String layout) {
        panel.add(widget.button, layout);
    }
    public static void addLabel(ILabel widget, String layout) {
        panel.add(widget.label, layout);
    }
    public static void addTextArea(ITextArea widget, String layout) {
        panel.add(widget.textArea, layout);
    }
    public static void removeLabel(ILabel widget) {
        panel.remove(widget.label);
    }
    public static void setLayout(FlowLayout layout) {
        panel.setLayout(layout);
    }
}
