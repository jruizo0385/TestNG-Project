       package TestNG;

       import java.io.FileInputStream;
       import java.io.IOException;
       import java.io.InputStream;
       import java.util.Properties;
       import java.util.Random;
       import java.util.concurrent.TimeUnit;
       import org.openqa.selenium.By;
       import org.openqa.selenium.WebDriver;
       import org.openqa.selenium.WebElement;
       import org.openqa.selenium.chrome.ChromeDriver;
       import org.openqa.selenium.firefox.FirefoxDriver;
       import org.testng.Assert;
       import org.testng.annotations.AfterMethod;
       import org.testng.annotations.Test;

       public class HomeworTest {
	   WebDriver driver;
       String browser = "chrome";
       String url;
       public void readConfig() {
       Properties prop = new Properties();	
       try {
       InputStream	input = new FileInputStream("src\\main\\java\\config\\config.properties"); 
       prop.load(input);
       browser = prop.getProperty("browser");
       System.out.println("Browser used: " + browser);
       url = prop.getProperty("url");
       
       }catch(IOException e){
       e.printStackTrace();	
        }
       }
	    
	    public void init() {
	    
	    if(browser.equalsIgnoreCase("chrome")) {
	    System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();	
	    
	    }else if (browser.equalsIgnoreCase("Firefox")) {
		System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		}
	   
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("url");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	     }

	    @Test(priority=1)
	    public void loginTest() throws InterruptedException {
	    Assert.assertEquals(driver.getTitle(),"Login - iBilling","Wright Page!!!");
	    	
	    WebElement USER_NAME_ELEMENT = driver.findElement(By.xpath("//*[@id='username']"));
		WebElement PASSWORD_ELEMENT = driver.findElement(By.xpath("//*[@id='password']"));
		WebElement SUBMIT_BOTTON_ELEMENT = driver.findElement(By.xpath("//button[@type='submit']"));
		
		String loginId = "demo@techfios.com";
		String password = "abc123";
		USER_NAME_ELEMENT.sendKeys(loginId);
		PASSWORD_ELEMENT.sendKeys(password);
		SUBMIT_BOTTON_ELEMENT.click();
		
		WebElement DASHBOARD_TITLE_ELEMENT = driver.findElement(By.xpath("//h2[contains(text(),'Dashboard')]"));
	    Assert.assertEquals(DASHBOARD_TITLE_ELEMENT.getText(),"Dashboard", "Wrong page");
	    }
      
		@Test(priority=2)
	    public void addCustomer() {
        Assert.assertEquals(driver.getTitle(),"Login - iBilling","Wrong Page!!!");

    	By USER_NAME_FIELD = By.id("username");
    	By PASSWORD_FIELD = By.id("password");
    	By SIGN_IN_BUTTON = By.id("Login");
    	By CUSTOMER_BUTTON = By.xpath("//span[contains(text(),'Customers')]");
    	By ADD_CUSTOMER_BUTTON = By.xpath("//a[contains(text(),'Add Customer')]");
    	By FULL_NAME = By.xpath("//input[@name='account']");
    	By COMPANY_NAME = By.xpath(" //select[@id='cid']");
    	By EMAIL_FIELD = By.xpath(" //input[@id='email']");
    	By PHONE_NUMBER = By.xpath("//input[@id='phone']");
    	By ADDRESS = By.xpath("//input[@id='address']");
    	By CITY_FIELD = By.xpath("//input[@id='city']");
    	By STATE_FIELD = By.xpath("//input@id='state']");
    	By ZIP_CODE = By.xpath("//input[@id='zip']");
    	By COUNTRY_FIELD = By.xpath("//select[@name='country']");
    	By SAVE_BUTON = By.xpath("//button[@id='submit']']");
    	By LIST_CUSTOMER_FIELD = By.xpath("//a[contains(text(),'List Customer')]");
    	By SERCH_FIELD = By.xpath("//input[@id='foo_filter']");

    	String LoginId = "demo@techfios.com";
    	String password = "abc123";
    	String name = "Tina Turner";
    	String companyname = "Google";
    	String emailaccount = "abc999@gmail.com";
    	String phonenum = "720-456-8975";
    	String address = "2358 N NYC";
    	String city = "new york";
    	String state = "new york";
    	String zipcode = "10001";
    	String country = "united states";
    	String tagname = "historical fiction";


    	driver.findElement(USER_NAME_FIELD).sendKeys(LoginId);
    	driver.findElement(PASSWORD_FIELD).sendKeys(password);
    	driver.findElement(SIGN_IN_BUTTON).click();

    	Assert.assertEquals("Dashboard- iBilling", driver.getTitle());

    	driver.findElement(CUSTOMER_BUTTON).click();
    	waitforElement(driver, 5, ADD_CUSTOMER_BUTTON);
    	driver.findElement(ADD_CUSTOMER_BUTTON).click();

    	Random ran = new Random();
    	int randomNumbers = ran.nextInt(999);
    	waitforElement(driver, 5, FULL_NAME);

    	driver.findElement(FULL_NAME).sendKeys(name + randomNumbers);
    	driver.findElement(COMPANY_NAME).sendKeys(companyname);
    	driver.findElement(PHONE_NUMBER).sendKeys(phonenum + randomNumbers);
    	driver.findElement(EMAIL_FIELD).sendKeys(emailaccount + randomNumbers);
    	driver.findElement(ADDRESS).sendKeys(address + randomNumbers);
    	driver.findElement(CITY_FIELD).sendKeys(city + randomNumbers);
    	driver.findElement(STATE_FIELD).sendKeys(state + randomNumbers);
    	driver.findElement(ZIP_CODE).sendKeys(zipcode + randomNumbers);
    	driver.findElement(COUNTRY_FIELD).sendKeys(country + randomNumbers);
    	driver.findElement(SAVE_BUTON).click();
    	driver.findElement(LIST_CUSTOMER_FIELD).click();

    	Assert.assertEquals("contacts- iBilling", driver.getTitle());
        }
	    public void waitforElement(WebDriver driver, int i, By ADD_CUSTOMER_BUTTON) {	
		}
	    @AfterMethod
       public void teardown() {
       driver.close();
       driver.quit();
    }
  }
     
	   
	   
	   
	   
	   

