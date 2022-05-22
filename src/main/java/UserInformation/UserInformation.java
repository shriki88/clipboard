package UserInformation;

import Configuration.Configuration;

public class UserInformation {

    Configuration configuration = new Configuration();

    private String url;

    public UserInformation()
    {
        this.url = configuration.getProperty("url");
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
