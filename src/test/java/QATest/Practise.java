package QATest;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Practise {

    @Test(enabled = false)
    public void abc() {
        System.out.println("hello");
    }

    @Test(description= "Test First" , priority = 2)
    public void firstM()
    {
        System.out.println("First Method");
        String a = "V";
        String b = "B";
        Assert.assertEquals(a,b);
    }

    @Test(description = "Test Third")
    public void thirdM()
    {
        System.out.println("Third Method");
    }

    @Test(description = "Test Second" , priority = 1)
    public void secondM()
    {
        System.out.println("Second Method");
    }


    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        File f = new File("/home/ttn/IdeaProjects/QATest/Properties/Prop.properties");
        FileInputStream fileInputStream = new FileInputStream(f);
        properties.load(fileInputStream);
        System.out.println(properties.getProperty("FName") + " " + properties.getProperty("LName"));
    }


}
