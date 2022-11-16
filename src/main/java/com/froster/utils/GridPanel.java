package com.froster.utils;

import javax.swing.*;
import java.awt.*;

public class GridPanel extends JPanel {

    public final JLabel label = new JLabel();

    public int width, height;

    public GridPanel(int width, int height) {
        this.width = width;
        this.height = height;
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(Box.createHorizontalGlue());
        add(label);
        add(Box.createHorizontalGlue());
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension space = getParent().getSize();
        int length = (Math.min(space.width / width, space.height / height));
        return new Dimension(length, length);
    }
}
