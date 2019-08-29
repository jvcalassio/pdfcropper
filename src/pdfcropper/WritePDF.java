package pdfcropper;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class WritePDF {

	PDDocument pdf_to_write;
	
	public WritePDF() throws IOException {
		this.pdf_to_write = new PDDocument();
	}
	
	public void create() throws IOException {
		File folder = new File("cropped_img");
		File[] imgs = folder.listFiles();
		
		 Arrays.sort(imgs, new Comparator<File>() {
		    @Override
	        public int compare(final File entry1, final File entry2) {
	        	if(entry1.getName().length() != entry2.getName().length()) {
	        		Integer e1 = entry1.getName().length();
	        		Integer e2 = entry2.getName().length();
	        		return e1.compareTo(e2);
	        	} else {
	        		return entry1.compareTo(entry2);
	        	}
	        }
	     });

		for(int i=0;i<imgs.length;i++){
			if(imgs[i].isFile()) {
				addImageToPDF(imgs[i]);
				System.out.println("Salvando pagina " + i); 
			}
		}
		this.pdf_to_write.save("output.pdf");
		this.pdf_to_write.close();
	}
	
	public void create(String out_name) throws IOException {
		File folder = new File("cropped_img");
		File[] imgs = folder.listFiles();
		
		// ordena alfabeticamente (considerando numeros > 10)
		Arrays.sort(imgs, new Comparator<File>() {
		    @Override
	        public int compare(final File entry1, final File entry2) {
	        	if(entry1.getName().length() != entry2.getName().length()) {
	        		Integer e1 = entry1.getName().length();
	        		Integer e2 = entry2.getName().length();
	        		return e1.compareTo(e2);
	        	} else {
	        		return entry1.compareTo(entry2);
	        	}
	        }
	     });

		for(int i=0;i<imgs.length;i++){
			if(imgs[i].isFile()) {
				addImageToPDF(imgs[i]);
				System.out.println("Salvando pagina " + i); 
			}
		}
		this.pdf_to_write.save(out_name);
		this.pdf_to_write.close();
	}
	
	
	private void addImageToPDF(File img) throws IOException {
		BufferedImage bi = ImageIO.read(img);
		
		PDPage pg = new PDPage(new PDRectangle(bi.getWidth(), bi.getHeight()));
		
		PDImageXObject pdimg = PDImageXObject.createFromFile(img.getPath(), this.pdf_to_write);
		PDPageContentStream contents = new PDPageContentStream(this.pdf_to_write, pg);
		contents.drawImage(pdimg, 0, 0);
		contents.close();
		
		this.pdf_to_write.addPage(pg);
	}
}
