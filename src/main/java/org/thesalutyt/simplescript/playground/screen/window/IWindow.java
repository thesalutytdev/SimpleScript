package org.thesalutyt.simplescript.playground.screen.window;

import org.thesalutyt.simplescript.playground.screen.resource.IVisible;
import org.thesalutyt.simplescript.playground.screen.window.key.KeyListen;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class IWindow extends JFrame implements IVisible {
    public IWindow(WindowSettings settings) {
        super(settings.TITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(settings.WIDTH, settings.HEIGHT);
        this.setVisible(settings.visible);
        this.setResizable(settings.RESIZABLE);
        if (settings.icon != null) {
            this.setIconImage(settings.icon.getImage());
        }
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                KeyListen.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                KeyListen.keyReleased(e);
            }
        });
    }

    @Override
    public void close() {
        this.setVisible(false);
        System.exit(0);
    }
}
