package com.rootlet.kotlin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MyPanel extends JPanel{
    int rows;
    int columns;
    int size;

    /*public MyPanel(int rows, int columns, int size) {
        this.rows = rows;
        this.columns = columns;
        this.size = size;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);

        for (int i=0; i<=300; i+=30) {
            g.drawLine(i, 0, i, 300);
            g.drawLine(0, i, 300, i);
        }

    }*/

    private List<Shape> grid;
    private List<Shape> fill;

    public MyPanel() {
        grid = new ArrayList<>();
        fill = new ArrayList<>();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (Shape shape : grid) {
                    if (shape.contains(e.getPoint())) {
                        if (fill.contains(shape)) {
                            fill.remove(shape);
                        } else {
                            fill.add(shape);
                        }
                    }
                }
                repaint();
            }
        });

        int colWidth = 200 / 50;
        int rowHeight = 200 / 50;

        for (int row = 0; row < 50; row++) {
            for (int col = 0; col < 50; col++) {
                grid.add(new Rectangle(colWidth * col, rowHeight * row, colWidth, rowHeight));
            }
        }

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        for (Shape cell : fill) {
            g2d.fill(cell);
        }
        g2d.setColor(Color.BLACK);
        for (Shape cell : grid) {
            g2d.draw(cell);
        }
    }

    public void changeGrid(int x, int y) {
        for (Shape shape : grid) {
            if (shape.contains(x, y)) {
                if (fill.contains(shape)) {
                    fill.remove(shape);
                } else {
                    fill.add(shape);
                }
            }
        }
        repaint();
    }

}
