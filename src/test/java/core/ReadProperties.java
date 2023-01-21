package core;

import java.io.IOException;
import java.util.Properties;

public final class ReadProperties {
    protected static Properties properties;
    private static ReadProperties instance;

    private ReadProperties() {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ReadProperties getInstance() {
        if (instance == null) {
            instance = new ReadProperties();
        }
        return instance;
    }

    public String getURL() {
        return properties.getProperty("baseUrl");
    }

    public String getBrowserName() {
        return properties.getProperty("browser");
    }

    public boolean isHeadless() {
        return properties.getProperty("isHeadless").equalsIgnoreCase("true");
    }

    public int getTimeOut() {
        return Integer.parseInt(properties.getProperty("timeout"));
    }

    public int getPageLoadTimeout() {
        return Integer.parseInt(properties.getProperty("pageLoadTimeout"));
    }

    public String getBrowserSize() {
        return properties.getProperty("browserSize");
    }

    public String getUsername() {
        return properties.getProperty("userName");
    }

    public String getPassword() {
        return properties.getProperty("password");
    }

    public String getAPI_URL() {return properties.getProperty("api_url");}

    public String getAPI_Key() {
        return properties.getProperty("api_key");
    }
}
