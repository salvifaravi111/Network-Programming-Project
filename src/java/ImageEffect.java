
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.ByteArrayOutputStream;
import static java.lang.Math.min;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sifat Ahmed
 */
public class ImageEffect {
     
    public byte[] get_byte_array(BufferedImage image, String extension) throws Exception
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, extension, baos);
        
        return baos.toByteArray();
    }
    
    public BufferedImage gray_scale(BufferedImage image)
    {
        int height = image.getHeight();
        int width = image.getWidth();
        
        for(int i=0; i<height; i++) {
         
            for(int j=0; j<width; j++) {
            
               Color c = new Color(image.getRGB(j, i));
               int red = (int)(c.getRed() * 0.299);
               int green = (int)(c.getGreen() * 0.587);
               int blue = (int)(c.getBlue() *0.114);
               Color newColor = new Color(red+green+blue,
               
               red+green+blue,red+green+blue);
               
               image.setRGB(j,i,newColor.getRGB());
            }
         }
        
        return image;
    }
    
    
    public BufferedImage sepia(BufferedImage image)
    {
        int height = image.getHeight();
        int width = image.getWidth();
        
        for(int i=0; i<height; i++) {
         
            for(int j=0; j<width; j++) {
            
               Color c = new Color(image.getRGB(j, i));
               int red = (int)(c.getRed());
               int green = (int)(c.getGreen());
               int blue = (int)(c.getBlue());
               
               if(red < 63){
                    red = (int)(red * 1.1);
                    blue = (int)(blue * 0.9);
                }else if(red < 192){
                    red = (int)(red * 1.15);
                    blue = (int)(blue * 0.85);
                }else{
                    red = Math.min((int)(red * 1.08), 255);
                    blue = (int)(blue * 0.93);
                }
               
               
               Color newColor = new Color(red, green, blue);
               
               image.setRGB(j,i,newColor.getRGB());
            }
         }  
        return image;
    }
    
    
    public BufferedImage negative(BufferedImage image)
    {
        int height = image.getHeight();
        int width = image.getWidth();
        
        for(int i=0; i<height; i++) {
         
            for(int j=0; j<width; j++) {
            
               Color c = new Color(image.getRGB(j, i));
               int red = (int)(c.getRed());
               int green = (int)(c.getGreen());
               int blue = (int)(c.getBlue());
               
               
               Color newColor = new Color(255-red, 255-green, 255-blue);
               
               image.setRGB(j,i,newColor.getRGB());
            }
         }  
        return image;
    }
    
    
    public BufferedImage blur(BufferedImage image)
    {
        Kernel kernel = new Kernel(5, 5, new float[] 
        { 1f / 25f, 1f / 25f, 1f / 25f, 1f / 25f, 1f / 25f,
          1f / 25f, 1f / 25f, 1f / 25f, 1f / 25f, 1f / 25f,
          1f / 25f, 1f / 25f, 1f / 25f, 1f / 25f, 1f / 25f,
          1f / 25f, 1f / 25f, 1f / 25f, 1f / 25f, 1f / 25f,
          1f / 25f, 1f / 25f, 1f / 25f, 1f / 25f, 1f / 25f,
          
          });
        BufferedImageOp op = new ConvolveOp(kernel);
        image = op.filter(image, null);
        
        return image;
    }
    
    public BufferedImage sharpen(BufferedImage image)
    {
        Kernel kernel = new Kernel(3, 3, new float[] { -1, -1, -1, -1, 9, -1, -1,
        -1, -1 });
        BufferedImageOp op = new ConvolveOp(kernel);
        image = op.filter(image, null);
        
        return image;
    }
    
    public BufferedImage threshold(BufferedImage image)
    {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage output = new BufferedImage(
            w, h, BufferedImage.TYPE_BYTE_BINARY);

        int blackRgb = Color.BLACK.getRGB();
        int whiteRgb = Color.WHITE.getRGB();

        for (int y = 0; y < h; y++)
        {
            for (int x = 0; x < w; x++)
            {

                int rgb = image.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = (rgb) & 0xFF;
                int gray = (int) (0.2126 * r + 0.7152 * g + 0.0722 * b);
                if (gray >= 100)
                {
                    output.setRGB(x, y, whiteRgb);
                }
                else
                {
                    output.setRGB(x, y, blackRgb);
                }
            }
        }
        return output;
    }
    
    
    public BufferedImage edge(BufferedImage image)
    {
        Kernel kernel = new Kernel(3, 3, new float[] { -1, -2, -1, 0, 0, 0, 1,
        2, 1 });
        BufferedImageOp op = new ConvolveOp(kernel);
        image = op.filter(image, null);
        
        return image;
    }
    
    public BufferedImage hue(BufferedImage image)
    {
        int height = image.getHeight();
        int width = image.getWidth();
        
        for(int i=0; i<height; i++) {
         
            for(int j=0; j<width; j++) {
            
               Color c = new Color(image.getRGB(j, i));
               int red = (int)(c.getRed());
               int green = (int)(c.getGreen());
               int blue = (int)(c.getBlue());
               Color newColor = new Color(0, green, blue);
               
               image.setRGB(j,i,newColor.getRGB());
            }
         }
        
        return image;
    }
    
    public BufferedImage custom(BufferedImage image)
    {
        int height = image.getHeight();
        int width = image.getWidth();
        
        for(int i=0; i<height; i++) {
         
            for(int j=0; j<width; j++) {
            
               Color c = new Color(image.getRGB(j, i));
               int red = (int)(c.getRed());
               int green = (int)(c.getGreen());
               int blue = (int)(c.getBlue());
               Color newColor = new Color(min(red+red, 255), min(green+green, 255), min(blue+blue, 255));
               
               image.setRGB(j,i,newColor.getRGB());
            }
         }
        
        return image;
    }
    
    
}
