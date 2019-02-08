package com.auto.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


public class ROOperation {
	
	/* Initialize webdriver
	 * properties
	 * logs
	 * extent reports
	 * DB
	 * mail
	 *Excel*/
	
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	//public static Logger log=Logger.getLogger("ROOperation");
	public static String browser;
	
		public void setUp()
	{
		//BasicConfigurator.configure();
		//to avoid log4j warning
		if (driver == null){
			
			try {
				System.out.println(System.getProperty("user.dir")+ "\\src\\test\\resources\\properties\\Config.properties");
				fis = new FileInputStream(System.getProperty("user.dir")+ "\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				System.out.println();
			}
			try {
				config.load(fis);
				System.out.println("config file loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				//config.load(fis);
				System.out.println("OR file loaded");
				System.out.println(System.getenv("browser"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (System.getenv("browser")!= null)
				{
					browser = System.getenv("browser");
					
					
				}
		else{
					
					browser = config.getProperty("browser");
					
				}
		config.setProperty(browser, browser);
		if (config.getProperty("browser").equals("chrome"))
			
		{
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
			driver = new ChromeDriver();
			System.out.println("Chrome Launched !!!");
		}
		//System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\chromedriver_win32\\chromedriver.exe");
		//driver = new ChromeDriver();
		driver.get(config.getProperty("testsiteurl"));
		System.out.println("Navigated to : " + config.getProperty("testsiteurl"));
		driver.manage().window().maximize();
		
	}

	public void click(String locator) {

		if (locator.endsWith("_className")) {
			driver.findElement(By.className(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_xpath")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_id")) {
			driver.findElement(By.id(OR.getProperty(locator))).click();
		}
		
	}
	
	public void type(String locator, String value) {

		if (locator.endsWith("_className")) {
			driver.findElement(By.className(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_xpath")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_id")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
			System.out.println(driver.findElement(By.id(OR.getProperty(locator))).getText());
		}

			}
	
	public void loginDetails(String un,String pwd,String id)
	{
		type("un_id",un);
		type("pwd_id",pwd);
		type("ID_id",id);
		
		
	}

	public void clickLogin()
	{
		click("lb_xpath");
	} 

	public void selectStore()
	{
		click("account_className");
		type("storeid_xpath","256");
		driver.findElement(By.xpath("//input[@placeholder='Store ID']")).sendKeys(Keys.ENTER);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void createNewRO()
	{
		System.out.println("in createNewRO method");
		waitUntil(By.xpath("//a[contains(text(),'New RO')]"));
		System.out.println("========="+driver.findElement(By.xpath("//a[contains(text(),'New RO')]")).isDisplayed());
		driver.findElement(By.xpath("//a[contains(text(),'New RO')]")).click();
		//click("ro_xpath");
		System.out.println("clicked on new ro");
			
	}
	
	public void searchCust()
	{
		waitUntil(By.id("searchName"));
		type("serachcust_id","arige");
		System.out.print("========="+driver.findElement(By.id("searchName")).isDisplayed());
		click("search_button_xpath");
		waitUntil(By.xpath("//div[@id='customerSearchResults']//div[contains(@class,'customerItem')][1]//button"));
	}
	
	public void clickSearchResults()
	{
		click("search_results_xpath");
	System.out.println("serached customer");
	}
	
	public void selectVehicle()
	{
		System.out.println("selecting vehicle");
		waitUntil(By.xpath("//div[contains(@class,'vehicleItem')][1]//button"));
		click("select_vehicle_xpath");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		waitUntil(By.xpath("//div[@id='newDTROModal']//button[contains(text(),'Next')]"));
		System.out.println("click on next to add service");
		System.out.print("Next Button   "+driver.findElement(By.xpath("//button[contains(text(),'Next')]")).isDisplayed());
		click("next_but_xpath");
		//driver.findElement(By.xpath("//div[@ng-controller='LoadingCtrl']//div[@class='modal-footer']//button[contains(text(),'Next')]")).click();
		
	}
	
	public void addService()
	{
		waitUntil(By.id("AddServiceButton"));
		click("add_service_id");
		}
	
	public void selectServiceDD()
	{
		
		System.out.println("Please select DD    "+driver.findElement(By.xpath("//span[@class='select2-arrow']")).isDisplayed());
		waitUntil(By.xpath("//span[@class='select2-arrow']"));
		click("select_service_xpath");
	//driver.findElement(By.xpath("//div//a[@class='select2-choice']//span[contains(text(),'Please Select')]")).click();
	}
	
	public void selectFromDD()
	{
		waitUntil(By.xpath("//ul[@class='select2-results']"));
		List<WebElement> services = driver.findElements(By.xpath(OR.getProperty("services_list_xpath")));
		//System.out.println("No of services   "+services.size());
		for(WebElement service:services)
		{
			String serviceName = service.getText().replaceAll("\\s", "");
			//A/T - Accumulator&nbsp;&nbsp;&nbsp;&nbsp;
			System.out.println("entered for loop");
			
			System.out.println(service.getText());
			if(service.getText().contains("A/T - Accumulator")){
				
				service.click();
				break;
			}

		}
	}
	
	public void enterDetails()
	{
		type("service_details_id","Test RO");
		type("manager_note_xpath","Test RO");
		type("Serv_hrs_id","1");
		type("serv_lab_id","1");
		type("serv_parts_id","1");
		click("save_service_xpath");
	}
	
	public void summary()
	{
		waitUntil(By.xpath("//button[contains(text(),'Summary')]"));
		click("sum_but_xpath");
		waitUntil(By.xpath("//select[@ng-model='newRo.advisor']"));
		WebElement adv= driver.findElement(By.xpath(OR.getProperty("adv_xpath")));
		System.out.println("clicking on option");
		Select advisor = new Select(adv);
		advisor.selectByIndex(1);
		type("mileage_id","85410");
		
	}
	
	public void roSeq()
	{
		WebElement ro = driver.findElement(By.xpath(OR.getProperty("ro_xpath")));
		Select roele = new Select(ro);
		roele.selectByIndex(2);
		System.out.println("clicking on button");
		driver.findElement(By.xpath(OR.getProperty("ro_but_xpath"))).click();
		System.out.println("clicked on create ro button");
		
	}
	public void waitUntil(By bylocator)
	{
		WebDriverWait wait=new WebDriverWait(driver, 150);
		wait.until(ExpectedConditions.presenceOfElementLocated(bylocator));
	}
}
