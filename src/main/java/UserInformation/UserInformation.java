package UserInformation;

import Configuration.Configuration;

import static Configuration.Configuration.*;

public class UserInformation {

    Configuration configuration = new Configuration();

    private String url;
    private String executionPlatform;

    public UserInformation() {
        this.url = configuration.getProperty(URL);
        this.executionPlatform = configuration.getProperty(EXECUTION_PLATFORM);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExecutionPlatform() {
        return executionPlatform;
    }

    public void setExecutionPlatform(String executionPlatform) {
        this.executionPlatform = executionPlatform;
    }


}
