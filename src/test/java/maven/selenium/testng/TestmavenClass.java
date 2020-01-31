package maven.selenium.testng;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestmavenClass {

	
	WebDriver driver;
	WebDriverWait wait_obj;
	
	@BeforeTest
	
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://www.gmail.com");
		wait_obj=new WebDriverWait(driver,10);
		
	}
	
	@Test(priority=1)
	public void verifyPageTitleTest() {
		String title=driver.getTitle();
		Assert.assertEquals(title, "Gmail");
		
	}
	
	@Test(priority=2)
	public void verifygmailbox() {
		WebElement email_box=driver.findElement(By.cssSelector("input[type='email']"));
		boolean flag=email_box.isDisplayed();
		Assert.assertTrue(flag);
		email_box.sendKeys("gaarakazakage123@gmail.com");
		
	} 
	
	@Test(priority=3)
	public void click_next() {
		driver.findElement(By.id("identifierNext")).click();
	}
	
	@Test(priority=4)
	 public void enter_password() {
		 WebDriverWait wait = new WebDriverWait(driver, 10);
		 WebElement element = wait.until(
				 ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='password']")));
		 WebElement password_box=driver.findElement(By.cssSelector("input[type='password']"));
		 password_box.click();
		 password_box.sendKeys("testing@1234");
		 
	 }
	
	@Test(priority=5)
	public void click_next2() {
		driver.findElement(By.id("passwordNext")).click();
	}
	
	@Test(priority=6)
	void compose_mail() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriverWait wait1=new WebDriverWait(driver,10);
		WebElement compose= wait1.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div[role='button'][gh='cm']"))));
		compose.click();
		
		
	}
	
	@Test(priority=7,dataProvider="dp")
	public void fill_id_of_reciever(String receiver_id, WebDriverWait wait_obj) {
		WebElement to_field= wait_obj.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("textarea[name='to']"))));
		to_field.sendKeys(receiver_id);
	}
	
	@Test(priority=8,dataProvider="dp1")
	void enter_subject(String subject) {
		driver.findElement(By.cssSelector("input[aria-label='Subject']")).sendKeys(subject);
	}
	
	@Test(priority=9,dataProvider="dp2")
	void enter_mail_body(String mail_body) {
		driver.findElement(By.cssSelector("div[aria-label='Message Body']")).sendKeys(mail_body);
		driver.findElement(By.cssSelector("div[role='button'][data-tooltip^='Send']")).click();
	}
	
	
	@Test(priority=10)
	void open_sent_box() {
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebDriverWait wait1=new WebDriverWait(driver,10);
		WebElement compose= wait1.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("a[title='Sent']"))));
		WebElement sent=driver.findElement(By.cssSelector("a[title='Sent']"));
		sent.click();
		
	}
	
	@Test(priority=11,dataProvider="dp1")
	void get_subject(String sub) {
		WebElement sent_list=driver.findElement(By.cssSelector("tr[class='zA yO']"));
		 sent_list.click();
		 String subject=driver.findElement(By.cssSelector("h2[class='hP']")).getText();
		 Assert.assertEquals(sub,subject,"Subject is not the same");
		 
	}
	
	
	
	@DataProvider
	public Object[][] dp(){
		
		  return new Object[][] {
		      new Object[] { "singhastha221@gmail.com", wait_obj }
		    };
		
	}
	@DataProvider
	public Object[] dp1(){
		
		  return new Object[] {
		       "subject"
		    };
		
	}
	@DataProvider
	public Object[] dp2(){
		
		return new Object[] {
		      "Mail_body to be sent"
		    };
		
	}
	
	@AfterTest
	public void tearUp() {
		driver.quit();
	}
	
}
