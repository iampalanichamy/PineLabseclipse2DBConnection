package captchaManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Prac {

	public static void main(String[] args) {
		String str = "Interview Selenium Webdriver";



		String str1[] = str.split(" ");

		String str2="";

		

		

		for(int i=0;i<str1.length;i++)
		{
			StringBuffer buffer = new StringBuffer(str1[i]);
			
			str2=str2+buffer.reverse().toString()+ " ";
		}

		System.out.println(str2);
}
}
