import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;


public class AI {

    public static void main(String[] args) throws URISyntaxException {
        ArrayList<String> myList = new ArrayList<>();

        String name_brunch_1 = chatGPT(geumata(1));
        String ylika = (chatGPT(geumata(2) + name_brunch_1));
        String onoma = (chatGPT(geumata(3) + name_brunch_1));
        int x = 0;
        for (int i = 0; i < ylika.length(); i++) {
            String ylika1 = ylika;
            if (",".equals(String.valueOf(ylika1.charAt(i)))) {
                System.out.println(ylika1.substring(x, i));
                x = i + 2;
            } else if (".".equals(String.valueOf(ylika1.charAt(i)))) {
                System.out.println(ylika1.substring(x, i));
                x = i + 2;
            }
        }



    }

    public static String chatGPT(String message) throws URISyntaxException {
        String url = "https://api.openai.com/v1/chat/completions";
        //TODO: put your key
        String apiKey = "key"; // Replace with your actual API key
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

    public static String geumata(int x) {
        if (x == 1) {
            return "θέλω ενα πρωινο γευμα και το υλικα που χρειζομαστε για αυτο το γευμα ";
        } else if (x == 2) {
            return  "θέλω ονομαστικά MONO τα υλικά , εκτός απο τα μπαχαρικα ," +
                    "για κάθε προϊόν, επιλέγοντας τον πιο γνωστό ή συνηθισμένο τύπο του(π.χ. φέτα, γάλα πλήρες, ψωμι ζεας...)," +
                    "μορφη απαντησης :αυγο, τυρι φιλαδελφια, ψωμι ζεας, ....," +
                    "Για το γεύμα ";
        } else if (x == 3) {
            return "πες μου ΜΟΝΟ ΤΟ ΟΝΟΜΑ του γεύματος";
        }
        return "";
    }

}
