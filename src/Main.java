public class Main {
    public static void main(String[] args) {
        try {
            // Load configuration
            ConfigManager config = new ConfigManager("config.properties");
            
            // Initialize services
            RestClient restClient = new RestClient(config.getServerUrl());
            BlogService blogService = new BlogService(restClient);
            
            // Start chat bot
            ChatBot bot = new ChatBot(config.getBotName(), blogService);
            bot.start();
        } catch (Exception e) {
            System.err.println("Error starting application: " + e.getMessage());
            e.printStackTrace();
        }
    }
}