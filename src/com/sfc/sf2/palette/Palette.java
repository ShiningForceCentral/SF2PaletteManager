/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfc.sf2.palette;

import java.awt.Color;
import java.awt.image.IndexColorModel;

/**
 *
 * @author TiMMy
 */
public class Palette {
    
    private String name;
    private Color[] colors;
    
    public Palette() {
        this.name = "New Palette";
        this.colors = null;
    }
    
    public Palette(Color[] colors) {
        this.name = "New Palette";
        this.colors = colors;
    }
    
    public Palette(String name, Color[] colors) {
        this.name = name;
        this.colors = colors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color[] getColors() {
        return colors;
    }

    public void setColors(Color[] palette) {
        this.colors = palette;
    }
    
    public int getColorsCount() {
        return colors.length;
    }
    
    public IndexColorModel buildICM() {
        return Palette.buildICM(colors);
    }
    
    public static Color[] fromICM(IndexColorModel icm){
        Color[] colors = new Color[16];
        if(icm.getMapSize()>16){
            System.out.println("fromICM - Image format has more than 16 indexed colors. Palette may not load correctly.");
        }
        byte[] reds = new byte[icm.getMapSize()];
        byte[] greens = new byte[icm.getMapSize()];
        byte[] blues = new byte[icm.getMapSize()];
        icm.getReds(reds);
        icm.getGreens(greens);
        icm.getBlues(blues);
        for(int i=0;i<16;i++){
            colors[i] = new Color((int)(reds[i]&0xff),(int)(greens[i]&0xff),(int)(blues[i]&0xff));
        }
        return colors;
    }
    
    public static IndexColorModel buildICM(Color[] colors) {
        byte[] reds = new byte[16];
        byte[] greens = new byte[16];
        byte[] blues = new byte[16];
        byte[] alphas = new byte[16];
        reds[0] = (byte)0xFF;
        greens[0] = (byte)0xFF;
        blues[0] = (byte)0xFF;
        alphas[0] = 0;
        for(int i=1; i<16; i++) {
            reds[i] = (byte)colors[i].getRed();
            greens[i] = (byte)colors[i].getGreen();
            blues[i] = (byte)colors[i].getBlue();
            alphas[i] = (byte)0xFF;
        }
        IndexColorModel icm = new IndexColorModel(4,16,reds,greens,blues,alphas);
        return icm;
    }
}
