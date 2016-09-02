package com.zdz.ant.test;

import org.testng.annotations.Test;

import com.zdz.ant.util.PageElementUtil;
import com.zdz.ant.util.SnapShotUtils;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;

public class HelloAntTest {
	PageElementUtil pe = new PageElementUtil();
	SnapShotUtils ss = new SnapShotUtils();
	Actions ac = null;
	public static ChromeDriver driver = null;
	public static String browser;
	@FindBy(xpath = "//div[@id='u1']/a[@name='tj_login']")
	public static WebElement logBt;

	/*
	 * @Parameters({ "BROWSER" }) public void setup(String BROWSER) { browser =
	 * BROWSER; System.out.println("ttttt "+browser); if
	 * (browser.equalsIgnoreCase("FF")){
	 * System.setProperty("webdriver.firefox.bin",
	 * "D:/Program Files (x86)/Mozilla Firefox/firefox.exe"); driver = new
	 * FirefoxDriver(); } else if (browser.equalsIgnoreCase("CH")){
	 * System.setProperty("webdriver.chrome.driver", "tools/chromedriver.exe");
	 * driver=new ChromeDriver(); } driver.get("http://www.baidu.com");
	 * 
	 * }
	 */
	@BeforeClass(alwaysRun = true)
	public void setup() {
		System.out.println("fffff");
		System.setProperty("webdriver.chrome.driver", "E:/Eclipse/workspace/AntTestDemo/tools/chromedriver.exe");
		ChromeOptions co=new ChromeOptions();
		co.setBinary("C:/Users/T/AppData/Local/Google/Chrome/Application/chrome.exe");
		driver = new ChromeDriver(co);
		driver.get("http://www.baidu.com");
	}

	@DataProvider
	public Object[][] getArray() throws BiffException, IOException {
		String filepath = "Resource/AccountList.xls";
		String[][] tabArray = null;
		Workbook workbook = Workbook.getWorkbook(new File(filepath));
		Sheet sheet = workbook.getSheet(0);
		int ci, cj, rowNum, colNum;
		colNum = sheet.getColumns();
		rowNum = sheet.getRows();
		Cell tableEnd = sheet.getCell(colNum - 1, rowNum - 1);
		tabArray = new String[rowNum - 1][colNum];
		ci = 0;
		for (int i = 1; i < rowNum; i++, ci++) {
			cj = 0;
			for (int j = 0; j < colNum; j++, cj++) {
				tabArray[ci][cj] = sheet.getCell(j, i).getContents();
			}
		}
		return (tabArray);
	}

	@Test(dataProvider = "LoginAccountInfo", dataProviderClass = LoginDataProvider.class)
	public void f(String usern, String passwd, Method method) throws InterruptedException {
		ac = new Actions(driver);
		ss.takeScreenShot(driver, method.getName(), true);
		System.out.println("fdsafdsafdsa");
		Thread.sleep(5000);
		WebElement inputBox=driver.findElement(By.id("kw"));
		WebElement searchBt=driver.findElement(By.id("su"));
		inputBox.click();
		inputBox.sendKeys("git");
		Thread.sleep(5000);
		searchBt.click();
		/*
		WebElement loginbt = driver.findElement(By.xpath("//div[@id='u1']/a[@name='tj_login']"));
		loginbt.click();
		System.out.println(ss.getCurrentTime());
		WebElement un = pe.WaitForElement(driver,
				By.xpath("//form[@id='TANGRAM__PSP_8__form']/p/input[@id='TANGRAM__PSP_8__userName']"));
		System.out.println(ss.getCurrentTime());
		un.click();
		WebElement pd = driver
				.findElement(By.xpath("//form[@id='TANGRAM__PSP_8__form']/p/input[@id='TANGRAM__PSP_8__password']"));
		WebElement submitBt = driver
				.findElement(By.xpath("//form[@id='TANGRAM__PSP_8__form']/p/input[@id='TANGRAM__PSP_8__submit']"));
		un.sendKeys(usern);
		pd.click();
		pd.sendKeys(passwd);
		System.out.println("submit button is :"+pe.isElementexist(driver, By.xpath("//form[@id='TANGRAM__PSP_8__form']/p/input[@id='TANGRAM__PSP_8__submit']")));
		submitBt.click();
		Thread.sleep(5000);
		Boolean t = pe.isElementexist(driver, By.xpath("//div[@id='u1']/a[@name='tj_login']"));
		System.out.println(t);
		System.out.println("22222");
		*/
     /*   ac.moveToElement(driver.findElement(By.id("s_username_top"))).perform();
    	System.out.println("haha"+ss.getCurrentTime());
        driver.findElement(By.xpath("//id('s_user_name_menu')/div/a[1]")).click();*/
	}
	
	@AfterClass
	public void quitBrowser(){
		driver.quit();
	}

}
