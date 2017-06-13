package yahoo;

import java.io.FileInputStream;



import java.lang.reflect.Method;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;

@Listeners({ConfigurationListener.class,MethodListener.class,ATUReportsListener.class})
public class ReTest extends DriverClass
{
	//DesiredCapabilities ds;
	
	{
		System.setProperty("atu.reporter.config","d:\\atu.properties");
	}
    @Test
	@Parameters({"browser"})
	public void retesting(String str) throws Exception
	{
		if(str.matches("firefox"))
		{
			driver=new FirefoxDriver();
			//ds=DesiredCapabilities.firefox();
			//ds.setPlatform(Platform.WINDOWS);
		}
		if(str.matches("chrome"))
		{
			System.setProperty("webdriver.chrome.driver","d:\\chromedriver.exe");
			driver=new ChromeDriver();
			//ds=DesiredCapabilities.chrome();
			//ds.setPlatform(Platform.WINDOWS);
		}				
		//driver=new RemoteWebDriver(new URL("http://192.168.1.196:1234/wd/hub"),ds);
		try
		{
		FileInputStream fin=new FileInputStream("D:\\7AM_May_17\\testdata.xlsx");
		XSSFWorkbook wb=new XSSFWorkbook(fin);
		XSSFSheet ws=wb.getSheet("retest");
		Row row;
		for(int r=1;r<=ws.getLastRowNum();r++)
		{
			row=ws.getRow(r);
			if(row.getCell(4).getStringCellValue().matches("yes"))
			{
				 Class c=Class.forName(row.getCell(2).getStringCellValue());
				 Method m=c.getMethod(row.getCell(3).getStringCellValue(),null);
				 Object o=c.newInstance();
				 m.invoke(o,null);
			}
		}
		fin.close();
		}
		catch(Exception e){}		
	}
}
