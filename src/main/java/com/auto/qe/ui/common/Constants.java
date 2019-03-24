package com.auto.qe.ui.common;

public class Constants {
    // RUN_LOCALLY used when we are running it in our local machines, we can make it false when running it in the CI server

    public static final boolean RUN_LOCALLY = Boolean.parseBoolean(System.getProperty("run.locally", "true"));
    public static final String APP_URL = System.getProperty("app.url", "https://www.autohero.com/de/search/");
}