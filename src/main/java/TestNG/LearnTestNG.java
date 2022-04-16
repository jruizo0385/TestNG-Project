        package TestNG;

       import java.io.FileInputStream;
       import java.io.IOException;
       import java.io.InputStream;
       import java.util.Properties;
       import java.util.concurrent.TimeUnit;
       import org.openqa.selenium.By;
       import org.openqa.selenium.WebDriver;
       import org.openqa.selenium.WebElement;
       import org.openqa.selenium.chrome.ChromeDriver;
       import org.openqa.selenium.firefox.FirefoxDriver;
       import org.testng.Assert;
       import org.testng.annotations.AfterMethod;
       import org.testng.annotations.BeforeClass;
       import org.testng.annotations.Test;

        public class LearnTestNG {
	    WebDriver driver;
        String browser = "chrome";
        String url;
        public void readConfig() {
        Properties prop = new Properties();	
        try {
        InputStream	input = new FileInputStream("src\\main\\java\\config\\config.properties"); 
        prop.load(input);
        browser = prop.getProperty("browser");
        System.out.println("Browser used:" + browser);
        url = prop.getProperty("url");
        
        }catch(IOException e){
        e.printStackTrace();	
         }
        }
	    @BeforeClass
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
		driver.get("https://techfios.com/billing/?ng=login/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	     }

	    @Test
	    public void loginTest() throws InterruptedException {
	    Assert.assertEquals(driver.getTitle(),"Login - iBilling","Wrong Page");
	    	
	    WebElement USER_NAME_ELEMENT = driver.findElement(By.xpath("//input[@id='username']"));
		WebElement PASSWORD_ELEMENT = driver.findElement(By.xpath("//*[@id=\"password\"]"));
		WebElement SUBMIT_BOTTON_ELEMENT = driver.findElement(By.xpath("//button[@type='submit']"));

		
		String loginId = "demo@techfios.com";
		String password = "abc123";
	
		USER_NAME_ELEMENT.sendKeys(loginId);
		PASSWORD_ELEMENT.sendKeys(password);
		SUBMIT_BOTTON_ELEMENT.click();
		WebElement DASHBOARD_TITLE_ELEMENT = driver.findElement(By.xpath("//h2[contains(text(),'Dashboard')]"));
	    Assert.assertEquals(DASHBOARD_TITLE_ELEMENT.getText(),"Dashboard", "Wrong page");
	    
	    
	    
	     }
        @AfterMethod
        public void teardown() {
        driver.close();
        driver.quit();
     }
   }
       