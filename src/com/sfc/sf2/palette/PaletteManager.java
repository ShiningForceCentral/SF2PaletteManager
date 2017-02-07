/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfc.sf2.palette;

import com.sfc.sf2.palette.graphics.PaletteDecoder;
import com.sfc.sf2.palette.io.DisassemblyManager;
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
    
    public void exportDisassembly(String filePath){
        System.out.println("com.sfc.sf2.palette.PaletteManager.importDisassembly() - Exporting disassembly ...");
        DisassemblyManager.exportDisassembly(this.palette, filePath);
        System.out.println("com.sfc.sf2.palette.PaletteManager.importDisassembly() - Disassembly exported.");        
    }   
    
    public void importOriginalRom(String originalRomFilePath){
        System.out.println("com.sfc.sf2.palette.PaletteManager.importOriginalRom() - Importing original ROM ...");
        this.palette = RomManager.importRom(RomManager.ORIGINAL_ROM_TYPE,originalRomFilePath);
        System.out.println("com.sfc.sf2.palette.PaletteManager.importOriginalRom() - Original ROM imported.");
    }
    
    public void exportOriginalRom(String originalRomFilePath){
        System.out.println("com.sfc.sf2.palette.PaletteManager.exportOriginalRom() - Exporting original ROM ...");
        RomManager.exportRom(RomManager.ORIGINAL_ROM_TYPE, this.palette, originalRomFilePath);
        System.out.println("com.sfc.sf2.palette.PaletteManager.exportOriginalRom() - Original ROM exported.");        
    }   
    
    public void importCaravanRom(String caravanRomFilePath){
        System.out.println("com.sfc.sf2.palette.PaletteManager.importCaravanRom() - Importing Caravan ROM ...");
        this.palette = RomManager.importRom(RomManager.CARAVAN_ROM_TYPE,caravanRomFilePath);
        System.out.println("com.sfc.sf2.palette.PaletteManager.importCaravanRom() - Caravan ROM imported.");
    }
    
    public void exportCaravanRom(String caravanRomFilePath){
        System.out.println("com.sfc.sf2.palette.PaletteManager.exportCaravanRom() - Exporting original ROM ...");
        RomManager.exportRom(RomManager.CARAVAN_ROM_TYPE, this.palette, caravanRomFilePath);
        System.out.println("com.sfc.sf2.palette.PaletteManager.exportCaravanRom() - Caravan ROM exported.");        
    }    
    
    public void loadPalette(byte[] paletteBytes){
        System.out.println("com.sfc.sf2.palette.PaletteManager.loadPalette() - Loading Palette ...");
        this.palette = PaletteDecoder.parsePalette(paletteBytes);
        System.out.println("com.sfc.sf2.palette.PaletteManager.loadPalette() - Palette loaded.");
    }
    
    
    
    
    
}
