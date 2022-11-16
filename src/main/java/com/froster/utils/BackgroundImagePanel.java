package com.froster.utils;

import javax.swing.*;
import java.awt.*;

public class BackgroundImagePanel extends JPanel {
    protected Image image;

    public BackgroundImagePanel() {
        this(null);
    }

    public BackgroundImagePanel(Image background) {
        this.image = background;
        this.setOpaque(false);
    }

    public BackgroundImagePanel(Image background, LayoutManager layout) {
        super(layout);
        this.image = background;
        this.setOpaque(false);
    }

    public void setBackgroundImage(Image image) {
        this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        }
    }
}
