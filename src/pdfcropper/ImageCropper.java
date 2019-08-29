package pdfcropper;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

public class ImageCropper {

	File image_orig;
	// Imagem de origem e pasta destino das imagens cortadas
	public ImageCropper(File image_orig) {
		this.image_orig = image_orig;
	}
	
	public void crop() throws IOException, UnsupportedEncodingException{
		int w_crop = 0;
		int h_crop = 0;
		
		// le a imagem original
		BufferedImage img = ImageIO.read(image_orig);
		w_crop = img.getWidth() / 2; // largura da imagem desejada = largura da original / 2
		h_crop = img.getHeight(); // mantem altura original
		
		// recorta com os tamanhos desejados
		File write_page = image_orig;
		BufferedImage img_page1 = cropImage(write_page, 0, 0, w_crop, h_crop); // pagina 1
		BufferedImage img_page2 = cropImage(write_page, w_crop, 0, w_crop, h_crop); // pagina 2
		
		if(!(new File("cropped_img").exists()))
			new File("cropped_img").mkdir();
		
		File output_page1 = new File("cropped_img", image_orig.getName().split(Pattern.quote("."))[0] + "_p1.png");
		ImageIO.write(img_page1, "png", output_page1);
		
		File output_page2 = new File("cropped_img", image_orig.getName().split(Pattern.quote("."))[0] + "_p2.png");
		ImageIO.write(img_page2, "png", output_page2); // escreve nas saidas
	}
	
	public void crop(File image_orig) throws IOException, UnsupportedEncodingException{
		this.image_orig = image_orig;
		
		int w_crop = 0;
		int h_crop = 0;
		
		// le a imagem original
		BufferedImage img = ImageIO.read(image_orig);
		w_crop = img.getWidth() / 2; // largura da imagem desejada = largura da original / 2
		h_crop = img.getHeight(); // mantem altura original
		
		// recorta com os tamanhos desejados
		File write_page = image_orig;
		BufferedImage img_page1 = cropImage(write_page, 0, 0, w_crop, h_crop); // pagina 1
		BufferedImage img_page2 = cropImage(write_page, w_crop, 0, w_crop, h_crop); // pagina 2
		
		if(!(new File("cropped_img").exists()))
			new File("cropped_img").mkdir();
		
		File output_page1 = new File("cropped_img", image_orig.getName().split(Pattern.quote("."))[0] + "_p1.png");
		ImageIO.write(img_page1, "png", output_page1);
		
		File output_page2 = new File("cropped_img", image_orig.getName().split(Pattern.quote("."))[0] + "_p2.png");
		ImageIO.write(img_page2, "png", output_page2); // escreve nas saidas
	}

	private BufferedImage cropImage(File filePath, int x, int y, int w, int h){
    	try {
        	BufferedImage originalImgage = ImageIO.read(filePath);
        	BufferedImage subImgage = originalImgage.getSubimage(x, y, w, h);
        	return subImgage;
    	} catch (IOException e) {
        	e.printStackTrace();
        	return null;
    	}
	}
}
