package com.crud.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class MyButton extends JButton {

	private static final long serialVersionUID = 1377106372776640473L;
	private static final Color PASTEL_GREEN = new Color(173, 216, 230);
    private static final Color DARKER_PASTEL_GREEN = new Color(133, 186, 200);

    public MyButton(String string) {
        super(string);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);
        setBackground(PASTEL_GREEN);
        setForeground(Color.BLACK);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setBackground(DARKER_PASTEL_GREEN);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setBackground(PASTEL_GREEN);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(PASTEL_GREEN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(PASTEL_GREEN);
            }
        });
    }
	@Override
	    protected void paintComponent(Graphics g) {
	        g.setColor(getBackground());
	        g.fillRect(0, 0, getWidth(), getHeight());
	        super.paintComponent(g);
	    }
}
