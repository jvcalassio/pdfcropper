package pdfcropper;

public class Main {

	public static void main(String[] args){
		if(args.length != 2) {
			System.out.println("Uso: java -jar pdfcropper.jar <arquivo input.pdf> <arquivo output.pdf>");
			System.exit(0);
		}
		try {
			PDFCropper pdfr = new PDFCropper();
			pdfr.run(args[0], args[1]);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
