import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class AI {

    @SuppressWarnings("deprecation")
    public static String generateChatGPTResponse(String userPrompt) {
        String apiURL = "https://api.openai.com/v1/chat/completions";
        String apiKey = "key"; // put your key here
        String LLMname = "gpt-3.5-turbo";

        try {
            // Create URL object
            URL url = new URL(apiURL);
            // Open connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method to POST
            connection.setRequestMethod("POST");
            // Set request headers
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setRequestProperty("Content-Type", "application/json");

            // Create the request body
            String requestBody = "{\"model\": \"" + LLMname + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + userPrompt + "\"}]}";

            // Enable input/output streams
            connection.setDoOutput(true);
            // Write the request body
            try (OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream())) {
                writer.write(requestBody);
                writer.flush();
            }

            // Read the response
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                return getLLMResponse(response.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error interacting with the ChatGPT API: " + e.getMessage(), e);
        }
    }

    private static String getLLMResponse(String response) {
        int firstChar = response.indexOf("content") + 11;
        int lastChar = response.indexOf("\"", firstChar);
        return response.substring(firstChar, lastChar);
    }

    public static void main(String[] args) {
        String userPrompt = "Hi robot, this is my first API call! I'm so excited! Tell me a joke!";
        String chatGPTResponse = generateChatGPTResponse(userPrompt);
        System.out.println(chatGPTResponse);
    }
}
