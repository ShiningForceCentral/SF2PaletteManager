/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfc.sf2.palette.io;

import com.sfc.sf2.palette.graphics.PaletteDecoder;
import com.sfc.sf2.palette.graphics.PaletteEncoder;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wiz
 */
public class RomManager {
    
    public static final int ORIGINAL_ROM_TYPE = 0;
    public static final int CARAVAN_ROM_TYPE = 1;
    
    private static final int BASE_PALETTE_OFFSET = 0x309E;
    
    private static File romFile;  
    private static byte[] romData;
    
    public static Color[] importRom(int romType, String romFilePath){
        System.out.println("com.sfc.sf2.palette.io.RomManager.importRom() - Importing ROM ...");
        RomManager.openFile(romFilePath);
        Color[] palette = RomManager.parsePalette(romType);        
        System.out.println("com.sfc.sf2.palette.io.RomManager.importRom() - ROM imported.");
        return palette;
    }
    
    public static void exportRom(int romType, Color[] palette, String romFilePath){
        System.out.println("com.sfc.sf2.palette.io.RomManager.exportRom() - Exporting ROM ...");
        RomManager.producePalette(palette);
        RomManager.writeFile(romType, romFilePath);
        System.out.println("com.sfc.sf2.palette.io.RomManager.exportRom() - ROM exported.");        
    }    
    
    private static void openFile(String romFilePath){
        try {
            System.out.println("com.sfc.sf2.palette.io.RomManager.openFile() - ROM file path : " + romFilePath);
            romFile = new File(romFilePath);
            romData = Files.readAllBytes(Paths.get(romFile.getAbsolutePath()));
            System.out.println("com.sfc.sf2.palette.io.RomManager.openFile() - File opened.");
        } catch (IOException ex) {
            Logger.getLogger(RomManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static Color[] parsePalette(int romType){
        System.out.println("com.sfc.sf2.palette.io.RomManager.parsePalette() - Parsing Palette ...");
        byte[] data = Arrays.copyOfRange(romData,BASE_PALETTE_OFFSET,BASE_PALETTE_OFFSET+0x20);        
        Color[] palette = PaletteDecoder.parsePalette(data);
        System.out.println("com.sfc.sf2.palette.io.RomManager.parsePalette() - Palette parsed.");
        return palette;
    }

    private static void producePalette(Color[] palette) {
        System.out.println("com.sfc.sf2.palette.io.DisassemblyManager.producePalette() - Producing Palette ...");
        PaletteEncoder.producePalette(palette);
        System.out.println("com.sfc.sf2.palette.io.DisassemblyManager.producePalette() - Palette produced.");
    }    
  
    private static void writeFile(int romType, String romFilePath){
        try {
            System.out.println("com.sfc.sf2.palette.io.RomManager.writeFile() - Writing file ...");
            romFile = new File(romFilePath);
            Path romPath = Paths.get(romFile.getAbsolutePath());
            romData = Files.readAllBytes(romPath);
            byte[] palette = PaletteEncoder.getNewPaletteFileBytes();
            System.arraycopy(palette, 0, romData, BASE_PALETTE_OFFSET, 0x20);
            Files.write(romPath,romData);
            System.out.println(romData.length + " bytes into " + romFilePath);  
            System.out.println("com.sfc.sf2.palette.io.RomManager.writeFile() - File written.");
        } catch (IOException ex) {
            Logger.getLogger(RomManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
