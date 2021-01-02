/**
 * 
 */
/**
 * @author lalit.kumar02
 *
 */
package captchaManager;
import java.io.PrintWriter;

import captchaManager.ReadText;
import captchaManager.SyncPipe;


public class CaptchaMain {

	public static void data(String a) {
		
		String input_file = a;
		String output_file = "D:\\testfiles\\"+1;//+LogURL.getDataFlow().getnumber();
		String tess_install_path = "D:\\Tesseract-OCR\\tesseract";

		String[] command =

				{

						"cmd", };
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
			new Thread(new SyncPipe(p.getInputStream(), System.out)).start();
			PrintWriter stdin = new PrintWriter(p.getOutputStream());

			stdin.println("\"" + tess_install_path + "\" \"" + input_file + "\" \"" + output_file + "\"");
			stdin.close();
			p.waitFor();
			//Data.h();
			//String a = Read_File.read_string;
			//System.out.println(a);
			//System.out.println();
			
			// System.out.println(1.);
			// System.out.println(2.);

			System.out.println(ReadText.read_a_file(output_file + ".txt"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
