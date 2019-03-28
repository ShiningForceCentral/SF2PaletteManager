/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfc.sf2.palette;

import com.sfc.sf2.palette.graphics.PaletteDecoder;
import com.sfc.sf2.palette.io.DisassemblyManager;
import com.sfc.sf2.palette.io.GifManager;
import com.sfc.sf2.palette.io.PngManager;
import com.sfc.sf2.palette.io.RomManager;
import java.awt.Color;

/**
 *
 * @author wiz
 */
public class PaletteManager {
       
    public Color[] palette;

    public Color[] getPalette() {
        return palette;
    }

    public void setPalette(Color[] palette) {
        this.palette = palette;
    }
       
    public void importDisassembly(String filePath){
        System.out.println("com.sfc.sf2.palette.PaletteManager.importDisassembly() - Importing disassembly ...");
        this.palette = DisassemblyManager.importDisassembly(filePath);
        System.out.println("com.sfc.sf2.palette.PaletteManager.importDisassembly() - Disassembly imported.");
    }
    
    public void exportDisassembly(String filePath, Color[] currentColors){
        System.out.println("com.sfc.sf2.palette.PaletteManager.importDisassembly() - Exporting disassembly ...");
        this.palette = currentColors;
        DisassemblyManager.exportDisassembly(currentColors, filePath);
        System.out.println("com.sfc.sf2.palette.PaletteManager.importDisassembly() - Disassembly exported.");        
    }   
    
    public void importRom(String originalRomFilePath, String offset, String length){
        System.out.println("com.sfc.sf2.palette.PaletteManager.importOriginalRom() - Importing original ROM ...");
        this.palette = RomManager.importRom(originalRomFilePath, offset, length);
        System.out.println("com.sfc.sf2.palette.PaletteManager.importOriginalRom() - Original ROM imported.");
    }
    
    public void exportRom(String originalRomFilePath, Color[] currentColors, String offset){
        System.out.println("com.sfc.sf2.palette.PaletteManager.exportOriginalRom() - Exporting original ROM ...");
        this.palette = currentColors;
        RomManager.exportRom(this.palette, originalRomFilePath, offset);
        System.out.println("com.sfc.sf2.palette.PaletteManager.exportOriginalRom() - Original ROM exported.");        
    }    
    
    public void importPng(String filepath){
        System.out.println("com.sfc.sf2.palette.PaletteManager.importPng() - Importing PNG ...");
        this.palette = PngManager.importPng(filepath);
        System.out.println("com.sfc.sf2.palette.PaletteManager.importPng() - PNG imported.");
    }
    
    public void exportPng(String filepath, Color[] currentColors, int width, int height){
        System.out.println("com.sfc.sf2.palette.PaletteManager.exportPng() - Exporting PNG ...");
        this.palette = currentColors;
        PngManager.exportPng(this.palette, filepath, width, height);
        System.out.println("com.sfc.sf2.palette.PaletteManager.exportPng() - PNG exported.");        
    }    
    
    public void importGif(String filepath){
        System.out.println("com.sfc.sf2.palette.PaletteManager.importGif() - Importing GIF ...");
        this.palette = GifManager.importGif(filepath);
        System.out.println("com.sfc.sf2.palette.PaletteManager.importGif() - GIF imported.");
    }
    
    public void exportGif(String filepath, Color[] currentColors, int width, int height){
        System.out.println("com.sfc.sf2.palette.PaletteManager.exportGif() - Exporting GIF ...");
        this.palette = currentColors;
        GifManager.exportGif(this.palette, filepath, width, height);
        System.out.println("com.sfc.sf2.palette.PaletteManager.exportGif() - GIF exported.");        
    }      
    
    public void loadPalette(byte[] paletteBytes){
        System.out.println("com.sfc.sf2.palette.PaletteManager.loadPalette() - Loading Palette ...");
        this.palette = PaletteDecoder.parsePalette(paletteBytes);
        System.out.println("com.sfc.sf2.palette.PaletteManager.loadPalette() - Palette loaded.");
    }
    
    
    
    
    
}
