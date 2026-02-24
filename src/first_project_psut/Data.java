package first_project_psut;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Data {
	
	
	

	Random rand=new Random();
	WebDriver driver = new EdgeDriver();

	String firstname;
	String lastname;
	
	String PostalCode;
	
	String email;
	String pass="12341234";
	String logoutconfirm = "You have been logged off your account";
	
	Connection con;
	Statement stmt;
	ResultSet rs;
	
	
	
	
	

	public void setup() throws SQLException {
		driver.get("https://automationteststore.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}

	
}
