package first_project_psut;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;


public class Home_Page  extends Data{
	

	Connection con;
	Statement stmt;
	ResultSet rs;
	
	
	

	@BeforeTest 
	public void setup() throws SQLException {
		super.setup();
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "Sara!Baddad");

	}
	
	
	
	@Test (priority=1, enabled=false)
	public void Add_data() throws SQLException {
		
		stmt = con.createStatement();
		String query = "INSERT INTO customers (customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, addressLine2, city, state, postalCode, country, salesRepEmployeeNumber, creditLimit) VALUES (9991, 'Raghad Obidat Trading', 'Obidat', 'Raghad', '+962-7-9000-1234', 'Al Rabieh Street', NULL, 'Amman', NULL, '11118', 'Jordan', 1370, 75000.00);";
		stmt.executeUpdate(query);
		
	}	
	
	@Test (priority=2, enabled=true)
	public void read_data() throws SQLException {
		stmt = con.createStatement();
		String query = "Select * from customers";
		
		rs=stmt.executeQuery(query);
		
		while(rs.next()) {
			firstname=rs.getString("contactFirstName");
			lastname=rs.getString("contactLastName");
			email = firstname+lastname+"@gmail.com";
			PostalCode=rs.getString("postalCode");
		}
		
	}
	

	

	@Test (priority = 3, enabled=false)
	public void signupwithdatabase() throws InterruptedException {
		//driver.navigate().to("https://automationteststore.com/index.php?rt=account/login");

		WebElement loginandregister =driver.findElement(By.linkText("Login or register"));

		loginandregister.click();

		WebElement continueBeforesignup=driver.findElement(By.xpath("//button[@title='Continue']"));
		continueBeforesignup.click();
		
		//webElements
		WebElement FirstName = driver.findElement(By.id("AccountFrm_firstname"));
		WebElement LasttName = driver.findElement(By.id("AccountFrm_lastname"));
		WebElement userEmail = driver.findElement(By.id("AccountFrm_email"));
		WebElement address1 = driver.findElement(By.id("AccountFrm_address_1"));
		WebElement city = driver.findElement(By.id("AccountFrm_city"));
		WebElement CountryDropDown=driver.findElement(By.id("AccountFrm_country_id"));
		WebElement StateDropDown=driver.findElement(By.id("AccountFrm_zone_id"));
		WebElement PostalInput=driver.findElement(By.id("AccountFrm_postcode"));
		WebElement LoginName = driver.findElement(By.id("AccountFrm_loginname"));
		WebElement Password = driver.findElement(By.id("AccountFrm_password"));
		WebElement ConfirmPassword = driver.findElement(By.id("AccountFrm_confirm"));
		WebElement agree_button = driver.findElement(By.id("AccountFrm_agree"));
		WebElement continueTosignup = driver.findElement(By.cssSelector(".btn.btn-orange.pull-right.lock-on-click"));

		
	//	//Actions
		FirstName.sendKeys(firstname);
		LasttName.sendKeys(lastname);
		userEmail.sendKeys(email);
		address1.sendKeys("Amman");
		city.sendKeys("randommm");
		Select country= new Select(CountryDropDown);
		country.selectByVisibleText("Jordan");
		Thread.sleep(2000);
		Select State= new Select(StateDropDown);
		int RANDSTATE= rand.nextInt(StateDropDown.findElements(By.tagName("option")).size());
		State.selectByIndex(RANDSTATE);
		PostalInput.sendKeys(PostalCode);
		LoginName.sendKeys(firstname+lastname);
		Password.sendKeys(pass);
		ConfirmPassword.sendKeys(pass);
		agree_button.click();
		continueTosignup.click();
		

		//Assertion
		Assert.assertEquals(driver.getCurrentUrl().contains("success"), true);
		Assert.assertEquals(driver.getPageSource().contains("Congratulations"), true);
		WebElement WelcomeMessageArea = driver.findElement(By.id("customernav"));
		Assert.assertEquals(WelcomeMessageArea.getText().contains(firstname), true);
		Thread.sleep(10000);

	}
	
	
	
	@Test (priority=4, enabled=false)
	public void update_data() throws SQLException{
		
		stmt = con.createStatement();
		String query = "UPDATE customers SET contactFirstName= 'sarab' WHERE customerNumber='9991'";
		stmt.executeUpdate(query);

		
	}
	
	@Test (priority=5, enabled=false)
	public void delete_data() throws SQLException {
		stmt=con.createStatement();
		String query ="DELETE phone FROM customers WHERE customerNumber='9991' ";
		stmt.executeUpdate(query);
		
	}
	
	
	@Test (priority = 6, enabled=true)
	public void logout() {
		
		driver.navigate().to("https://automationteststore.com/index.php?rt=account/logout");
		
		Assert.assertEquals(driver.getPageSource().contains(logoutconfirm), true);
		
	}
	
	
	@Test (priority=7, enabled=true )
	public void login() {
		WebElement loginandregister =driver.findElement(By.linkText("Login or register"));
		loginandregister.click();
		WebElement LoginName = driver.findElement(By.id("loginFrm_loginname"));
		WebElement PassTologin = driver.findElement(By.id("loginFrm_password"));

		LoginName.sendKeys(firstname+lastname);
		PassTologin.sendKeys(pass);
		driver.findElement(By.xpath("//button[@title = 'Login']")).click();
		

	}
	
	
	@Test (priority =8 , enabled=false, invocationCount=3)
	public void AddRandomItem () {
		
		driver.navigate().to("https://automationteststore.com/");
		
		int totalitems = driver.findElements(By.className("prdocutname")).size();
		int randomitem =rand.nextInt(totalitems);
		
		driver.findElements(By.className("prdocutname")).get(randomitem).click();
		
		if (driver.getCurrentUrl().contains("product_id=116")) {
			
			driver.findElement(By.id("option344747")).click();
		}
		
		
		
		driver.findElement(By.className("productpagecart")).click();
	}
	

	@Test (priority =9, enabled=false)
	public void cartpage () throws InterruptedException {
	driver.navigate().to("https://automationteststore.com/index.php?rt=checkout/cart");
	Thread.sleep(6000);
	}
	
	
	
	@AfterTest 
	public void AfterTestingDone() throws InterruptedException {
		Thread.sleep(3000);
		driver.close();
		
	}

}
