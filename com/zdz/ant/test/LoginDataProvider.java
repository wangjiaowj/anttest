package com.zdz.ant.test;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.DataProvider;

import com.zdz.ant.util.PageElementUtil;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class LoginDataProvider {
	PageElementUtil pg =new PageElementUtil();
	
	/*public LoginDataProvider() {
		// TODO Auto-generated constructor stub
	}*/
	@DataProvider(name="LoginAccountInfo")
	public static Object[][] getAccountinfo() throws BiffException, IOException{
		Object[][] retObjAr=getArray("Resource/AccountList.xls","Sheet1","AccountInfo");
		return(retObjAr);
	}
 
	public static Object[][] getArray(String filepath,String sheetName,String tableName) throws BiffException, IOException{
	      String[][] tabArray=null;        
	        Workbook workbook = Workbook.getWorkbook(new File(filepath));
	        Sheet sheet = workbook.getSheet(sheetName); 
	        int ci,cj,rowNum,colNum;
	        colNum=sheet.getColumns();
	        rowNum=sheet.getRows();
	        Cell tableEnd= sheet.getCell(colNum-1,rowNum-1);
	        tabArray=new String[rowNum-1][colNum];
	        ci=0;  
	        for (int i=1;i<rowNum;i++,ci++){
	        	cj=0;
	            for (int j=0;j<colNum;j++,cj++){
	                tabArray[ci][cj]=sheet.getCell(j,i).getContents();    
	                System.out.println(tabArray[ci][cj]);
	            }
	        }      
	        return(tabArray);
	    }
/*	public static void main(String[] args) throws BiffException, IOException {
		// TODO Auto-generated method stub
		LoginDataProvider lp=new LoginDataProvider();
		lp.getArray("Resource/AccountList.xls","Sheet1","AccountInfo");
	}*/
}
