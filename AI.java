import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class AI {

    public static void main(String[] args) throws URISyntaxException {
        System.out.println(chatGPT("No you have to update"));
    }

    public static String chatGPT(String message) throws URISyntaxException {
        String url = "https://api.openai.com/v1/chat/completions";
        String apiKey = "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"; // Replace with your actual API key
        String model = "gpt-4o";

        try {
            URI uri = new URI(url);
            URL obj = uri.toURL();
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", "Bearer " + apiKey);
            con.setRequestProperty("Content-Type", "application/json");

            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + message + "\"}]}";
            con.setDoOutput(true);

            try (OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream())) {
                writer.write(body);
                writer.flush();
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // HTTP 200
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return extractContentFromResponse(response.toString());
            } else {
                BufferedReader errorStream = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                String inputLine;
                StringBuilder errorResponse = new StringBuilder();
                while ((inputLine = errorStream.readLine()) != null) {
                    errorResponse.append(inputLine);
                }
                errorStream.close();
                throw new RuntimeException("Request failed with HTTP code " + responseCode + ": " + errorResponse);
            }

        } catch (IOException e) {
            throw new RuntimeException("Connection error: " + e.getMessage(), e);
        }
    }

    public static String extractContentFromResponse(String response) {
        int startMarker = response.indexOf("content") + 11;
        int endMarker = response.indexOf("\"", startMarker);
        return response.substring(startMarker, endMarker);
    }
}
