import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    private String serverUrl;
    private String botName;
    
    public ConfigManager(String configPath) throws IOException {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(configPath)) {
            props.load(fis);
            this.serverUrl = props.getProperty("server.url");
            this.botName = props.getProperty("bot.name");
        }
    }
    
    public String getServerUrl() { return serverUrl; }
    public String getBotName() { return botName; }
}
