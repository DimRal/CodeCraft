import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class AI {

    public static void main(String[] args) throws URISyntaxException {
        make_food(1);
    }

    public static String chatGPT(String message) throws URISyntaxException {
        String url = "https://api.openai.com/v1/chat/completions";
        //TODO: put your key
        String apiKey = ""; // Replace with your actual API key
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
            return " γευμα και τα υλικα που χρειζομαστε για αυτο το γευμα ";
        } else if (x == 2) {
            return  "θέλω ονομαστικά MONO τα υλικά , εκτός απο τα μπαχαρικα ," +
                    "για κάθε προϊόν, επιλέγοντας τον πιο γνωστό ή συνηθισμένο τύπο του(π.χ. φέτα, γάλα πλήρες, ψωμι ζεας...)," +
                    "μορφη απαντησης :αυγο, τυρι φιλαδελφια, ψωμι ζεας, ....," +
                    "Για το γεύμα ";
        } else if (x == 3) {
            return "πες μου ΜΟΝΟ ΤΟ ΟΝΟΜΑ του γεύματος";
        }else if (x == 4){
            return "θελω να μου πεις τησ θερμιδες του υλικου" +
                    "μορφη απαντησης: 456  (δηλαδη μονο εναν αριθμο)"+
                    "αν εχει δυο τυπους ενος φαγητου νσ πεις τι θερμιδεσ του πρωτου τυπου" +
                    "αν ποικιλουν οι θερμιδεσ τοτε πες τον μεσο ορο" +
                    "αναλογος το φαγητο πως μετριετε πρεπει: ανα 100 γραμμαρια , 100ml, ενα αυγο"+
                    "αν δηλαδη ειναι το υλικο στανταρ οπως το αυγο τοτε μετραμε ενα αυγο , αν ειναι μη αναμενομενο οπως μια μπιζολα,το γαλα..."+
                    "του υλικου ";
        }else if (x == 5){
            return "θελω να μου πεις τησ ΠΡΩΤΕΙΝΗ του υλικου" +
                    "μορφη απαντησης: 456  (δηλαδη μονο εναν αριθμο)"+
                    "αν εχει δυο τυπους ενος φαγητου νσ πεις τι ΠΡΩΤΕΙΝΗ του πρωτου τυπου" +
                    "αν ποικιλουν οι ΠΡΩΤΕΙΝΗ τοτε πες τον μεσο ορο" +
                    "αναλογος το φαγητο πως μετριετε πρεπει: ανα 100 γραμμαρια , 100ml, ενα αυγο"+
                    "αν δηλαδη ειναι το υλικο στανταρ οπως το αυγο τοτε μετραμε ενα αυγο , αν ειναι μη αναμενομενο οπως μια μπιζολα,το γαλα..."+
                    "του υλικου ";
        }else if (x == 6){
            return "θελω να μου πεις τους ΥΔΑΤΑΝΘΡΑΚΕΣ του υλικου" +
                    "μορφη απαντησης: 456  (δηλαδη μονο εναν αριθμο)"+
                    "αν εχει δυο τυπους ενος φαγητου νσ πεις τι ΥΔΑΤΑΝΘΡΑΚΕΣ του πρωτου τυπου" +
                    "αν ποικιλουν οι ΥΔΑΤΑΝΘΡΑΚΕΣ τοτε πες τον μεσο ορο" +
                    "αναλογος το φαγητο πως μετριετε πρεπει: ανα 100 γραμμαρια , 100ml, ενα αυγο"+
                    "αν δηλαδη ειναι το υλικο στανταρ οπως το αυγο τοτε μετραμε ενα αυγο , αν ειναι μη αναμενομενο οπως μια μπιζολα,το γαλα..."+
                    "του υλικου ";
        }else if (x == 7){
            return "θελω να μου πεις τα  ΛΙΠΑΡΑ του υλικου" +
                    "μορφη απαντησης: 456  (δηλαδη μονο εναν αριθμο)"+
                    "αν εχει δυο τυπους ενος φαγητου νσ πεις τι ΛΙΠΑΡΑ του πρωτου τυπου" +
                    "αν ποικιλουν οι ΛΙΠΑΡΑ τοτε πες τον μεσο ορο" +
                    "αναλογος το φαγητο πως μετριετε πρεπει: ανα 100 γραμμαρια , 100ml, ενα αυγο"+
                    "αν δηλαδη ειναι το υλικο στανταρ οπως το αυγο τοτε μετραμε ενα αυγο , αν ειναι μη αναμενομενο οπως μια μπιζολα,το γαλα..."+
                    "του υλικου ";
        }
        return "";
    }
    public static Macros[] make_food (int choose_meal) throws URISyntaxException {
        ArrayList<String> foodlist = new ArrayList<>();//λιστα υλικων
        ArrayList<Float> calorieslist = new ArrayList<>();//λιστα θερμιδων
        ArrayList<Float> proteinlist = new ArrayList<>();//λιστα πρωτεινων
        ArrayList<Float> carbslist = new ArrayList<>();//λιστα υδατανθρακων
        ArrayList<Float> fatlist = new ArrayList<>();//λιστα λιπαρων

        String meal=""; //αρχικοποιηση γευματος

        switch (choose_meal){//επιλογη τυπου γευματος μεσο των αριθμων 1,2,3,4,5
            case 1:
                meal = chatGPT("θελω ενα ΠΡΩΙΝΟ "+geumata(1));
                break;
            case 2:
                meal = chatGPT("θελω ενα ΔΕΚΑΤΙΑΝΟ " +geumata(1));
                break;
            case 3:
                meal = chatGPT("θελω ενα ΜΕΣΜΕΡΙΑΝΟ" +geumata(1));
                break;
            case 4:
                meal = chatGPT("θελω ενα ΑΠΟΓΕΥΜΑΤΙΝΑ" +geumata(1));
                break;
            case 5:
                meal = chatGPT("θελω ενα ΒΡΑΔΙΝΟ " +geumata(1));
                break;
        }

        String ingredients = (chatGPT(geumata(2) + meal));//τοποθετηση υλικων σε μεταβλητη
        String name_meal = (chatGPT(geumata(3) + meal));// ονομα γευματος

        //----τοποθετηση υλικων απο την μεταβλητη ingredients σε λιστα ξεχωριστα το ενα με το αλλο
        int x = 0;
        for (int i = 0; i < ingredients.length(); i++) {
            String ylika1 = ingredients;
            if (",".equals(String.valueOf(ylika1.charAt(i)))) {
                foodlist.add(ylika1.substring(x, i));
                x = i + 2;
            } else if (".".equals(String.valueOf(ylika1.charAt(i)))) {
                foodlist.add(ylika1.substring(x, i));
                x = i + 2;
            }
        }
        //----τοποθετηση υλικων απο την μεταβλητη ingredients σε λιστα ξεχωριστα το ενα με το αλλο


        Macros[] meal_panel = new Macros[ingredients.length()+1]; //πινακας που θα μπει ολοκληρωμενα το γευμα με τα μακρος
        int i =0;//αρχικοποιηση

        //να επαναλαμβανει μεχρι να σταμτησει να βγαζει eroor που γινεται λογο λαθος μορφης απαντησησ του AI
        //τρεχει η μεθοδος macro_numbers αρα το γευμα και τα υλικα ειναι ιδια γινεται η επαναληψη μονο στα μακρος
        do {
            try {
                meal_panel = macro_numbers(foodlist, calorieslist, proteinlist, carbslist, fatlist, name_meal);
                break;
            } catch (Exception e) {
                i=1;
            }
        }while ( i==0);
        return meal_panel;
    }

    public static Macros[] macro_numbers(List<String> food, List<Float> calories, List<Float> protein ,List<Float> carbs , List<Float> fat , String name_meal) throws URISyntaxException {
        //γεμησμα λιστων με τα μακρος τους
        for (int i = 0; i < food.size(); i++) {
            calories.add(Float.valueOf(chatGPT(geumata(4) + "του ΥΛΙΚΟΥ " + food.get(i) + "ΑΠΑΝΤΑ ΜΕ ΕΝΑΝ ΑΡΙΘΜΟ")));
            protein.add(Float.valueOf(chatGPT(geumata(5) + "του υλικου " + food.get(i) + "ΑΠΑΝΤΑ ΜΕ ΕΝΑΝ ΑΡΙΘΜΟ")));
            carbs.add(Float.valueOf(chatGPT(geumata(6) + "του υλικου " + food.get(i) + "ΑΠΑΝΤΑ ΜΕ ΕΝΑΝ ΑΡΙΘΜΟ")));
            fat.add(Float.valueOf(chatGPT(geumata(7) + "του υλικου " + food.get(i) + "ΑΠΑΝΤΑ ΜΟΝΟ ΜΕ ΕΝΑΝ ΑΡΙΘΜΟ")));
        }

        Macros[] meal_list = new Macros[food.size() + 1]; //μακροσ λιστα
        meal_list[0] = new Macros(name_meal); // αρχικοποιηση πρωτησ θεσησ με το ονομα του γευματος


        //καταχορουμε στο πινακα τα μακροσ ανα σειρα και υλικο με μακρος
        for (int i = 1; i < meal_list.length; i++) {
            meal_list[i] = new Macros(food.get(i - 1), calories.get(i - 1), protein.get(i - 1), carbs.get(i - 1), fat.get(i - 1));
        }

        // εκτυπωση για να ειμαστε σιγουροι οτι εγινε η δουλεια
        System.out.println(meal_list[0].name);
        for (int i = 1; i < meal_list.length; i++) {
            System.out.println(meal_list[i].name + ", " + meal_list[i].calories + ", " + meal_list[i].protein + ", " + meal_list[i].fat);
        }

        return meal_list;
    }
}


