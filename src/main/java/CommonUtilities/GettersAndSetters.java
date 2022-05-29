package CommonUtilities;

import static CommonUtilities.TestConfigurations.*;

public class GettersAndSetters {

    TestConfigurations configuration = new TestConfigurations();

    private String url;
    private String executionPlatform;
    private String allureCommand;
    private String serverCommand;
    private String killCommand;

    public GettersAndSetters() {
        this.url = configuration.getProperty(URL);
        this.executionPlatform = configuration.getProperty(EXECUTION_PLATFORM);
        this.allureCommand = configuration.getProperty(ALLURE_COMMAND);
        this.serverCommand = configuration.getProperty(SERVER_COMMAND);
        this.killCommand = configuration.getProperty(KILL_COMMAND);
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

    public String getAllureCommand() {
        return allureCommand;
    }

    public void setAllureCommand(String allureCommand) {
        this.allureCommand = allureCommand;
    }

    public String getServerCommand() {
        return serverCommand;
    }

    public void setServerCommand(String serverCommand) {
        this.serverCommand = serverCommand;
    }

    public String getKillCommand() {
        return killCommand;
    }

    public void setKillCommand(String killCommand) {
        this.killCommand = killCommand;
    }


}
