package com.zdz.ant.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SnapShotUtils {
	public static String getCurrentTime(){
		        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMddHHmms");//dd/MM/yyyy
		        Date now = new Date();
		        String strDate = sdfDate.format(now);
		        return strDate;
	}
	
    public void takeScreenShot(WebDriver driver,String testCaseName, boolean state) {
        System.out.println("start takeScreenShot");
    	try {
            String currenttime = this.getCurrentTime();
            File scrFile = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("screenshots" + File.separator
                    + testCaseName + currenttime + ".jpg"));
            Assert.assertTrue(state);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
