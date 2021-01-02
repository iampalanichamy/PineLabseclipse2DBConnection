/*
 * Version1
 * Author: Ankush Kalash
 * Created Date: 16 Dec 2016
 * Details: This class is used to read the values from an external properties file located at home path. File name is: Config.properties.
 * 			If a new field in added in external properties file, then this PropertyFileReader should also be updated.
 */

package pine.test.databasecon;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileReader 
{
	//Initialize Variables.
	private static String loggerPropertiesFile;
	private static String dbUser;
	private static String dbPassword;
	private static String databaseName;
	private static String jdbcDriver;
	private static String jdbcUrl;
	private static String browser;
	private static String baseurl;
	private static String mobNo;
	private static String shopBusinessNameValue;
	private static String contactPersonNameValue;
	private static String emailIdValue;
	private static String setPasswordValue;
	private static String setNewPasswordValue;
	private static String referralCodeValue;
	private static String reportName;
	private static String reportDocumentTitle;
	private static String logPath;
	
	public static void initPropertyFile(String getValue)
	{
		Properties prop = new Properties();

		try 
		{
			//load a properties file
			prop.load(new FileInputStream("configs//config.properties"));

			//get the property values
			if(getValue == "getloggerPropertiesFile")
			{
				try 
				{
					loggerPropertiesFile="configs//"+prop.getProperty("loggerPropertiesFile");
				}
				catch (Exception e) 
				{
					System.err.println("Logger path details not found in Configuration file.");
					e.printStackTrace();
					System.exit(1);
				}
			}

			if(getValue == "getDbUser")
			{
				try 
				{
					dbUser=prop.getProperty("dbUser");
				} 
				catch (Exception e) 
				{
					System.err.println("DB User details not found in Configuration file.");
					e.printStackTrace();
					System.exit(1);
				}
			}

			if(getValue == "getDbPassword")
			{
				try 
				{
					dbPassword=prop.getProperty("dbPassword");
				} 
				catch (Exception e) 
				{
					System.err.println("DB Password details not found in Configuration file.");
					e.printStackTrace();
					System.exit(1);
				}
			}

			if(getValue == "getDatabaseName")
			{
				try 
				{
					databaseName=prop.getProperty("databaseName");
				} 
				catch (Exception e) 
				{
					System.err.println("DatabaseName details not found in Configuration file.");
					e.printStackTrace();
					System.exit(1);
				}
			}

			if(getValue == "getJdbcDriver")
			{
				try 
				{
					jdbcDriver=prop.getProperty("jdbcDriver");
					System.out.println("Found JDBC Driver: "+jdbcDriver);
				} 
				catch (Exception e) 
				{
					System.err.println("jdbcDriver details not found in Configuration file.");
					e.printStackTrace();
					System.exit(1);
				}
			}

			if(getValue == "getJdbcUrl")
			{
				try 
				{
					jdbcUrl=prop.getProperty("jdbcUrl");
				} 
				catch (Exception e) 
				{
					System.err.println("jdbcUrl details not found in Configuration file.");
					e.printStackTrace();
					System.exit(1);
				}
			}

			if(getValue == "getBrowser")
			{
				try 
				{
					browser=prop.getProperty("browser");
					// Setting default browser as Firefox,if browser value is not proper.
					if(!((browser.equalsIgnoreCase("FIREFOX"))||(browser.equalsIgnoreCase("CHROME"))||(browser.equalsIgnoreCase("IE"))))
					{
						browser="FIREFOX";
					}
				} 
				catch (Exception e) 
				{
					System.err.println("browser details not found in Configuration file.");
					e.printStackTrace();
					System.exit(1);
				}
			}
			
			if(getValue == "getBaseUrl")
			{
				try 
				{
					baseurl=prop.getProperty("baseurl");
				} 
				catch (Exception e) 
				{
					System.err.println("URL details not found in Configuration file.");
					e.printStackTrace();
					System.exit(1);
				}
			}

			if(getValue == "mobNo")
			{
				try 
				{
					mobNo=prop.getProperty("mobNo");
				} 
				catch (Exception e) 
				{
					System.err.println("Mobile Number details not found in Configuration file.");
					e.printStackTrace();
					System.exit(1);
				}
			}
			if(getValue == "shopBusinessNameValue")
			{
				try 
				{
					shopBusinessNameValue=prop.getProperty("shopBusinessNameValue");
				} 
				catch (Exception e) 
				{
					System.err.println("Shop Business Name value not found in Configuration file.");
					e.printStackTrace();
					System.exit(1);
				}
			}
			if(getValue == "contactPersonNameValue")
			{
				try 
				{
					contactPersonNameValue=prop.getProperty("contactPersonNameValue");
				} 
				catch (Exception e) 
				{
					System.err.println("Contact Person Name value not found in Configuration file.");
					e.printStackTrace();
					System.exit(1);
				}
			}
			if(getValue == "emailIdValue")
			{
				try 
				{
					emailIdValue=prop.getProperty("emailIdValue");
				} 
				catch (Exception e) 
				{
					System.err.println("Email value not found in Configuration file.");
					e.printStackTrace();
					System.exit(1);
				}
			}
			if(getValue == "setPasswordValue")
			{
				try 
				{
					setPasswordValue=prop.getProperty("setPasswordValue");
				} 
				catch (Exception e) 
				{
					System.err.println("Password details not found in Configuration file.");
					e.printStackTrace();
					System.exit(1);
				}
			}
			if(getValue == "setNewPasswordValue")
			{
				try 
				{
					setNewPasswordValue=prop.getProperty("setNewPasswordValue");
				} 
				catch (Exception e) 
				{
					System.err.println("New password not found in Configuration file.");
					e.printStackTrace();
					System.exit(1);
				}
			}
			if(getValue == "referralCodeValue")
			{
				try 
				{
					referralCodeValue=prop.getProperty("referralCodeValue");
				} 
				catch (Exception e) 
				{
					System.err.println("Referral Code details not found in Configuration file.");
					e.printStackTrace();
					System.exit(1);
				}
			}
			if(getValue == "reportName")
			{
				try 
				{
					loggerPropertiesFile="configs//"+prop.getProperty("loggerPropertiesFile");
				}
				catch (Exception e) 
				{
					System.err.println("Logger path details not found in Configuration file.");
					e.printStackTrace();
					System.exit(1);
				}
			}
			if(getValue == "reportDocumentTitle")
			{
				try 
				{
					loggerPropertiesFile="configs//"+prop.getProperty("loggerPropertiesFile");
				}
				catch (Exception e) 
				{
					System.err.println("Logger path details not found in Configuration file.");
					e.printStackTrace();
					System.exit(1);
				}
			}
			if(getValue == "logPath")
			{
				try 
				{
					loggerPropertiesFile="configs//"+prop.getProperty("loggerPropertiesFile");
				}
				catch (Exception e) 
				{
					System.err.println("Logger path details not found in Configuration file.");
					e.printStackTrace();
					System.exit(1);
				}
			}
		}
		catch (IOException ex) 
		{
			System.err.println("Unable to connect/or read properties file.");
			ex.printStackTrace();
		}
	}

	// Get methods for above variables.
	public static String getloggerPropertiesFile() {
		if (loggerPropertiesFile == null)
		{
			initPropertyFile("getloggerPropertiesFile");
		}
		return loggerPropertiesFile;
	}
	public static String getDbUser() {
		if (dbUser == null)
		{
			initPropertyFile("getDbUser");
		}
		return dbUser;
	}
	public static String getDbPassword() {
		if (dbPassword == null)
		{
			initPropertyFile("getDbPassword");
		}
		return dbPassword;
	}
	public static String getDatabaseName() {
		if (databaseName == null)
		{
			initPropertyFile("getDatabaseName");
		}
		return databaseName;
	}
	public static String getJdbcDriver() {
		if (jdbcDriver == null)
		{
			initPropertyFile("getJdbcDriver");
		}
		return jdbcDriver;
	}
	public static String getJdbcUrl() {
		if (jdbcUrl == null)
		{
			initPropertyFile("getJdbcUrl");
		}
		return jdbcUrl;
	}
	public static String getBrowser() {
		if (browser == null)
		{
			initPropertyFile("getBrowser");
		}
		return browser;
	}
	public static String getBaseUrl() {
		if (baseurl == null)
		{
			initPropertyFile("getBaseUrl");
		}
		return baseurl;
	}

	public static String mobNo() {
		if (mobNo == null)
		{
			initPropertyFile("mobNo");
		}
		return mobNo;
	}
	public static String shopBusinessNameValue() {
		if (shopBusinessNameValue == null)
		{
			initPropertyFile("shopBusinessNameValue");
		}
		return shopBusinessNameValue;
	}
	public static String contactPersonNameValue() {
		if (contactPersonNameValue == null)
		{
			initPropertyFile("contactPersonNameValue");
		}
		return contactPersonNameValue;
	}
	public static String emailIdValue() {
		if (emailIdValue == null)
		{
			initPropertyFile("emailIdValue");
		}
		return emailIdValue;
	}
	public static String setPasswordValue() {
		if (setPasswordValue == null)
		{
			initPropertyFile("setPasswordValue");
		}
		return setPasswordValue;
	}
	public static String setNewPasswordValue() {
		if (setNewPasswordValue == null)
		{
			initPropertyFile("setNewPasswordValue");
		}
		return setNewPasswordValue;
	}
	public static String referralCodeValue() {
		if (referralCodeValue == null)
		{
			initPropertyFile("referralCodeValue");
		}
		return referralCodeValue;
	}
	public static String reportName() {
		if (reportName == null)
		{
			initPropertyFile("reportName");
		}
		return reportName;
	}
	public static String reportDocumentTitle() {
		if (reportDocumentTitle == null)
		{
			initPropertyFile("reportDocumentTitle");
		}
		return reportDocumentTitle;
	}
	public static String logPath() {
		if (logPath == null)
		{
			initPropertyFile("logPath");
		}
		return logPath;
	}
}
