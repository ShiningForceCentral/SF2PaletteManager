/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfc.sf2.palette.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

/**
 *
 * @author wiz
 */
public class ColorPane extends JPanel {

    private Color currentColor;

    public Color getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }
    private Border defaultBorder;
    private ColorEditor colorEditor;
    private ColorPane self = this;

    public ColorPane(Color color, ColorEditor ce) {
        
        colorEditor = ce;
        
        currentColor = color;
        setBackground(color);
        defaultBorder = new MatteBorder(1, 1, 1, 1, Color.GRAY);
        setBorder(defaultBorder);
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                //defaultBackground = getBackground();
                //setBackground(Color.BLUE);
                Border border = new MatteBorder(2, 2, 2, 2, Color.DARK_GRAY);
                setBorder(border);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //setBackground(defaultBackground);
                setBorder(defaultBorder);
            }
            
            @Override
            public void mouseClicked(MouseEvent e){
                colorEditor.setColorPane(self);
            }
        });
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(30, 30);
    }
    
    public void updateColor(Color c){
        this.currentColor = c;
        setBackground(this.currentColor);
    }
}    
