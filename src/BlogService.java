public class BlogService {
    private RestClient restClient;
    
    public BlogService(RestClient restClient) {
        this.restClient = restClient;
    }
    
    public String getAllBlogs() throws Exception {
        String response = restClient.sendGetRequest("?api=blogs");
        return formatBlogPosts(response);
    }
    
    public String createBlogPost(String title, String content, String author) throws Exception {
        String jsonBody = String.format(
            "{\"title\":\"%s\",\"content\":\"%s\",\"author\":\"%s\"}",
            escapeJson(title), escapeJson(content), escapeJson(author)
        );
        String response = restClient.sendPostRequest("?api=blogs", jsonBody);
        return formatCreatedPost(response);
    }
    
    public String getStatistics() throws Exception {
        String response = restClient.sendGetRequest("?api=stats");
        return formatStatistics(response);
    }
    
    private String escapeJson(String input) {
        if (input == null) return "";
        return input.replace("\\", "\\\\")
                   .replace("\"", "\\\"")
                   .replace("\n", "\\n")
                   .replace("\r", "\\r")
                   .replace("\t", "\\t");
    }
    
    private String formatBlogPosts(String jsonResponse) {
        // Simple formatting - in a real app you'd parse the JSON properly
        return jsonResponse
            .replace("{", "\n{")
            .replace("}", "}\n")
            .replace(",", ",\n ")
            .replace("\"title\"", "\nTitle: ")
            .replace("\"content\"", "\nContent: ")
            .replace("\"author\"", "\nAuthor: ")
            .replace("\"", "");
    }
    
    private String formatCreatedPost(String jsonResponse) {
        return jsonResponse
            .replace("{", "\nCreated Post:\n{")
            .replace("}", "}\n")
            .replace(",", ",\n ")
            .replace("\"title\"", "Title: ")
            .replace("\"content\"", "\nContent: ")
            .replace("\"author\"", "\nAuthor: ")
            .replace("\"", "");
    }
    
    private String formatStatistics(String jsonResponse) {
        return jsonResponse
            .replace("{", "\nBlog Statistics:\n{")
            .replace("}", "}\n")
            .replace(",", ",\n ")
            .replace("\"total_posts\"", "Total Posts: ")
            .replace("\"max_posts\"", "\nMax Posts Allowed: ")
            .replace("\"remaining_posts\"", "\nRemaining Posts: ")
            .replace("\"percentage_used\"", "\nPercentage Used: ")
            .replace("\"can_add_more\"", "\nCan Add More: ")
            .replace("\"", "");
    }
}