package captchaManager;
import java.io.BufferedReader;
import java.io.FileReader;

public class ReadText {




    public static String read_string = "";
	
	public static String read_a_file(String file_name)
	{
		BufferedReader br = null;
		
		 
		// System.out.println(read_string);
		try {
			
			String sCurrentLine;
			br = new BufferedReader(new FileReader(file_name));
			
			while ((sCurrentLine = br.readLine()) !=null)
			{
				read_string = read_string+sCurrentLine;
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		finally

	{
		try {
			if (br != null)
				br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		return read_string;
	}
}

