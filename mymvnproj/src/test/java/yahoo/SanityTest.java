package yahoo;

import java.io.FileInputStream;
import java.lang.reflect.Method;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;


import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SanityTest extends DriverClass
{
       
    @Test
	@Parameters({"browser"})
	public void sanitytesting(String str) throws Exception
	{
		if(str.matches("firefox"))
		{
			driver=new FirefoxDriver();
		}
		if(str.matches("chrome"))
		{
			System.setProperty("webdriver.chrome.driver","d:\\may_wk\\chromedriver.exe");
			driver=new ChromeDriver();
		}
		FileInputStream fin=new FileInputStream("D:\\7AM_May_17\\testdata.xlsx");
		XSSFWorkbook wb=new XSSFWorkbook(fin);
		XSSFSheet ws=wb.getSheet("sanitytest");
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
		
		/*Home h=new Home(driver);
		h.login();
		
		Compose c=new Compose(driver);
		c.sendmail();
		c.signout();*/
	}
}
