package pdfcropper;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

public class PDFExtractor {
	
	File pdf_file;

	public PDFExtractor(File pdf_file) {
		this.pdf_file = pdf_file;
	}
	
	public ArrayList<Integer> extract() throws IOException{
		int qtd_img = 0, max_width = 0;
		PDDocument document = PDDocument.load(pdf_file);
		PDFRenderer pdfRenderer = new PDFRenderer(document);
		qtd_img = document.getNumberOfPages();
		
		// salva cada pagina individualmente
		for (int page = 0; page < qtd_img; page++) { 
		    BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 200, ImageType.RGB);
		    max_width = max(max_width, bim.getWidth());
		    if(!(new File("pdf_img").exists()))
		    	new File("pdf_img").mkdir();
		    
		    ImageIOUtil.writeImage(bim, "pdf_img/" + pdf_file.getName().split(Pattern.quote("."))[0] + "-" + (page+1) + ".png", 200);
		    System.out.println("Pagina " + (page+1) + " de " + document.getNumberOfPages() + " extraida");
		}
		document.close();
		ArrayList<Integer> rt = new ArrayList<Integer>();
		rt.add(qtd_img);
		rt.add(max_width);
		return rt;
	}
	
	public ArrayList<Integer> extract(File pdf_file) throws IOException{
		this.pdf_file = pdf_file;
		
		int qtd_img = 0, max_width = 0;
		PDDocument document = PDDocument.load(pdf_file);
		PDFRenderer pdfRenderer = new PDFRenderer(document);
		qtd_img = document.getNumberOfPages();
		
		// salva cada pagina individualmente
		for (int page = 0; page < qtd_img; page++) { 
		    BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 200, ImageType.RGB);
		    max_width = max(max_width, bim.getWidth());
		    if(!(new File("pdf_img").exists()))
		    	new File("pdf_img").mkdir();
		    
		    ImageIOUtil.writeImage(bim, "pdf_img/" + pdf_file.getName().split(Pattern.quote("."))[0] + "-" + (page+1) + ".png", 200);
		    System.out.println("Pagina " + (page+1) + " de " + document.getNumberOfPages() + " extraida");
		}
		document.close();
		ArrayList<Integer> rt = new ArrayList<Integer>();
		rt.add(qtd_img);
		rt.add(max_width);
		return rt;
	}
	
	private int max(int a, int b) {
		if(a > b) 
			return a;
		else
			return b;
	}

}
