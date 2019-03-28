/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfc.sf2.palette.io;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author wiz
 */
public class GifManager {

    private static final Logger LOG = Logger.getLogger(GifManager.class.getName());
    
    public static Color[] importGif(String filepath){
        LOG.entering(LOG.getName(),"importGif");
        Color[] palette = null;
        try{
            Path path = Paths.get(filepath);
            BufferedImage img = ImageIO.read(path.toFile());
            ColorModel cm = img.getColorModel();
            if(!(cm instanceof IndexColorModel)){
                LOG.warning("GIF FORMAT ERROR : COLORS ARE NOT INDEXED AS EXPECTED.");
            }else{
                IndexColorModel icm = (IndexColorModel) cm;
                palette = buildColors(icm);
            }
        }catch(Exception e){
             LOG.throwing(LOG.getName(), "importGif", e);
        }        
        LOG.exiting(LOG.getName(),"importGif");        
        return palette;                
    }
    
    private static Color[] buildColors(IndexColorModel icm){
        Color[] colors = new Color[16];
        if(icm.getMapSize()>16){
            LOG.warning("com.sfc.sf2.graphics.io.GifManager.buildColors() - GIF FORMAT HAS MORE THAN 16 INDEXED COLORS : "+icm.getMapSize());
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
    
    public static void exportGif(Color[] palette, String filepath, int width, int height){
        try {
            LOG.entering(LOG.getName(),"exportGif");
            IndexColorModel icm = buildIndexColorModel(palette);
            BufferedImage image = new BufferedImage(width, height , BufferedImage.TYPE_BYTE_BINARY, icm);
            File outputfile = new File(filepath);
            LOG.fine("File path : "+outputfile.getAbsolutePath());
            ImageIO.write(image, "gif", outputfile);
            LOG.exiting(LOG.getName(),"exportGif");
        } catch (Exception ex) {
            LOG.throwing(LOG.getName(),"exportGif", ex);
        }  
    }
    
    
    private static IndexColorModel buildIndexColorModel(Color[] colors){
        byte[] reds = new byte[16];
        byte[] greens = new byte[16];
        byte[] blues = new byte[16];
        byte[] alphas = new byte[16];
        reds[0] = (byte)0xFF;
        greens[0] = (byte)0xFF;
        blues[0] = (byte)0xFF;
        alphas[0] = 0;
        for(int i=1;i<16;i++){
            reds[i] = (byte)colors[i].getRed();
            greens[i] = (byte)colors[i].getGreen();
            blues[i] = (byte)colors[i].getBlue();
            alphas[i] = (byte)0xFF;
        }
        IndexColorModel icm = new IndexColorModel(4,16,reds,greens,blues,alphas);
        return icm;
    }   
}
