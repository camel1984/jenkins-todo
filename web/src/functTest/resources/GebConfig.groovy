import org.openqa.selenium.firefox.FirefoxDriver

waiting {
    timeout = 2
}

environments {
    firefox {
        atCheckWaiting = 1
        System.setProperty("webdriver.gecko.driver","/Users/shuaijie/Unix/commonLib/geckodriver");
        driver = { new FirefoxDriver() }
    }
}