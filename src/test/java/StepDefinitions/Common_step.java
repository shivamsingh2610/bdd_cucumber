package StepDefinitions;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v125.network.Network;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

public class Common_step {
    public static WebDriver driver;
    public  static ExtentReports extent;
    public static ExtentTest logger;
    public static WebDriverWait wait;
    public static String ScreenShotName;
    public static Select select;
    public static Properties props;
    public static FileInputStream file;
    public static DevTools devtool;
    public static Boolean resp;
    //public static Robot rb;
    public static ChromeOptions options;
    public static SoftAssert softAssertion;
    public static JavascriptExecutor jsExecutor;

    public WebDriver getdriver()
    {
        return driver;
    }

    public ExtentTest getlogger()
    {
        return logger;
    }
    @BeforeTest
    public void check()
    {
        System.out.println("open browser");
    }
    @Before
    public void setup(Scenario scenario) throws InterruptedException, MalformedURLException {
        //System.out.println("test start");
        if(driver == null)
        {
            Thread.sleep(5000);
            if(System.getProperty("browser").equalsIgnoreCase("chrome")) {
                options = new ChromeOptions();
                options.addArguments("--headless=new");
                //driver = new ChromeDriver(options);
                driver = new ChromeDriver();
                ChromeOptions options = new ChromeOptions();

            }
            else
            {
                driver=new SafariDriver();
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        if(extent==null)
        {
            extent = new ExtentReports();
            Date d = new Date();
            String d1 = d.toString().replace(" ", "_").replace(":", "_");
            ExtentSparkReporter spark = new ExtentSparkReporter("report"+System.getProperty("env_value")+d1+".html");
            extent.attachReporter(spark);

        }
        logger=extent.createTest(scenario.getName());

        logger.log(Status.INFO,"Logged in for this "+scenario.getName());

    }

    @After
    public void tearDown(Scenario scenario) throws InterruptedException {
        Thread.sleep(2000);
        extent.flush();
        if(driver!=null)
        {
            driver.quit();
            System.out.println("test close");
        }
        driver=null;
    }
    @AfterTest
    public void closeBrowser()
    {
        System.out.println("Browser closed");
        driver.quit();
    }

    public static void clickwithborder(String xpath) {
        WebElement element=driver.findElement(By.xpath(xpath));
        if (element.isDisplayed())
        {
            jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
            jsExecutor.executeScript("arguments[0].style.border='2px solid red'", element);
            element.click();
        }
    }

    public static void borderwithsendkeys(String xpath, String data) {
        WebElement element = driver.findElement(By.xpath(xpath));
        if (element.isDisplayed()) {
            jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
            jsExecutor.executeScript("arguments[0].style.border='2px solid red'", element);
            element.sendKeys(data);
        }

    }
    public static void Send_data_with_placeholder_with_inputtag(String placeholder, String data) {
        WebElement element = driver.findElement(By.xpath("//*[@placeholder='"+placeholder+"']"));
        if (element.isDisplayed()) {
            jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
            jsExecutor.executeScript("arguments[0].style.border='2px solid red'", element);
            element.sendKeys(data);
        }

    }

    public static void putwaitclick(String Xpath) {
        WebElement element = driver.findElement(By.xpath(Xpath));
        wait = new WebDriverWait(driver, Duration.ofMillis(10));
        wait.until(ExpectedConditions.visibilityOf(element)).click();
    }

    public static void put_wait_and_send_data(String xpath,String data) {
        WebElement element=driver.findElement(By.xpath(xpath));
        wait = new WebDriverWait(driver, Duration.ofMillis(30));
        wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(data);
    }
    public static void clickwithcontains(String contains) {
        WebElement element = driver.findElement(By.xpath("//*[normalize-space()='" + contains + "']"));
        if (element.isDisplayed()) {
            jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
            jsExecutor.executeScript("arguments[0].style.border='2px solid red'", element);
            element.click();
        }
    }

    public static void ClickwithXpath(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        if (element.isDisplayed()) {
            jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
            jsExecutor.executeScript("arguments[0].style.border='2px solid red'", element);
            element.click();
        }
    }

    public static void clickwithText(String Text) {
        WebElement element = driver.findElement(By.xpath("//a[contains(text(),'" + Text + "')]"));
        if (element.isDisplayed()) {
            jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].style.border='2px solid red'", element);
            element.click();

        }
    }


    public static void waitAndClick(String Xpath, int time) {

        WebElement element = driver.findElement(By.xpath(Xpath));
        await().atMost(Duration.ofSeconds(time)).until(() -> {
                    return element.isDisplayed();
                }
        );
        element.click();
    }

    public static void waitandclickwithnotime(String Xpath) {

        WebElement element = driver.findElement(By.xpath(Xpath));
        await().atMost(Duration.ofSeconds(10)).until(() -> {
                    return element.isDisplayed();
                }
        );
        element.click();
    }

    public static void waitAndDisplay(WebElement element) {
        //WebElement element = driver().findElement(By.xpath(Xpath));
        await().until(() -> {
                    return element.isDisplayed();
                }
        );

    }
    public static void waitAndGetText(String Xpath) {
        WebElement element = driver.findElement(By.xpath(Xpath));
        await().until(() -> {
                    return element.isDisplayed();
                }
        );
        System.out.println(element.getText());
    }


    public static void getText(String Xpath) {
        WebElement element = driver.findElement(By.xpath(Xpath));
        String text = element.getText();
        System.out.println(text);
    }


    public static void checkBtnEnable(String Xpath) {
        WebElement element = driver.findElement(By.xpath(Xpath));
        if (element.isEnabled()) {
            System.out.println("Button is enabled");
        } else {
            System.out.println("Button is disabled");
        }
    }

    public static void checkBtnWithText(String Xpath, String ButtonName) {
        WebElement element = driver.findElement(By.xpath(Xpath));
        //System.out.println(element.getText()+" "+ButtonName);
        if (element.getText().equalsIgnoreCase(ButtonName)) {
            System.out.println("button text is matched");
        } else {
            System.out.println("button text is not matched");
        }

    }

    public static void clickonLinkText(String LinkText) {
        WebElement element = driver.findElement(By.linkText(LinkText));
        if (element.isDisplayed()) {
            jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].style.border='2px solid red'", element);
            element.click();
        }

    }

    public static void clickByjavaScript(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        if (element.isDisplayed()) {
            jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
            jsExecutor.executeScript("arguments[0].click();", element);
        }

    }

    public static void GetTextByJavascript(WebElement element) {
        //WebElement element = driver().findElement(By.xpath(xpath));
        {
            jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
            jsExecutor.executeScript("arguments[0].text;", element);
        }

    }

    public static void SendKeysByJavaScript(String xpath, String textvalue) {
        WebElement element = driver.findElement(By.xpath(xpath));
        if (element.isDisplayed()) {
            jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].value='" + textvalue + "';", element);
        }

    }

    public static void addScreemshot() throws IOException {
        String screen = screenShot();
        logger.log(Status.INFO, "Screenshot of this page", MediaEntityBuilder.createScreenCaptureFromPath(screen).build());
        //logger(.log(Status.INFO, MediaEntity.MediaEntityBuilder.<String>createScreenCaptureFromPath(screen).build());
    }

    public static void addScreemshot_with_somedetails(String details) throws IOException {
        String screen = screenShot();
        //String screen="https://tartan-assets.s3.ap-south-1.amazonaws.com/assets/images/batik-new-failed.png?response-content-disposition=inline&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEE0aCmFwLXNvdXRoLTEiRjBEAiAzOmJs4%2BoYvI5PtXinI5%2BYz2zSXILMKJDK2FDnrwF0rQIgWnFf5w6Tyf12BeKlzB%2BMZLjaRdUMSmr6CdrOZ1D69hAqgQMIFhABGgw0MTk4MzczMDk2NjciDMU0VC0zMLvA516oQireAnlfo7UIATHK5e%2BQhLbMBH9NLUOrEgvycc75Cf5crJCRtaw8nptwqH%2BOGI3d9h4jGb3Wkk45PpdsHRkKRRpa%2FUqfQBg5TMWC6mSrCo7ncgWJsM00sFDvTgCmN1w%2FQ0E5ob7oPLIGiXkF6epvZh0mkj%2Bq%2F%2F3DEke86RC326seAog0jgOp4C0g8XyjUyjs%2BHzJaOiVGBkEPJj2E8mBS8PeeOQBZprU4bxTzOfousGi9Z9kX%2B14KjQoU8zlgdL2rg0HYA2rO1DsFyrTvBEOw6ZgkdaqSTn2zVMvCPgCu3ncHktCH5rafC0iyL6%2FssA8V1AWAZr%2FVpskRDoFuRMn7np8UbSYXtHtA8XPwBRgVviu%2BL%2BKsfj%2FtqgdYD2YELBTpRV6dx%2FOOfHYX69gtZB359%2Fin867hpPiBhXOoOFhdRfYZPAl5drbJ1dw3gN%2FW6%2FWQBo8MZL0nQea9%2BL3qJzdo827MNG04q0GOrQC4zsx83lwqmoDb5MOALJcftdSXxNHG%2BFFsrugINTLLmrfvnUyrP9SZEj2es%2F%2FEc6DRCaT2O3fF620iE3uBUCko643EUwSnxCNf3aX3Y8gUAzLTuVczzKLU4uAnQNzltr8ruIlQ8hj4754OJUtLXQuAPXVdGCbceAJ50YK3taibJtHpGSgAoqMDsdcT3HW0X9qv4I0Wc1G%2BmmIEtRhXHGoDZss2olkSwoIgxhrgivOhRDN2AWeEB2toZzAMpG4eha4d3llc137ZCQdl7Xt%2B0AlO622%2BoVMfxIZpzXWwiJqA1kppC6ExTl13QxILsqSooiFz%2F7F473BBsx%2BAs6Qi1Tm1LKcQE7loKJ9ODh7zaFRpe4QP4WHftyY8Pg25CXFF4NtVEWtzOeh4NdZqHEyMp7M3VmzrF0%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20240130T131206Z&X-Amz-SignedHeaders=host&X-Amz-Expires=300&X-Amz-Credential=ASIAWDQCA6LRTBEMCSNA%2F20240130%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Signature=9cff1cfc6f39f29621485e52af2f84901af4535d6e791be47e93177f389b7994";
        logger.log(Status.INFO, details, MediaEntityBuilder.createScreenCaptureFromPath(screen).build());
    }


    public static void SelectByValue(WebElement element, String value) {
        //WebElement element = driver().findElement(By.xpath(selectvaluexpath));
        if (element.isDisplayed()) {
            select = new Select(element);
            select.selectByValue(value);
        }
    }

    public static void SelectByIndex(WebElement element, int index) {
        //WebElement element = driver().findElement(By.xpath(selectvaluexpath));
        if (element.isDisplayed()) {
            select = new Select(element);
            select.selectByIndex(index);
        }
    }

    public static void SelectByVisibleText(WebElement element, String text) {
        //WebElement element = driver().findElement(By.xpath(selectvaluexpath));
        if (element.isDisplayed()) {
            select = new Select(element);
            select.selectByVisibleText(text);
        }
    }

    public static void chromeNetworkResponse() {
        devtool = ((ChromeDriver) driver).getDevTools();
        devtool.createSession();
        devtool.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devtool.addListener(Network.responseReceived(), responseReceived ->
        {
            if ((responseReceived.getResponse().getStatus().toString().startsWith("40"))) {
                System.out.println("Api is not working correctly");
                System.out.println(responseReceived.getResponse().getUrl());
                System.out.println(responseReceived.getResponse().getUrl() + "Status code is " + responseReceived.getResponse().getStatus());
            }
            //System.out.println("-------------------------------->");
            //System.out.println(responseReceived.getResponse().getUrl()+"Status code is "+responseReceived.getResponse().getStatus());
        });
//

    }


    public void alertAccept() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }

    public static String screenShot() throws IOException {
        Date d = new Date();
        String d1 = d.toString().replace(" ", "_").replace(":", "_");
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File dest = new File("screen/pic_" + d1 + ".png");
        FileUtils.copyFile(src, dest);
        String finalpath = dest.getAbsolutePath();
        return finalpath;
    }

    public static String takeFullPageScreenshot() throws IOException {
        Date d = new Date();
        String d1 = d.toString().replace(" ", "_").replace(":", "_");
        String screenshotPath = "screen/fullPageScreenshot_" + d1 + ".png";

        Screenshot screenshot = new AShot()
                .coordsProvider(new WebDriverCoordsProvider())
                .shootingStrategy(ShootingStrategies.viewportPasting(1000))
                .takeScreenshot(driver);

        File screenshotFile = new File(screenshotPath);
        ImageIO.write(screenshot.getImage(), "PNG", screenshotFile);

        return screenshotFile.getAbsolutePath();
    }

    public static void add_fullpage_Screemshot_with_somedetails(String details) throws IOException {
        String screen = takeFullPageScreenshot();
        //String screen="https://tartan-assets.s3.ap-south-1.amazonaws.com/assets/images/batik-new-failed.png?response-content-disposition=inline&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEE0aCmFwLXNvdXRoLTEiRjBEAiAzOmJs4%2BoYvI5PtXinI5%2BYz2zSXILMKJDK2FDnrwF0rQIgWnFf5w6Tyf12BeKlzB%2BMZLjaRdUMSmr6CdrOZ1D69hAqgQMIFhABGgw0MTk4MzczMDk2NjciDMU0VC0zMLvA516oQireAnlfo7UIATHK5e%2BQhLbMBH9NLUOrEgvycc75Cf5crJCRtaw8nptwqH%2BOGI3d9h4jGb3Wkk45PpdsHRkKRRpa%2FUqfQBg5TMWC6mSrCo7ncgWJsM00sFDvTgCmN1w%2FQ0E5ob7oPLIGiXkF6epvZh0mkj%2Bq%2F%2F3DEke86RC326seAog0jgOp4C0g8XyjUyjs%2BHzJaOiVGBkEPJj2E8mBS8PeeOQBZprU4bxTzOfousGi9Z9kX%2B14KjQoU8zlgdL2rg0HYA2rO1DsFyrTvBEOw6ZgkdaqSTn2zVMvCPgCu3ncHktCH5rafC0iyL6%2FssA8V1AWAZr%2FVpskRDoFuRMn7np8UbSYXtHtA8XPwBRgVviu%2BL%2BKsfj%2FtqgdYD2YELBTpRV6dx%2FOOfHYX69gtZB359%2Fin867hpPiBhXOoOFhdRfYZPAl5drbJ1dw3gN%2FW6%2FWQBo8MZL0nQea9%2BL3qJzdo827MNG04q0GOrQC4zsx83lwqmoDb5MOALJcftdSXxNHG%2BFFsrugINTLLmrfvnUyrP9SZEj2es%2F%2FEc6DRCaT2O3fF620iE3uBUCko643EUwSnxCNf3aX3Y8gUAzLTuVczzKLU4uAnQNzltr8ruIlQ8hj4754OJUtLXQuAPXVdGCbceAJ50YK3taibJtHpGSgAoqMDsdcT3HW0X9qv4I0Wc1G%2BmmIEtRhXHGoDZss2olkSwoIgxhrgivOhRDN2AWeEB2toZzAMpG4eha4d3llc137ZCQdl7Xt%2B0AlO622%2BoVMfxIZpzXWwiJqA1kppC6ExTl13QxILsqSooiFz%2F7F473BBsx%2BAs6Qi1Tm1LKcQE7loKJ9ODh7zaFRpe4QP4WHftyY8Pg25CXFF4NtVEWtzOeh4NdZqHEyMp7M3VmzrF0%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20240130T131206Z&X-Amz-SignedHeaders=host&X-Amz-Expires=300&X-Amz-Credential=ASIAWDQCA6LRTBEMCSNA%2F20240130%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Signature=9cff1cfc6f39f29621485e52af2f84901af4535d6e791be47e93177f389b7994";
        logger.log(Status.INFO, details, MediaEntityBuilder.createScreenCaptureFromPath(screen).build());
    }

    public static String takeFullPageScreenshot_shutterbug(WebDriver driver) throws IOException {
        Date d = new Date();
        String d1 = d.toString().replace(" ", "_").replace(":", "_");
        String screenshotPath = "screens/fullPageScreenshot_" + d1;

        // Ensure the directory exists
        File directory = new File("screens");
        if (!directory.exists()) {
            directory.mkdirs();
        }
       // Shutterbug.shootPage(driver, Capture.FULL,true).save("screens1/fullPageScreenshot/");
        // Capture the full page screenshot and save it
        //Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS).withName("fullPageScreenshot_" + d1).save("screens");
        //Shutterbug.shootPage(driver,Capture.FULL_SCROLL).withName("fullPageScreenshot_" + d1).save("screens");
        return new File(screenshotPath).getAbsolutePath();
    }

    public static void add_fullpage_shutterbug_Screemshot_with_somedetails(String details) throws IOException {
        String screen = takeFullPageScreenshot_shutterbug(driver);
        //String screen="https://tartan-assets.s3.ap-south-1.amazonaws.com/assets/images/batik-new-failed.png?response-content-disposition=inline&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEE0aCmFwLXNvdXRoLTEiRjBEAiAzOmJs4%2BoYvI5PtXinI5%2BYz2zSXILMKJDK2FDnrwF0rQIgWnFf5w6Tyf12BeKlzB%2BMZLjaRdUMSmr6CdrOZ1D69hAqgQMIFhABGgw0MTk4MzczMDk2NjciDMU0VC0zMLvA516oQireAnlfo7UIATHK5e%2BQhLbMBH9NLUOrEgvycc75Cf5crJCRtaw8nptwqH%2BOGI3d9h4jGb3Wkk45PpdsHRkKRRpa%2FUqfQBg5TMWC6mSrCo7ncgWJsM00sFDvTgCmN1w%2FQ0E5ob7oPLIGiXkF6epvZh0mkj%2Bq%2F%2F3DEke86RC326seAog0jgOp4C0g8XyjUyjs%2BHzJaOiVGBkEPJj2E8mBS8PeeOQBZprU4bxTzOfousGi9Z9kX%2B14KjQoU8zlgdL2rg0HYA2rO1DsFyrTvBEOw6ZgkdaqSTn2zVMvCPgCu3ncHktCH5rafC0iyL6%2FssA8V1AWAZr%2FVpskRDoFuRMn7np8UbSYXtHtA8XPwBRgVviu%2BL%2BKsfj%2FtqgdYD2YELBTpRV6dx%2FOOfHYX69gtZB359%2Fin867hpPiBhXOoOFhdRfYZPAl5drbJ1dw3gN%2FW6%2FWQBo8MZL0nQea9%2BL3qJzdo827MNG04q0GOrQC4zsx83lwqmoDb5MOALJcftdSXxNHG%2BFFsrugINTLLmrfvnUyrP9SZEj2es%2F%2FEc6DRCaT2O3fF620iE3uBUCko643EUwSnxCNf3aX3Y8gUAzLTuVczzKLU4uAnQNzltr8ruIlQ8hj4754OJUtLXQuAPXVdGCbceAJ50YK3taibJtHpGSgAoqMDsdcT3HW0X9qv4I0Wc1G%2BmmIEtRhXHGoDZss2olkSwoIgxhrgivOhRDN2AWeEB2toZzAMpG4eha4d3llc137ZCQdl7Xt%2B0AlO622%2BoVMfxIZpzXWwiJqA1kppC6ExTl13QxILsqSooiFz%2F7F473BBsx%2BAs6Qi1Tm1LKcQE7loKJ9ODh7zaFRpe4QP4WHftyY8Pg25CXFF4NtVEWtzOeh4NdZqHEyMp7M3VmzrF0%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20240130T131206Z&X-Amz-SignedHeaders=host&X-Amz-Expires=300&X-Amz-Credential=ASIAWDQCA6LRTBEMCSNA%2F20240130%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Signature=9cff1cfc6f39f29621485e52af2f84901af4535d6e791be47e93177f389b7994";
        logger.log(Status.INFO, details, MediaEntityBuilder.createScreenCaptureFromPath(screen).build());
    }




    public static void list_comparison(List<WebElement> portal_list, List<String>hardcoded_list )
    {
        System.out.println("portal list size :- "+portal_list.size());
        System.out.println("hardcoded list size :-"+hardcoded_list.size());
        if(portal_list.size()==hardcoded_list.size()) {
            resp = true;
            List<String> strings = new ArrayList<String>();
            for (WebElement e : portal_list) {
                strings.add(e.getText());
            }
            Collections.sort(strings);
            Collections.sort(hardcoded_list);

            List<String> difference = new ArrayList<>(hardcoded_list);

            for (int i = 0; i <= hardcoded_list.size() - 1; i++) {
                if (hardcoded_list.get(i).equalsIgnoreCase(strings.get(i))) {

                    System.out.println("Responses are same");
                } else {

                    difference.removeAll(strings);

                    System.out.println("Response are different " + difference);
                    resp = false;
                }
            }
        }
        else {
            System.out.println("Response are not same ");
        }
    }
}
