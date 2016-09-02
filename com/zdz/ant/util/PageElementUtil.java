package com.zdz.ant.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageElementUtil {
	public PageElementUtil() {
		// TODO Auto-generated constructor stub
	}

	public boolean isElementexist(WebDriver driver,By locator){
		try{
			driver.findElement(locator);
			
			return true;
		}catch(Exception e){
			return false;
		}
	
	}
	
	public static WebElement WaitForElement(WebDriver driver, By locator)
    {
        WebElement targetElement = (new WebDriverWait(driver,10)).until(ExpectedConditions.presenceOfElementLocated(locator));
        return targetElement;  
    }
}


