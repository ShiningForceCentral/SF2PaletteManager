/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfc.sf2.palette.graphics;

import java.awt.Color;

/**
 *
 * @author wiz
 */
public class PaletteDecoder {
    
    public static Color[] parsePalette(byte[] data){
        Color[] colors = new Color[data.length/2];
        
        for(int i=0;i*2<data.length;i++){
            if(i*2+1<data.length){
                byte first = data[i*2];
                byte second = data[i*2+1];
                int b = (0x0F & first) * 16;
                int g = (0xF0 & second);
                int r = (0x0F & second) * 16;
                Color color = new Color(r,g,b);
                colors[i] = color;
            }
        }
        
        return colors;
    }
    
}
