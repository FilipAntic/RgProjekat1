package projekat1;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.Random;

import rafgfxlib.ImageViewer;
import rafgfxlib.Util;


public class Ilustrator {
	
	
//	private static BufferedImage image = new BufferedImage(MainFrame.WIDTH,MainFrame.HEIGHT, BufferedImage.TYPE_INT_RGB);
//	private static Random random = new Random();
//	
//	public static BufferedImage noiseGenerator(){
//		 
//		 
//	    	for(int y = 0; y < MainFrame.WIDTH; y++){
//	    		System.out.println("tu si 1");
//	    		for(int x = 0; x < MainFrame.HEIGHT; x++){
//	        		image.setRGB(x, y, random.nextInt(0xFFFFFF));
//	        		System.out.println("tu si 2");
//	        	}
//	    	}
//			return image;
//	    }
	
	
	public static BufferedImage noiseGenerator(){
		int[] black = { 0, 0, 0 };
		int[] white = { 255, 255, 255 };
		
		int octaves = 10;
		
		int octaveSize = 2;
		
		float persistence = 0.75f;
		
		int width = (int)Math.pow(octaveSize, octaves);
		int height = width;
		
		WritableRaster target = Util.createRaster(width, height, false);
		
		float[][] tempMap = new float[width][height];
		
		float[][] finalMap = new float[width][height];
		
		float multiplier = 1.0f;
		
		for(int o = 0; o < octaves; ++o)
		{
			float[][] octaveMap = new float[octaveSize][octaveSize];
			
			for(int x = 0; x < octaveSize; ++x)
			{
				for(int y = 0; y < octaveSize; ++y)
				{
					octaveMap[x][y] = ((float)Math.random() - 0.5f) * 2.0f;
				}
			}
			
			Util.floatMapRescale(octaveMap, tempMap);
			
			Util.floatMapMAD(tempMap, finalMap, multiplier);
			
			octaveSize *= 2;
			
			multiplier *= persistence;
		}
		
		Util.mapFloatMapToRaster(finalMap, -1.0f, 1.0f, black, white, target);
		
		return Util.rasterToImage(target);
		
		
		
	}
		
	public static int randomColor(){
		
		Random random = new Random();
		
		return random.nextInt(256);
		
	}

	
	

}