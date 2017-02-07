/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfc.sf2.palette.io;

import com.sfc.sf2.palette.graphics.PaletteDecoder;
import com.sfc.sf2.palette.graphics.PaletteEncoder;
import java.awt.Color;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wiz
 */
public class DisassemblyManager {
    
    
    public static Color[] importDisassembly(String filePath){
        System.out.println("com.sfc.sf2.palette.io.DisassemblyManager.importDisassembly() - Importing disassembly ...");
        Color[] palette = DisassemblyManager.parsePalette(filePath);        
        System.out.println("com.sfc.sf2.palette.io.DisassemblyManager.importDisassembly() - Disassembly imported.");
        return palette;
    }
    
    public static void exportDisassembly(Color[] palette, String filePath){
        System.out.println("com.sfc.sf2.palette.io.DisassemblyManager.exportDisassembly() - Exporting disassembly ...");
        DisassemblyManager.producePalette(palette);
        DisassemblyManager.writeFiles(filePath);
        System.out.println("com.sfc.sf2.palette.io.DisassemblyManager.exportDisassembly() - Disassembly exported.");        
    }    
    
    private static Color[] parsePalette(String filePath){
        System.out.println("com.sfc.sf2.palette.io.DisassemblyManager.parseTextbank() - Parsing Palette ...");
        Color[] palette = null;       
        try{
            Path path = Paths.get(filePath);
            byte[] data = Files.readAllBytes(path);
            palette = PaletteDecoder.parsePalette(data);
        }catch(Exception e){
             System.err.println("com.sfc.sf2.palette.io.DisassemblyManager.parseTextbank() - Error while parsing Palette data : "+e);
        } 
        System.out.println("com.sfc.sf2.palette.io.DisassemblyManager.parseTextbank() - Palette parsed.");
        return palette;
    }

    private static void producePalette(Color[] palette) {
        System.out.println("com.sfc.sf2.palette.io.DisassemblyManager.produceTextbanks() - Producing palette ...");
        PaletteEncoder.producePalette(palette);
        System.out.println("com.sfc.sf2.palette.io.DisassemblyManager.produceTextbanks() - Palette produced.");
    }    
  
    private static void writeFiles(String filePath){
        try {
            System.out.println("com.sfc.sf2.palette.io.DisassemblyManager.writeFiles() - Writing file ...");
            Path paletteFilePath = Paths.get(filePath);
            byte[] newPaletteFileBytes = PaletteEncoder.getNewPaletteFileBytes();
            Files.write(paletteFilePath,newPaletteFileBytes);
            System.out.println(newPaletteFileBytes.length + " bytes into " + paletteFilePath);
            System.out.println("com.sfc.sf2.palette.io.DisassemblyManager.writeFiles() - File written.");
        } catch (IOException ex) {
            Logger.getLogger(DisassemblyManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    
}
