/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfc.sf2.palette.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

/**
 *
 * @author wiz
 */
public class PalettePane extends JPanel{
    
    public PalettePane(){
        Color[] colors = {};
        setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        for (int col = 0; col < colors.length; col++) {
            gbc.gridx = col;

            ColorPane colorPane = new ColorPane(colors[col]);
            Border border = null;
            if (col < colors.length-1) {
                border = new MatteBorder(1, 1, 1, 0, Color.GRAY);
            } else {
                border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
            }
            colorPane.setBorder(border);
            add(colorPane, gbc);
        }
    }
    
    public PalettePane(Color[] colors) {
        setLayout(new GridBagLayout());
        setColors(colors);
   }
    
   public void setColors(Color[] colors){
        this.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        for (int col = 0; col < colors.length; col++) {
            gbc.gridx = col;
            ColorPane colorPane = new ColorPane(colors[col]);
            add(colorPane, gbc);
        }
   }
   
}
