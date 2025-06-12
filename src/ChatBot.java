import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ChatBot {
    private String botName;
    private BlogService blogService;
    
    public ChatBot(String botName, BlogService blogService) {
        this.botName = botName;
        this.blogService = blogService;
    }
    
    public void start() {
      System.out.println("\n-----------------------------------------------");
        System.out.println(botName + ": Hello! I can help you manage blog posts.");
        System.out.println("-----------------------------------------------");
        
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                printMenu();
                String choice = reader.readLine();
                
                switch (choice) {
                    case "1":
                        viewAllBlogs();
                        break;
                    case "2":
                        createNewBlog(reader);
                        break;
                    case "3":
                        viewStatistics();
                        break;
                    case "4":
                        System.out.println(botName + ": Goodbye!");
                        return;
                    default:
                        System.out.println(botName + ": Invalid choice. Please try again.");
                }
                System.out.println("\n-----------------------------------------------");
            }
        } catch (Exception e) {
            System.out.println(botName + ": An error occurred - " + e.getMessage());
        }
    }
    
    private void printMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. View all blog posts");
        System.out.println("2. Create new blog post");
        System.out.println("3. View statistics");
        System.out.println("4. Exit");
        System.out.print("Enter your choice (1-4): ");
    }
    
    private void viewAllBlogs() throws Exception {
        System.out.println("\n" + botName + ": Fetching all blog posts...");
        String response = blogService.getAllBlogs();
        System.out.println(response);
    }
    
    private void createNewBlog(BufferedReader reader) throws Exception {
        System.out.println("\nCreate New Blog Post");
        System.out.print("Enter blog title: ");
        String title = reader.readLine();
        
        System.out.print("Enter blog content: ");
        String content = reader.readLine();
        
        System.out.print("Enter author name (leave blank for Anonymous): ");
        String author = reader.readLine();
        if (author.isEmpty()) {
            author = "Anonymous";
        }
        
        System.out.println("\n" + botName + ": Creating blog post...");
        String response = blogService.createBlogPost(title, content, author);
        System.out.println(response);
    }
    
    private void viewStatistics() throws Exception {
        System.out.println("\n" + botName + ": Fetching statistics...");
        String response = blogService.getStatistics();
        System.out.println(response);
    }
}