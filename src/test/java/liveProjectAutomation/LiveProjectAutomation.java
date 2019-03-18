package liveProjectAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class LiveProjectAutomation {
    WebDriver driver;
    Properties properties;
//    Properties properties;
//    File f = new File("/home/ttn/IdeaProjects/QATest/Properties/Prop.properties");
//    FileInputStream fileInputStream = new FileInputStream(f);
//    LiveProjectAutomation() throws IOException {
//        System.setProperty("webdriver.chrome.driver","/home/ttn/IdeaProjects/selenium-testing/exe/chromedriver");
//        properties.load(fileInputStream);
//    }


    @BeforeSuite
    public void driver() throws IOException {
        System.setProperty("webdriver.chrome.driver","/home/ttn/IdeaProjects/selenium-testing/exe/chromedriver");
        driver = new ChromeDriver();
        properties = new Properties();
        File f = new File("/home/ttn/IdeaProjects/QATest/Properties/Prop.properties");
        FileInputStream fileInputStream = new FileInputStream(f);
        properties.load(fileInputStream);
    }


    @BeforeClass
    public void Register_with_Valid_Data() throws IOException {
//        driver = driver();
//        Properties properties = properties();
        driver.get("http://newtours.demoaut.com/");
        driver.findElement(By.xpath("//a[text()='REGISTER']")).click();
        System.out.println(properties.getProperty("State")+" "+ properties.getProperty("City"));
        driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(properties.getProperty("FName"));
        driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys(properties.getProperty("LName"));
        driver.findElement(By.xpath("//input[@name='phone']")).sendKeys(properties.getProperty("Phone"));
        driver.findElement(By.xpath("//input[@name='userName']")).sendKeys(properties.getProperty("Email"));
        driver.findElement(By.xpath("//input[@name='address1']")).sendKeys(properties.getProperty("Address"));
        driver.findElement(By.xpath("//input[@name='address2']")).sendKeys(properties.getProperty("Address"));
        driver.findElement(By.xpath("//input[@name='city']")).sendKeys(properties.getProperty("City"));
        driver.findElement(By.xpath("//input[@name='state']")).sendKeys(properties.getProperty("State"));
        driver.findElement(By.xpath("//input[@name='postalCode']")).sendKeys(properties.getProperty("Postal"));
        WebElement testdropdown = driver.findElement(By.xpath("//select[@name='country']"));
        Select dropdown = new Select(testdropdown);
        dropdown.selectByVisibleText(properties.getProperty("Country").toUpperCase());
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(properties.getProperty("UName"));
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(properties.getProperty("Pass"));
        driver.findElement(By.xpath("//input[@name='confirmPassword']")).sendKeys(properties.getProperty("Pass"));
        driver.findElement(By.xpath("//input[@name='register']")).click();
        String actual = driver.findElement(By.xpath("//b")).getText();
        String expected = "Dear "+properties.getProperty("FName")+" "+ properties.getProperty("LName")+",";
//        driver.close();
        Assert.assertEquals(actual,expected);
    }

    @BeforeMethod
    public void Login_with_valid_credentials() throws IOException {
//        WebDriver driver = driver();
        driver.get("http://newtours.demoaut.com/");
//        Properties properties = properties();
        driver.findElement(By.xpath("//input[@name='userName']")).sendKeys(properties.getProperty("UName"));
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(properties.getProperty("Pass"));
        driver.findElement(By.xpath("//input[@name='login']")).click();
        String actual = driver.getCurrentUrl();
//        System.out.println(actual);
        String expected = "http://newtours.demoaut.com/mercuryreservation.php";
//        driver.close();
        Assert.assertTrue(actual.contains(expected));
    }

    @Test(priority = 1)
    public void Flight_Booking_with_Valid_credentials() throws IOException {
//           WebDriver driver = driver();
//        driver.get("http://newtours.demoaut.com/");
//        Properties properties = properties();
        String type = properties.getProperty("FlightType");
        type = type.replace(" ","").toLowerCase();
        driver.findElement(By.xpath("//input[@value='"+type+"']")).click();

        WebElement passCount = driver.findElement(By.xpath("//select[@name='passCount']"));
        Select passenger = new Select(passCount);
        passenger.selectByVisibleText(properties.getProperty("Passengers"));

        WebElement fromPort = driver.findElement(By.xpath("//select[@name='fromPort']"));
        Select departingCity = new Select(fromPort);
        departingCity.selectByVisibleText(properties.getProperty("DepartingCity"));

        WebElement fromMonth = driver.findElement(By.xpath("//select[@name='fromMonth']"));
        Select onMonth = new Select(fromMonth);
        onMonth.selectByVisibleText(properties.getProperty("OnMonth"));

        WebElement fromDay = driver.findElement(By.xpath("//select[@name='fromDay']"));
        Select onDay = new Select(fromDay);
        onDay.selectByVisibleText(properties.getProperty("OnDate"));

        WebElement toPort = driver.findElement(By.xpath("//select[@name='toPort']"));
        Select arrivalCity = new Select(toPort);
        arrivalCity.selectByVisibleText(properties.getProperty("ArrivalCity"));

        WebElement toMonth = driver.findElement(By.xpath("//select[@name='toMonth']"));
        Select returnMonth = new Select(toMonth);
        returnMonth.selectByVisibleText(properties.getProperty("ReturnMonth"));

        WebElement toDay = driver.findElement(By.xpath("//select[@name='toDay']"));
        Select returnDate = new Select(toDay);
        returnDate.selectByVisibleText(properties.getProperty("ReturnDate"));

        driver.findElement(By.xpath("//input[@value='"+ properties.getProperty("Class") +"']")).click();

        driver.findElement(By.xpath("//input[@name='findFlights']")).click();
        driver.findElement(By.xpath("//input[@name='reserveFlights']")).click();

        driver.findElement(By.xpath("//input[@name='passFirst0']")).sendKeys(properties.getProperty("FName"));
        driver.findElement(By.xpath("//input[@name='passLast0']")).sendKeys(properties.getProperty("LName"));
        //meal
        WebElement meal = driver.findElement(By.xpath("//select[@name='pass.0.meal']"));
        Select mealPrefer = new Select(meal);
        mealPrefer.selectByVisibleText(properties.getProperty("Meal"));

        //cardtype
        WebElement creditCard = driver.findElement(By.xpath("//select[@name='creditCard']"));
        Select card = new Select(creditCard);
        card.selectByVisibleText(properties.getProperty("Card"));

        driver.findElement(By.xpath("//input[@name='creditnumber']")).sendKeys(properties.getProperty("CreditNumber"));
        //expiry
        WebElement expiryDate = driver.findElement(By.xpath("//select[@name='cc_exp_dt_mn']"));
        Select expiryD = new Select(expiryDate);
        expiryD.selectByVisibleText(properties.getProperty("ExpiryDate"));

        WebElement expiryYear = driver.findElement(By.xpath("//select[@name='cc_exp_dt_yr']"));
        Select expiryY = new Select(expiryYear);
        expiryY.selectByVisibleText(properties.getProperty("ExpiryYear"));

        driver.findElement(By.xpath("//input[@name='cc_frst_name']")).sendKeys(properties.getProperty("FName"));
        driver.findElement(By.xpath("//input[@name='cc_last_name']")).sendKeys(properties.getProperty("LName"));
        driver.findElement(By.xpath("//input[@name='billAddress1']")).sendKeys(properties.getProperty("Address"));
        driver.findElement(By.xpath("//input[@name='billCity']")).sendKeys(properties.getProperty("City"));
        driver.findElement(By.xpath("//input[@name='billState']")).sendKeys(properties.getProperty("State"));
        driver.findElement(By.xpath("//input[@name='billZip']")).sendKeys(properties.getProperty("Postal"));
//        driver.findElement(By.xpath("")).sendKeys(properties.getProperty(""));

//        WebElement billCountry = driver.findElement(By.xpath("//select[@name='billCountry']"));
//        Select country = new Select(billCountry);
//        country.selectByVisibleText(properties.getProperty("Country").toUpperCase());

        driver.findElement(By.xpath("//input[@name='buyFlights']")).click();

        String actual = driver.findElement(By.xpath("//b")).getText();
        String expected = "Your itinerary has been booked!";
        System.out.println();
        Assert.assertEquals(actual,expected);
    }

    @Test(priority = 2)
    public void Flight_Departure_Arrival_City() throws IOException {
//        Properties properties = properties();
        String type = properties.getProperty("FlightType");
        type = type.replace(" ","").toLowerCase();
        driver.findElement(By.xpath("//input[@value='"+type+"']")).click();

        WebElement passCount = driver.findElement(By.xpath("//select[@name='passCount']"));
        Select passenger = new Select(passCount);
        passenger.selectByVisibleText(properties.getProperty("Passengers"));

        WebElement fromPort = driver.findElement(By.xpath("//select[@name='fromPort']"));
        Select departingCity = new Select(fromPort);
        departingCity.selectByVisibleText(properties.getProperty("DepartingCity"));

        WebElement fromMonth = driver.findElement(By.xpath("//select[@name='toMonth']"));
        Select onMonth = new Select(fromMonth);
        onMonth.selectByVisibleText(properties.getProperty("OnMonth"));

        WebElement fromDay = driver.findElement(By.xpath("//select[@name='toDay']"));
        Select onDay = new Select(fromDay);
        onDay.selectByVisibleText(properties.getProperty("OnDate"));

        WebElement toPort = driver.findElement(By.xpath("//select[@name='toPort']"));
        Select arrivalCity = new Select(toPort);
        arrivalCity.selectByVisibleText(properties.getProperty("ArrivalCity"));

        WebElement toMonth = driver.findElement(By.xpath("//select[@name='fromMonth']"));
        Select returnMonth = new Select(toMonth);
        returnMonth.selectByVisibleText(properties.getProperty("ReturnMonth"));

        WebElement toDay = driver.findElement(By.xpath("//select[@name='fromDay']"));
        Select returnDate = new Select(toDay);
        returnDate.selectByVisibleText(properties.getProperty("ReturnDate"));

        driver.findElement(By.xpath("//input[@value='"+ properties.getProperty("Class") +"']")).click();
        String expected = "http://newtours.demoaut.com/mercuryreservation2.php";
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(actual,expected);

    }

    @Test(priority = 3)
    public void Flight_Arrival_Departure_date() throws IOException {
//        Properties properties = properties();
        String type = properties.getProperty("FlightType");
        type = type.replace(" ","").toLowerCase();
        driver.findElement(By.xpath("//input[@value='"+type+"']")).click();

        WebElement passCount = driver.findElement(By.xpath("//select[@name='passCount']"));
        Select passenger = new Select(passCount);
        passenger.selectByVisibleText(properties.getProperty("Passengers"));

        WebElement fromPort = driver.findElement(By.xpath("//select[@name='fromPort']"));
        Select departingCity = new Select(fromPort);
        departingCity.selectByVisibleText(properties.getProperty("DepartingCity"));

        WebElement fromMonth = driver.findElement(By.xpath("//select[@name='fromMonth']"));
        Select onMonth = new Select(fromMonth);
        onMonth.selectByVisibleText(properties.getProperty("OnMonth"));

        WebElement fromDay = driver.findElement(By.xpath("//select[@name='fromDay']"));
        Select onDay = new Select(fromDay);
        onDay.selectByVisibleText(properties.getProperty("OnDate"));

        WebElement toPort = driver.findElement(By.xpath("//select[@name='toPort']"));
        Select arrivalCity = new Select(toPort);
        arrivalCity.selectByVisibleText(properties.getProperty("DepartingCity"));

        WebElement toMonth = driver.findElement(By.xpath("//select[@name='toMonth']"));
        Select returnMonth = new Select(toMonth);
        returnMonth.selectByVisibleText(properties.getProperty("ReturnMonth"));

        WebElement toDay = driver.findElement(By.xpath("//select[@name='toDay']"));
        Select returnDate = new Select(toDay);
        returnDate.selectByVisibleText(properties.getProperty("ReturnDate"));

        driver.findElement(By.xpath("//input[@value='"+ properties.getProperty("Class") +"']")).click();
        String expected = "http://newtours.demoaut.com/mercuryreservation2.php";
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(actual,expected);
    }

    @Test(priority = 4)
    public void Flight_Booking_with_Invalid_Passenger_credentials() throws IOException {
//        Properties properties = properties();
        String type = properties.getProperty("FlightType");
        type = type.replace(" ","").toLowerCase();
        driver.findElement(By.xpath("//input[@value='"+type+"']")).click();

        WebElement passCount = driver.findElement(By.xpath("//select[@name='passCount']"));
        Select passenger = new Select(passCount);
        passenger.selectByVisibleText(properties.getProperty("Passengers"));

        WebElement fromPort = driver.findElement(By.xpath("//select[@name='fromPort']"));
        Select departingCity = new Select(fromPort);
        departingCity.selectByVisibleText(properties.getProperty("DepartingCity"));

        WebElement fromMonth = driver.findElement(By.xpath("//select[@name='fromMonth']"));
        Select onMonth = new Select(fromMonth);
        onMonth.selectByVisibleText(properties.getProperty("OnMonth"));

        WebElement fromDay = driver.findElement(By.xpath("//select[@name='fromDay']"));
        Select onDay = new Select(fromDay);
        onDay.selectByVisibleText(properties.getProperty("OnDate"));

        WebElement toPort = driver.findElement(By.xpath("//select[@name='toPort']"));
        Select arrivalCity = new Select(toPort);
        arrivalCity.selectByVisibleText(properties.getProperty("ArrivalCity"));

        WebElement toMonth = driver.findElement(By.xpath("//select[@name='toMonth']"));
        Select returnMonth = new Select(toMonth);
        returnMonth.selectByVisibleText(properties.getProperty("ReturnMonth"));

        WebElement toDay = driver.findElement(By.xpath("//select[@name='toDay']"));
        Select returnDate = new Select(toDay);
        returnDate.selectByVisibleText(properties.getProperty("ReturnDate"));

        driver.findElement(By.xpath("//input[@value='"+ properties.getProperty("Class") +"']")).click();

        driver.findElement(By.xpath("//input[@name='findFlights']")).click();
        driver.findElement(By.xpath("//input[@name='reserveFlights']")).click();
        driver.findElement(By.xpath("//input[@name='buyFlights']")).click();
        String expected = "http://newtours.demoaut.com/mercurypurchase.php";
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(actual,expected);
    }

    @AfterSuite
    public void closeAllTabs(){
        driver.quit();
    }

}
