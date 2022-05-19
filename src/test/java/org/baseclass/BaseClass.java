package org.baseclass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;
	static Actions actions;
	static JavascriptExecutor executor = (JavascriptExecutor) driver;
	static Select select;
//	static Alert alert = driver.switchTo().alert();
	
	public static void browserLaunch(String url) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get(url);
//		driver.manage().window().maximize();
	}
	public static WebElement findElementId(String id) {
		WebElement element = driver.findElement(By.id(id));
		return element;
	}
	public static WebElement findElementName(String name) {
		WebElement element = driver.findElement(By.name(name));
		return element;
	}
	public static WebElement findElementClassName(String className) {
		WebElement element = driver.findElement(By.className(className));
		return element;
	}
	public static WebElement findElementXpath(String xpath) {
		WebElement element = driver.findElement(By.xpath(xpath));
		return element;
	}
	public static void sendValues(WebElement element, String data) {
		element.sendKeys(data);
	}
	public static String getContent(WebElement element) {
		String text = element.getText();
		return text;
	}
	public static String attribute(WebElement element) {
		String attribute = element.getAttribute("value");
		return attribute;
	}
	public static void dragdrop(WebElement s, WebElement d) {
		actions = new Actions(driver);
		actions.dragAndDrop(s, d).perform();
	}
	public static void moveToElement(WebElement element) {
		actions = new Actions(driver);
		actions.moveToElement(element).perform();
	}
	public static void rightClick(WebElement element) {
		actions = new Actions(driver);
		actions.contextClick(element).perform();
	}
	public static void doubleClick(WebElement element) {
		actions = new Actions(driver);
		actions.doubleClick(element).perform();
	}
//	public static String getAlertText() {
//		String text = alert.getText();
//		return text;
//	}
//	public static void setAlertText(String text) {
//		alert.sendKeys(text);
//	}
	public static void screenshot(WebElement element) throws IOException {
		File s = element.getScreenshotAs(OutputType.FILE);
		File d = new File("D:\\Divya\\Maven/Output.png");
		FileUtils.copyFile(s, d);
	}
	public static void javascript(WebElement element, String data) {
		executor.executeScript("arguments[0].setAttribute('value',data)",element);
		Object name = executor.executeScript("return arguments[0].getAttribute('value')", element);
		System.out.println(name);
	}
	public static void selectIndex(WebElement element, int index) {
		select = new Select(element);
		select.selectByIndex(index);
	}
	public static void selectValue(WebElement element,  String value) {
		select = new Select(element);
		select.selectByValue(value);
	}
	public static void selectVisibleText(WebElement element,  String text) {
		select = new Select(element);
		select.selectByVisibleText(text);
	}
	public static void iterateOptions(WebElement element) {
		select = new Select(element);
		List<WebElement> options = select.getOptions();
		for (int i = 0; i < options.size(); i++) {
			WebElement element2 = options.get(i);
			String text = element2.getText();
			System.out.println(text);
		}
	}
	public static String getDataFromExcel(String pathName, String sheetName, int rowNo, int cellNo) throws IOException {
		String data = null;
		File file = new File(pathName);
		FileInputStream stream = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(stream);
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(rowNo);
		Cell cell = row.getCell(cellNo);
		int cellType = cell.getCellType();
		if (cellType==1) {
			data = cell.getStringCellValue();
		}
		if (cellType==0) {
			if (DateUtil.isCellDateFormatted(cell)) {
				Date date = cell.getDateCellValue();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
				data = dateFormat.format(date);
			} else {
				double d = cell.getNumericCellValue();
				long l = (long) d;
				data = String.valueOf(l);
			}
		}
		return data;
	}
	public void outputStream(String pathName, String sheetName, int rowNo, int cellNo, String input) throws IOException {
		File file = new File(pathName);
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet(sheetName);
		Row row = sheet.createRow(rowNo);
		Cell cell = row.createCell(cellNo);
		cell.setCellValue(input);
		FileOutputStream stream = new FileOutputStream(file);
		workbook.write(stream);
	}
	public void writeExisting(String pathName, String sheetName, int rowNo, int cellNo, String input) throws IOException, InterruptedException {
		File file = new File(pathName);
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(rowNo);
		Cell cell = row.createCell(cellNo);
		cell.setCellValue(input);
		Thread.sleep(100);
		FileOutputStream stream = new FileOutputStream(file);
		workbook.write(stream);
	}
	
	


}
