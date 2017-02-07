/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfc.sf2.palette.graphics;

import java.awt.Color;
import java.util.Arrays;

/**
 *
 * @author wiz
 */
public class PaletteEncoder {
    
    private static byte[] newPaletteBytes;
    
    public static void producePalette(Color[] palette){

        byte[] paletteBytes = new byte[palette.length*2];
        
        for(int i=0;i<palette.length;i++){
            byte first = 0x00;
            byte second = 0x00;
            Color color = palette[i];
            int b = color.getBlue() / 16;
            int g = color.getGreen() / 16;
            int r = color.getRed() / 16;
            first = (byte)b;
            second = (byte)(((g*16)&0xF0) | (r&0x0F));
            paletteBytes[i*2] = first;
            paletteBytes[i*2+1] = second;
        }
        
        newPaletteBytes = paletteBytes;
    }
    
    public static byte[] getNewPaletteFileBytes(){
        return newPaletteBytes;
    }
    
}
