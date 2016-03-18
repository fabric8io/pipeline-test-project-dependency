package io.fabric8;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WebPageMessageGenerator {
    static {
        loadProperties();
    }
    static Properties properties;
    private static void loadProperties() {
        properties = new Properties();
        InputStream in = WebPageMessageGenerator.class
                .getResourceAsStream("/project.properties");
        try {
            properties.load(in);
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getWelcomeMessage(){
        return "We're using the java dependency " + properties.getProperty("artifactId") + " version " + properties.getProperty("version");
    }
}
