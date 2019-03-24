package com.auto.qe.ui.utils;

public class DriverSetUpUtil {

    DriverSetUpUtil() {
    }

    public static void setToRunLocally() {
        if (System.getProperty("os.name").contains("Windows")) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chrome_driver/windows/chromedriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chrome_driver/" + System.getProperty("os.arch") + "/chromedriver");
        }
    }

}
