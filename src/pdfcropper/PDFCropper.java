package pdfcropper;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

public class PDFCropper {

	public PDFCropper() { }

	public void run(String nomepdf, String destpdf) throws IOException {
		// extrai imagens do pdf
		File pdf = new File(nomepdf);
		PDFExtractor pde = new PDFExtractor(pdf);
		// Retorna [ quantidade de paginas, largura da maior pagina ]
		ArrayList<Integer> response_data = pde.extract();


		// corta cada imagem individualmente
		for(int i=1;i<=response_data.get(0);i++) {
			File nf = new File("pdf_img/" + nomepdf.split(Pattern.quote("."))[0] + "-" + i + ".png");
			BufferedImage img_file = ImageIO.read(nf);

			// se a largura da imagem + 40 for maior que a largura da maior imagem, ela sera cortada
			if(img_file.getWidth() > ((response_data.get(1)/2) + (response_data.get(1)/4))) {
				ImageCropper img = new ImageCropper(nf);
				img.crop();
			} else { // do contrario, apenas replica a imagem
				if(!(new File("cropped_img").exists()))
					new File("cropped_img").mkdir();

				File endf = new File("cropped_img", nomepdf.split(Pattern.quote("."))[0] + "-" + i + "_p1.png");
				ImageIO.write(img_file, "png", endf);
			}
			System.out.println("Recortando pagina " + i);
		}

		// salva o pdf
		WritePDF wp = new WritePDF();
		wp.create(destpdf);

		// apaga as pastas
		File p1 = new File("pdf_img");
		String[] p1_f = p1.list();
		for(String str : p1_f) {
			File act_f = new File(p1.getPath(), str);
			act_f.delete();
		}
		p1.delete();

		p1 = new File("cropped_img");
		p1_f = p1.list();
		for(String str : p1_f) {
			File act_f = new File(p1.getPath(), str);
			act_f.delete();
		}
		p1.delete();
	}

}
