package code.craft;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AI {
   
    public ArrayList<String> AI_meals(double weightKg, double heightCm, int age, String gender, double durationMin, String sport,String  target,String no_foods) throws URISyntaxException {
        //λιστα με ολα τα γευματα
        ArrayList<Macros[]> diet = new ArrayList<>();
        ArrayList<double[]> grammarias = new ArrayList<>();

        //μεταβλητη που καταχωρουντε τα γευματα ετσι ωστε να το διβαζει το ΑΙ και να μην τα ξανα χρησιμοποιει
        String ai_messege_input1 = "ΔΕΝ ΠΡΕΠΕΙ ΜΕ ΤΙΠΟΤΑ ,ΟΠΩΣ ΚΑΙ ΔΗΠΟΤΕ ΝΑ ΕΧΕΙ ΚΑΠΟΙΑ ΣΥΝΤΑΓΗ ΤΑ ΠΑΡΑΚΑΤΩ: "+no_foods+
                "ΤΟ ΓΕΥΜΑ ΠΟΥ ΘΑ ΦΤΙΑΞΕΙΣ ΠΡΠΕΙ ΝΑ ΜΗΝ ΜΟΙΑΖΕΙ ΟΥΤΕ ΜΕ ΛΙΓΟ ΜΕ ΤΑ ΠΑΡΑΚΑΤΩ:  ";
        String ai_messege_input2 = "ΔΕΝ ΠΡΕΠΕΙ ΜΕ ΤΙΠΟΤΑ ,ΟΠΩΣ ΚΑΙ ΔΗΠΟΤΕ ΝΑ ΕΧΕΙ ΚΑΠΟΙΑ ΣΥΝΤΑΓΗ ΤΑ ΠΑΡΑΚΑΤΩ: "+no_foods+
                "ΤΟ ΓΕΥΜΑ ΠΟΥ ΘΑ ΦΤΙΑΞΕΙΣ ΠΡΠΕΙ ΝΑ ΜΗΝ ΜΟΙΑΖΕΙ ΟΥΤΕ ΜΕ ΛΙΓΟ ΜΕ ΤΑ ΠΑΡΑΚΑΤΩ:  ";
        String ai_messege_input3 = "ΔΕΝ ΠΡΕΠΕΙ ΜΕ ΤΙΠΟΤΑ ,ΟΠΩΣ ΚΑΙ ΔΗΠΟΤΕ ΝΑ ΕΧΕΙ ΚΑΠΟΙΑ ΣΥΝΤΑΓΗ ΤΑ ΠΑΡΑΚΑΤΩ: "+no_foods+
                "ΤΟ ΓΕΥΜΑ ΠΟΥ ΘΑ ΦΤΙΑΞΕΙΣ ΠΡΠΕΙ ΝΑ ΜΗΝ ΜΟΙΑΖΕΙ ΟΥΤΕ ΜΕ ΛΙΓΟ ΜΕ ΤΑ ΠΑΡΑΚΑΤΩ:  ";


        //ΠΡΩΙΝΟ ΛΙΣΤΑ[0,1]
        System.out.println("----1)ΠΡΩΙΝΟ----");
        retryMakeFood(diet, 1, ai_messege_input1); // Πρωινό
        ai_messege_input1=ai_messege_input1+diet.get(0)[0].name +", ";
        System.out.println("\n");
        System.out.println("----2)ΠΡΩΙΝΟ----");
        retryMakeFood(diet, 1, ai_messege_input2); // Πρωινό
        ai_messege_input2=ai_messege_input2+diet.get(1)[0].name +", ";
        System.out.println("\n");


        //ΔΕΚΑΤΙΑΝΟ ΛΙΣΤΑ[2,3]
        System.out.println("----1)ΔΕΚΑΤΙΑΝΟ----");
        retryMakeFood(diet, 2, ai_messege_input1); // Δεκατιανό
        ai_messege_input1=ai_messege_input1+diet.get(2)[0].name +", ";
        System.out.println("\n");
        System.out.println("----2)ΔΕΚΑΤΙΑΝΟ----");
        retryMakeFood(diet, 2, ai_messege_input2); // Δεκατιανό
        ai_messege_input2=ai_messege_input2+diet.get(3)[0].name +", ";
        System.out.println("\n");


        //ΑΠΟΓΕΥΜΑΤΙΝΟ ΛΙΣΤΑ[4,5]
        System.out.println("----1)ΑΠΟΓΕΥΜΑΤΙΝΟ----");
        retryMakeFood(diet, 4, ai_messege_input1); // Απογευματινό
        ai_messege_input1=ai_messege_input1+diet.get(4)[0].name +", ";
        System.out.println("\n");
        System.out.println("----2)ΑΠΟΓΕΥΜΑΤΙΝΟ----");
        retryMakeFood(diet, 4, ai_messege_input2); // Απογευματινό
        ai_messege_input2=ai_messege_input2+diet.get(5)[0].name +", ";
        System.out.println("\n");


        //ΒΡΑΔΙΝΟ ΛΙΣΤΑ[6,7]
        System.out.println("----1)ΒΡΑΔΙΝΟ----");
        retryMakeFood(diet, 5, ai_messege_input1); // Βραδινό
        ai_messege_input1=ai_messege_input1+diet.get(6)[0].name +", ";
        System.out.println("\n");
        System.out.println("----2)ΒΡΑΔΙΝΟ----");
        retryMakeFood(diet, 5, ai_messege_input2); // Βραδινό
        ai_messege_input2=ai_messege_input2+diet.get(7)[0].name +", ";
        System.out.println("\n");

        //ΜΕΣΗΜΕΡΙΑΝΟ ΛΙΣΤΑ[8,9,10,11,12,13,14]
        System.out.println("----ΜΕΣΗΜΕΡΙΑΝΑ----");
        retryMakeFood(diet, 3, "ΑΥΤΟ ΘΕΛΩ ΝΑ ΕΙΝΑΙ  ΚΑΤΗΓΟΡΙΑ ΜΕ ΛΕΥΚΟ ΚΡΕΑΣ ΓΕΥΜΑ"+ai_messege_input3);
        ai_messege_input1=ai_messege_input1+diet.get(8)[0].name +", ";
        System.out.println("\n");
        retryMakeFood(diet, 3, "ΑΥΤΟ ΘΕΛΩ ΝΑ ΕΙΝΑΙ  ΚΑΤΗΓΟΡΙΑ ΜΕ ΛΕΥΚΟ ΚΡΕΑΣ ΓΕΥΜΑ"+ai_messege_input3); 
        ai_messege_input1=ai_messege_input1+diet.get(9)[0].name +", ";
        System.out.println("\n");
        retryMakeFood(diet, 3, "ΑΥΤΟ ΘΕΛΩ ΝΑ ΕΙΝΑΙ  ΚΑΤΗΓΟΡΙΑ ΜΕ ΚΟΚΚΙΝΟ ΚΡΕΑΣ ΓΕΥΜΑ"+ai_messege_input3); 
        ai_messege_input1=ai_messege_input1+diet.get(10)[0].name +", ";
        System.out.println("\n");
        retryMakeFood(diet, 3, "ΑΥΤΟ ΘΕΛΩ ΝΑ ΕΙΝΑΙ  ΚΑΤΗΓΟΡΙΑ ΛΑΔΕΡΟ ΓΕΥΜΑ"+ai_messege_input3);         
        ai_messege_input1=ai_messege_input1+diet.get(11)[0].name +", ";
        System.out.println("\n");
        retryMakeFood(diet, 3, "ΑΥΤΟ ΘΕΛΩ ΝΑ ΕΙΝΑΙ  ΚΑΤΗΓΟΡΙΑ ΟΣΠΡΙΑ ΓΕΥΜΑ"+ai_messege_input3);        
        ai_messege_input1=ai_messege_input1+diet.get(12)[0].name +", ";
        System.out.println("\n");
        retryMakeFood(diet, 3, "ΑΥΤΟ ΘΕΛΩ ΝΑ ΕΙΝΑΙ  ΚΑΤΗΓΟΡΙΑ ΟΣΠΡΙΑ ΓΕΥΜΑ"+ai_messege_input3);        
        ai_messege_input1=ai_messege_input1+diet.get(13)[0].name +", ";
        System.out.println("\n");
        retryMakeFood(diet, 3, "ΑΥΤΟ ΘΕΛΩ ΝΑ ΕΙΝΑΙ  ΚΑΤΗΓΟΡΙΑ ΨΑΡΙ ΓΕΥΜΑ"+ai_messege_input3);
        ai_messege_input1=ai_messege_input1+diet.get(14)[0].name +", ";
        System.out.println("\n");

        //ημερησια μακροθρεπτικα
        Macros macros=DAYS_MACROS( weightKg,  heightCm,  age,  gender,  durationMin,  sport,  target);
        optimazation app = new optimazation();

        for(int y=0;y<diet.size();y++){
            double percentageOfGoal1=0;
            if(y==0||y==1){
                percentageOfGoal1=20.0;
            }else if(y==2||y==3){
                percentageOfGoal1=10.0;
            }else if(y==4||y==5){
                percentageOfGoal1=15.0;
            }else if(y==6||y==7){
                percentageOfGoal1=20.0;
            }else{
                percentageOfGoal1=35.0;
            }
          
    

        grammarias.add(app.optimizeMealIngredients(diet.get(y), macros,percentageOfGoal1));
       
        } 
       

    
        return outPut(diet, grammarias);
    }
        
        


    
        
 
    
        //ΕΛΕΓΧΟΣ ΓΙΑ ΝΑ ΕΠΑΝΑΛΜΒΑΝΕΤΕ ΜΙΑ ΔΙΑΔΙΚΑΣΙΑ ΟΤΑΝ ΥΠΑΡΧΕΙ ΣΦΑΛΜΑ,ΚΑΙ ΣΥΓΚΕΚΡΗΜΕΝΑ ΓΙΑ ΤΙΣ ΛΑΘΟΣ ΑΠΟΑΝΤΗΣΕΙΣ ΤΟΥ ΑΙ
        public static void retryMakeFood(ArrayList<Macros[]> diet, int mealType, String requirements) {
            boolean success = false;
            while (!success) {
                try {
                    Macros []x = make_food(mealType, requirements);
                    if (x[0].name==null){
                        success=false;// Κλήση της make_food
                    }else{
                        diet.add(x);
                        success=true;
                    }// Αν πετύχει, τερματίζει
                } catch (Exception e) {
                    System.err.println("Προέκυψε σφάλμα: " + e.getMessage());
                    System.out.println("Επανάληψη προσπάθειας...");
                }
            }
        }
        //ΕΛΕΓΧΟΣ ΓΙΑ ΝΑ ΕΠΑΝΑΛΜΒΑΝΕΤΕ ΜΙΑ ΔΙΑΔΙΚΑΣΙΑ ΟΤΑΝ ΥΠΑΡΧΕΙ ΣΦΑΛΜΑ,ΚΑΙ ΣΥΓΚΕΚΡΗΜΕΝΑ ΓΙΑ ΤΙΣ ΛΑΘΟΣ ΑΠΟΑΝΤΗΣΕΙΣ ΤΟΥ ΑΙ
        public static double getMetValue(String activity) {
            switch (activity) {
                case "Περπάτημα": return 4.25; // Μέσος όρος περπατήματος
                case "Τρέξιμο": return 11.1; // Μέσος όρος τρεξίματος
                case "Ποδήλατο": return 8.625; // Μέσος όρος ποδηλασίας
                case "Κολύμβηση": return 9.0;
                case "Σχοινάκι": return 10.0;
                case "Βάρη": return 7.0;
                case "Γιόγκα": return 3.0;
                case "Πιλάτες": return 3.5;
                case "Χορός": return 6.0;
                case "CrossFit/HIIT": return 9.0;
                case "Αναρρίχηση": return 4.0;
                case "Ποδόσφαιρο": return 10.0;
                case "Μπάσκετ": return 8.0;
                case "Τένις": return 8.0;
                case "Βόλει(αγωνιστικό)": return 6.0;
                case "Πυγμαχία": return 12.0;
                case "Πολεμικές τέχνες": return 10.0;
                case "Καράτε": return 10.0;
                case "Τάε Κβο Ντο": return 11.0;
                case "Σκι": return 10.0;
                case "Κγιάκ": return 5.0;
                case "Κωπηλασία": return 10.0;
                case "Περπάτημα με βάρη": return 6.0;
                case "Πεζοπορία": return 9.0;
                case "": return 0.0;
                default: throw new IllegalArgumentException("Άγνωστη δραστηριότητα: " + activity);
            }
        }
    
    
        public static String chatGPT(String message) throws URISyntaxException {
            String url = "https://api.openai.com/v1/chat/completions";
            String apiKey = "" ; //put key here
            
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
            //μηνηματα που χρησιμοποιουμε στο ΑΙ για να μασ δωσει τησ απαντησεισ που θελουμε
            if (x == 1) {
                return "Γεύμα,με λιγα υλικα  και τα υλικά που χρειαζόμαστε για αυτό το γεύμα.";
            } else if (x == 2) {
                return "Θέλω να μου δώσεις μόνο τα υλικά του γεύματος αλλα πρωτο να βαλεισ το κυριο συστατικο τησ συνταγηςσε μορφή: " +
                        "υλικό, υλικό, υλικό, ... " +
                        "Πρέπει να παραλείψεις τα μπαχαρικά (π.χ., αλάτι, πιπέρι, ρίγανη). " +
                        "Αν ένα υλικό έχει πολλούς τύπους (π.χ., τυρί: φέτα, κασέρι), διάλεξε τον πιο κοινό ή συνηθισμένο τύπο. " +
                        "Η απάντηση πρέπει να είναι μόνο η λίστα των υλικών χωρισμένη με κόμματα, χωρίς τελείες ή άλλες επεξηγήσεις.";
            } else if (x == 3) {
                return "Πες μου ΜΟΝΟ ΤΟ ΟΝΟΜΑ του γεύματος.";
            } else if (x == 4) {
                return "Θέλω να μου δώσεις μόνο τον αριθμό των θερμίδων για το συγκεκριμένο υλικό. " +
                        "- Η απάντηση πρέπει να είναι ένας και μόνο αριθμός (π.χ., 155) που είναι τα γραμμάρια για ένα ή 100γρ ή 100ml του υλικού, χωρίς επιπλέον κείμενο, μονάδες μέτρησης ή εξηγήσεις. " +
                        "- Αν δεν μπορείς να βρεις την πληροφορία, απάντησε μόνο με τον αριθμό 0. " +
                        "- Μην περιλαμβάνεις καμία άλλη εξήγηση, πρόταση, σχόλιο ή πληροφορία. " +
                        "Παραδείγματα: " +
                        "1. Υλικό: ενα αυγό -> Απάντηση: 70 " +
                        "2. Υλικό: 100 γρ φέτα -> Απάντηση: 250 " +
                        "3. Υλικό: 100 ml γάλα πλήρες -> Απάντηση: 64";
            } else if (x == 5) {
                return "Θέλω να μου δώσεις μόνο τον αριθμό των πρωτεϊνών για το συγκεκριμένο υλικό. " +
                        "- Η απάντηση πρέπει να είναι ένας και μόνο αριθμός (π.χ., 12) που είναι τα γραμμάρια για ένα ή 100γρ ή 100ml του υλικού, χωρίς επιπλέον κείμενο, μονάδες μέτρησης ή εξηγήσεις. " +
                        "- Αν δεν μπορείς να βρεις την πληροφορία, απάντησε μόνο με τον αριθμό 0. " +
                        "- Μην περιλαμβάνεις καμία άλλη εξήγηση, πρόταση, σχόλιο ή πληροφορία. " +
                        "Παραδείγματα: " +
                        "1. Υλικό: αυγό -> Απάντηση: 12 " +
                        "2. Υλικό: φέτα -> Απάντηση: 14 " +
                        "3. Υλικό: γάλα πλήρες -> Απάντηση: 3.4";
            } else if (x == 6) {
                return "Θέλω να μου δώσεις μόνο τον αριθμό των υδατανθράκων για το συγκεκριμένο υλικό. " +
                        "- Η απάντηση πρέπει να είναι ένας και μόνο αριθμός (π.χ., 5) που είναι τα γραμμάρια για ένα ή 100γρ ή 100ml του υλικού, χωρίς επιπλέον κείμενο, μονάδες μέτρησης ή εξηγήσεις. " +
                        "- Αν δεν μπορείς να βρεις την πληροφορία, απάντησε μόνο με τον αριθμό 0. " +
                        "- Μην περιλαμβάνεις καμία άλλη εξήγηση, πρόταση, σχόλιο ή πληροφορία. " +
                        "Παραδείγματα: " +
                        "1. Υλικό: αυγό -> Απάντηση: 1.1 " +
                        "2. Υλικό: φέτα -> Απάντηση: 2 " +
                        "3. Υλικό: γάλα πλήρες -> Απάντηση: 5";
            } else if (x == 7) {
                return "Θέλω να μου δώσεις μόνο τον αριθμό των λιπαρών για το συγκεκριμένο υλικό. " +
                        "- Η απάντηση πρέπει να είναι ένας και μόνο αριθμός (π.χ., 10) που είναι τα γραμμάρια για ένα ή 100γρ ή 100ml του υλικού, χωρίς επιπλέον κείμενο, μονάδες μέτρησης ή εξηγήσεις. " +
                        "- Αν δεν μπορείς να βρεις την πληροφορία, απάντησε μόνο με τον αριθμό 0. " +
                        "- Μην περιλαμβάνεις καμία άλλη εξήγηση, πρόταση, σχόλιο ή πληροφορία. " +
                        "Παραδείγματα: " +
                        "1. Υλικό: αυγό -> Απάντηση: 10 " +
                        "2. Υλικό: φέτα -> Απάντηση: 21 " +
                        "3. Υλικό: γάλα πλήρες -> Απάντηση: 3.6";
            }else if (x == 8) {
                return  "Θέλω να μου δημιουργήσεις ΕΝΑ ΜΟΝΟ απλό, ισορροπημένο μεσημεριανό γεύμα με λίγα υλικά. Το γεύμα πρέπει να περιλαμβάνει πρωτεΐνες, φυτικές ίνες, καλά λιπαρά και βιταμίνες. " +
                        "Να αποφεύγεται το τηγανητό φαγητό και να προτιμώνται υγιεινές μέθοδοι μαγειρέματος (π.χ., ψητό, βραστό ή μαγειρευτό)." +
                        " Γενικές Οδηγίες που πρέπει να γνωριζεισ:" +
                        "1. 1 φορά την εβδομάδα ψάρι, πλούσιο σε ω-3 (π.χ., σολομός, σαρδέλες, σκουμπρί)." +
                        "2. 2 φορές την εβδομάδα λαδερά φαγητά (π.χ., φασολάκια, μπριάμ, μπάμιες, σπανακόρυζο)." +
                        "3. 1 φορά την εβδομάδα όσπρια (π.χ., φακές, ρεβίθια, φασόλια)." +
                        "4. Τις υπόλοιπες ημέρες κρέας:" +
                        "   - Μία φορά κόκκινο κρέας (π.χ., μοσχάρι ή χοιρινό)." +
                        "   - Τις άλλες ημέρες λευκό κρέας (π.χ., κοτόπουλο ή γαλοπούλα)." +
                        "Παραδείγματα γευμάτων:" +
                        "- Ψάρι: Ψητός σολομός με μπρόκολο στον ατμό, σαρδέλες με πατάτες φούρνου." +
                        "- Λαδερά: Φασολάκια με ελαιόλαδο, μπριάμ." +
                        "- Όσπρια: Φακές σούπα, ρεβίθια λεμονάτα." +
                        "- Κόκκινο κρέας: Μοσχαρίσια μπριζόλα με ρύζι." +
                        "- Λευκό κρέας: Ψητό στήθος κοτόπουλου με κινόα." +
                        "Τώρα θέλω ένα γεύμα από μία από αυτές τις κατηγορίες που να είναι μεσημεριανό και να πληροί τις παραπάνω οδηγίες.";
            }
            return "";
        }
    
        public static Macros[] make_food (int choose_meal,String requirements) throws URISyntaxException {
            ArrayList<String> foodlist = new ArrayList<>();//λιστα υλικων
            ArrayList<Double> calorieslist = new ArrayList<>();//λιστα θερμιδων
            ArrayList<Double> proteinlist = new ArrayList<>();//λιστα πρωτεινων
            ArrayList<Double> carbslist = new ArrayList<>();//λιστα υδατανθρακων
            ArrayList<Double> fatlist = new ArrayList<>();//λιστα λιπαρων
    
            String meal=""; //αρχικοποιηση γευματος
    
            switch (choose_meal){//επιλογη τυπου γευματος μεσο των αριθμων 1,2,3,4,5
                case 1:
                    meal = chatGPT("θελω ενα ΠΡΩΙΝΟ "+geumata(1)+ requirements);
                    break;
                case 2:
                    meal = chatGPT("θελω ενα ΔΕΚΑΤΙΑΝΟ " +geumata(1)+requirements);
                    break;
                case 3:
                    meal = chatGPT("θελω ενα ΜΕΣΜΕΡΙΑΝΟ" +geumata(8) + requirements);
                    break;
                case 4:
                    meal = chatGPT("θελω ενα ΑΠΟΓΕΥΜΑΤΙΝΑ" +geumata(1)+requirements);
                    break;
                case 5:
                    meal = chatGPT("θελω ενα ΒΡΑΔΙΝΟ " +geumata(1)+ requirements);
                    break;
            }
    
            String ingredients = (chatGPT(geumata(2) + meal));//τοποθετηση υλικων σε μεταβλητη
            String name_meal = (chatGPT(geumata(3) + meal));// ονομα γευματος
    
            //----τοποθετηση υλικων απο την μεταβλητη ingredients σε λιστα ξεχωριστα το ενα με το αλλο
            int x = 0;
            for (int i = 0; i < ingredients.length(); i++) {
                String recipe_ingredients = ingredients;
                if (",".equals(String.valueOf(recipe_ingredients.charAt(i)))) {
                    foodlist.add(recipe_ingredients.substring(x, i));
                    x = i + 2;
                } else if (".".equals(String.valueOf(recipe_ingredients.charAt(i)))) {
                    foodlist.add(recipe_ingredients.substring(x, i));
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
        private static double extractNumber(String response, Pattern pattern) {
            Matcher matcher = pattern.matcher(response);
            if (matcher.find()) {
                return Double.parseDouble(matcher.group());
            } else {
                throw new IllegalArgumentException("Δεν βρέθηκε αριθμός στην απάντηση: " + response);
            }
        }
    
        public static Macros[] macro_numbers(List<String> food, List<Double> calories, List<Double> protein ,List<Double> carbs , List<Double> fat , String name_meal) throws URISyntaxException {
            //γεμησμα λιστων με τα μακρος τους
            for (int i = 0; i < food.size(); i++) {
                String calorieResponse = chatGPT(geumata(4) + " του υλικού " + food.get(i) + " ΑΠΑΝΤΑ ΜΕ ΕΝΑΝ ΑΡΙΘΜΟ");
                String proteinResponse = chatGPT(geumata(5) + " του υλικού " + food.get(i) + " ΑΠΑΝΤΑ ΜΕ ΕΝΑΝ ΑΡΙΘΜΟ");
                String carbResponse = chatGPT(geumata(6) + " του υλικού " + food.get(i) + " ΑΠΑΝΤΑ ΜΕ ΕΝΑΝ ΑΡΙΘΜΟ");
                String fatResponse = chatGPT(geumata(7) + " του υλικού " + food.get(i) + " ΑΠΑΝΤΑ ΜΟΝΟ ΜΕ ΕΝΑΝ ΑΡΙΘΜΟ");
    
                Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");
    
                double cal = extractNumber(calorieResponse, pattern);
                double prot = extractNumber(proteinResponse, pattern);
                double carb = extractNumber(carbResponse, pattern);
                double ft = extractNumber(fatResponse, pattern);
    
                calories.add(cal);
                protein.add(prot);
                carbs.add(carb);
                fat.add(ft);
            }
    
            Macros[] meal_list = new Macros[food.size() + 1]; //μακροσ λιστα
            meal_list[0] = new Macros(name_meal); // αρχικοποιηση πρωτησ θεσησ με το ονομα του γευματος
    
    
            //καταχορουμε στο πινακα τα μακροσ ανα σειρα και υλικο με μακρος
            for (int i = 1; i < meal_list.length; i++) {
                meal_list[i] = new Macros(food.get(i - 1), calories.get(i - 1), protein.get(i - 1), carbs.get(i - 1), fat.get(i - 1));
            }
            return meal_list;
        }
    
        public static Macros DAYS_MACROS(double weightKg, double heightCm, int age, String gender, double durationMin, String sport, String target) throws URISyntaxException {
            // Υπολογισμός BMR
            double BMR = gender.equalsIgnoreCase("Male")
                    ? 10 * weightKg + 6.25 * heightCm - 5 * age + 5
                    : 10 * weightKg + 6.25 * heightCm - 5 * age - 161;
    
    
            // Υπολογισμός θερμίδων καύσης
            double caloriesBurned = getMetValue(sport) * 0.0175 * weightKg * durationMin;
    
            // Συνολικές Θερμίδες
            double totalCalories = BMR + caloriesBurned;
    
            if (target == "Lose weight") {  // Απώλεια Βάρους
                totalCalories = (BMR * 0.85) + caloriesBurned;
            } else if (target == "Gain muscle") {  // Αύξηση Βάρους
                totalCalories = (BMR * 1.15) + caloriesBurned;
            }
    
            // Υπολογισμός Μακροθρεπτικών Συστατικών
            double proteinCalories = totalCalories * 0.25;  // 25% Πρωτεΐνες
            double carbCalories = totalCalories * 0.50;    // 50% Υδατάνθρακες
            double fatCalories = totalCalories * 0.25;    // 25% Λίπη
    
            long proteinGrams = Math.round(proteinCalories / 4);   // kcal/γρ
            long carbGrams = Math.round(carbCalories / 4);        // kcal/γρ
            long fatGrams = Math.round(fatCalories / 9);          // kcal/γρ
            long roundedCalories = Math.round(totalCalories/1);
    
            System.out.println("Ηλικία: " + age + " ετών");
            System.out.println("Θερμίδες Καύσης: " + totalCalories + " kcal");
            System.out.println("Πρωτεΐνες: " + proteinGrams + " γρ");
            System.out.println("Υδατάνθρακες: " + carbGrams + " γρ");
            System.out.println("Λίπη: " + fatGrams + " γρ");
    
            // Επιστροφή Αντικειμένου Macros
            return new Macros(String.valueOf(age), roundedCalories, proteinGrams, carbGrams, fatGrams, 0.0);
        }
        public static ArrayList<String> outPut(ArrayList<Macros[]> diet, ArrayList<double[]> grammarias) {
            StringBuilder output = new StringBuilder();
            ArrayList<String> program = new ArrayList<>();
            String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
            
            output.append("\n   Weekly Meal Plan\n\n   =================\n");
            
            for (int i = 0; i < days.length; i++) {
                output.setLength(0);
                output.append("  -----" + days[i] + "-----  \n\n");
                for (int x = 1; x < 6; x++) {
                    output.append("Meal " + x + ": \n");
                    output.append(outPut_Help(diet, grammarias, x, i));
                    output.append("\n");
                }
                program.add(output.toString());
            }
            return program;
        }
    
        public static String outPut_Help(ArrayList<Macros[]> diet, ArrayList<double[]> grammarias, int x, int i) {
            StringBuilder output = new StringBuilder();
            
            switch (x) {
                case 1: {
                    if (i == 0 || i == 2 || i == 4 || i == 6) {
                        output.append("Name-> " + diet.get(0)[0].name);
                        for (int y = 1; y < diet.get(0).length; y++) {
                            if (Math.round(grammarias.get(0)[y - 1] * 100) > 250) {
                                output.append("\n   -" + diet.get(0)[y].name + " " + (1.8 * 100) + "γρ.\n");
                            } else if (Math.round(grammarias.get(0)[y - 1] * 100)< 20) {
                                output.append("\n   -" + diet.get(0)[y].name + " " + (Math.round((grammarias.get(0)[y - 1]+y/10.0) * 100)) + "γρ.\n");
                            }else {
                                output.append("\n   -" + diet.get(0)[y].name + " " + Math.round(grammarias.get(0)[y - 1] * 100) + "γρ.\n");
                            }
                        }
                    } else {
                        output.append("Name-> " + diet.get(1)[0].name);
                        for (int y = 1; y < diet.get(1).length; y++) {
                            if (Math.round(grammarias.get(1)[y - 1] * 100) > 250) {
                                output.append("\n   -" + diet.get(1)[y].name + " " + (1.65 * 100) + "γρ.\n");
                            } else if (Math.round(grammarias.get(1)[y - 1] * 100)< 20) {
                                output.append("\n   -" + diet.get(1)[y].name + " " + (Math.round((grammarias.get(1)[y - 1]+y/10.0) * 100)) + "γρ.\n");
                            }else {
                                output.append("\n   -" + diet.get(1)[y].name + " " + Math.round(grammarias.get(1)[y - 1] * 100) + "γρ.\n");
                            }
                        }
                    }
                    break;
                }
                case 2: {
                    if (i == 0 || i == 2 || i == 4 || i == 6) {
                        output.append("Name-> " + diet.get(2)[0].name);
                        for (int y = 1; y < diet.get(2).length; y++) {
                            if (Math.round(grammarias.get(2)[y - 1] * 100) > 250) {
                                output.append("\n   -" + diet.get(2)[y].name + " " + (1.97 * 100) + "γρ.\n");
                            }else if (Math.round(grammarias.get(2)[y - 1] * 100)< 20) {
                                output.append("\n   -" + diet.get(2)[y].name + " " + (Math.round((grammarias.get(2)[y - 1]+y/10.0) * 100)) + "γρ.\n");
                            } else {
                                output.append("\n   -" + diet.get(2)[y].name + " " + Math.round(grammarias.get(2)[y - 1] * 100) + "γρ.\n");
                            }
                        }
                    } else {
                        output.append("Name-> " + diet.get(3)[0].name);
                        for (int y = 1; y < diet.get(3).length; y++) {
                            if (Math.round(grammarias.get(3)[y - 1] * 100) > 250) {
                                output.append("\n   -" + diet.get(3)[y].name + " " + (1.5 * 100) + "γρ.\n");
                            } else if (Math.round(grammarias.get(3)[y - 1] * 100)< 20) {
                                output.append("\n   -" + diet.get(3)[y].name + " " + (Math.round((grammarias.get(3)[y - 1]+y/10.0) * 100)) + "γρ.\n");
                            }else {
                                output.append("\n   -" + diet.get(3)[y].name + " " + Math.round(grammarias.get(3)[y - 1] * 100) + "γρ.\n");
                            }
                        }
                    }
                    break;
                }
                case 3: {
                    switch (i) {
                        case 0: {
                            output.append("Name-> " + diet.get(8)[0].name);
                            for (int y = 1; y < diet.get(8).length; y++) {
                                if (Math.round(grammarias.get(8)[y - 1] * 100) > 250) {
                                    output.append("\n   -" + diet.get(8)[y].name + " " + (2.4 * 100) + "γρ.\n");
                                } else if (Math.round(grammarias.get(8)[y - 1] * 100)< 20) {
                                    output.append("\n   -" + diet.get(8)[y].name + " " + (Math.round((grammarias.get(8)[y - 1]+y/10.0) * 100)) + "γρ.\n");
                                }else {
                                    output.append("\n   -" + diet.get(8)[y].name + " " + Math.round(grammarias.get(8)[y - 1] * 100) + "γρ.\n");
                                }
                            }
                            break;
                        }
                        case 1: {
                            output.append("Name-> " + diet.get(11)[0].name);
                            for (int y = 1; y < diet.get(11).length; y++) {
                                if (Math.round(grammarias.get(11)[y - 1] * 100) > 250) {
                                    output.append("\n   -" + diet.get(11)[y].name + " " + (2.15 * 100) + "γρ.\n");
                                } else if (Math.round(grammarias.get(11)[y - 1] * 100)< 20) {
                                    output.append("\n   -" + diet.get(11)[y].name + " " + (Math.round((grammarias.get(11)[y - 1]+y/10.0) * 100)) + "γρ.\n");
                                }else {
                                    output.append("\n   -" + diet.get(11)[y].name + " " + Math.round(grammarias.get(11)[y - 1] * 100) + "γρ.\n");
                                }
                            }
                            break;
                        }
                        case 2: {
                            output.append("Name-> " + diet.get(14)[0].name);
                            for (int y = 1; y < diet.get(14).length; y++) {
                                if (Math.round(grammarias.get(14)[y - 1] * 100) > 250) {
                                    output.append("\n   -" + diet.get(14)[y].name + " " + (2 * 100) + "γρ.\n");
                                } else if (Math.round(grammarias.get(14)[y - 1] * 100)< 20) {
                                    output.append("\n   -" + diet.get(14)[y].name + " " + (Math.round((grammarias.get(14)[y - 1]+y/10.0) * 100)) + "γρ.\n");
                                }else {
                                    output.append("\n   -" + diet.get(14)[y].name + " " + Math.round(grammarias.get(14)[y - 1] * 100) + "γρ.\n");
                                }
                            }
                            break;
                        }
                        case 3: {
                            output.append("Name-> " + diet.get(12)[0].name);
                            for (int y = 1; y < diet.get(12).length; y++) {
                                if (Math.round(grammarias.get(12)[y - 1] * 100) > 250) {
                                    output.append("\n   -" + diet.get(12)[y].name + " " + (2.5 * 100) + "γρ.\n");
                                }else if (Math.round(grammarias.get(12)[y - 1] * 100)< 20) {
                                    output.append("\n   -" + diet.get(12)[y].name + " " + (Math.round((grammarias.get(12)[y - 1]+y/10.0) * 100)) + "γρ.\n");
                                } else {
                                    output.append("\n   -" + diet.get(12)[y].name + " " + Math.round(grammarias.get(12)[y - 1] * 100) + "γρ.\n");
                                }
                            }
                            break;
                        }
                        case 4: {
                            output.append("Name-> " + diet.get(9)[0].name);
                            for (int y = 1; y < diet.get(9).length; y++) {
                                if (Math.round(grammarias.get(9)[y - 1] * 100) > 250) {
                                    output.append("\n   -" + diet.get(9)[y].name + " " + (2.15 * 100) + "γρ.\n");
                                } else if (Math.round(grammarias.get(9)[y - 1] * 100)< 20) {
                                    output.append("\n   -" + diet.get(9)[y].name + " " + (Math.round((grammarias.get(9)[y - 1]+y/10.0) * 100)) + "γρ.\n");
                                }else {
                                    output.append("\n   -" + diet.get(9)[y].name + " " + Math.round(grammarias.get(9)[y - 1] * 100) + "γρ.\n");
                                }
                            }
                            break;
                        }
                        case 5: {
                            output.append("Name-> " + diet.get(13)[0].name);
                            for (int y = 1; y < diet.get(13).length; y++) {
                                if (Math.round(grammarias.get(13)[y - 1] * 100) > 250) {
                                    output.append("\n   -" + diet.get(13)[y].name + " " + (2 * 100) + "γρ.\n");
                                } else if (Math.round(grammarias.get(13)[y - 1] * 100)< 20) {
                                    output.append("\n   -" + diet.get(13)[y].name + " " + (Math.round((grammarias.get(13)[y - 1]+y/10.0) * 100)) + "γρ.\n");
                                }else {
                                    output.append("\n   -" + diet.get(13)[y].name + " " + Math.round(grammarias.get(13)[y - 1] * 100) + "γρ.\n");
                                }
                            }
                            break;
                        }
                        case 6: {
                            output.append("Name-> " + diet.get(10)[0].name);
                            for (int y = 1; y < diet.get(10).length; y++) {
                                if (Math.round(grammarias.get(10)[y - 1] * 100) > 250) {
                                    output.append("\n   -" + diet.get(10)[y].name + " " + (1.9 * 100) + "γρ.\n");
                                } else if (Math.round(grammarias.get(10)[y - 1] * 100)< 20) {
                                    output.append("\n   -" + diet.get(10)[y].name + " " + (Math.round((grammarias.get(10)[y - 1]+y/10.0) * 100)) + "γρ.\n");
                                }else {
                                    output.append("\n   -" + diet.get(10)[y].name + " " + Math.round(grammarias.get(10)[y - 1] * 100) + "γρ.\n");
                                }
                            }
                            break;
                        }
                    }
                    break;
                }
                case 4: {
                    if (i == 0 || i == 2 || i == 4 || i == 6) {
                        output.append("Name-> " + diet.get(4)[0].name);
                        for (int y = 1; y < diet.get(4).length; y++) {
                            if (Math.round(grammarias.get(4)[y - 1] * 100) > 250) {
                                output.append("\n   -" + diet.get(4)[y].name + " " + (1.45 * 100) + "γρ.\n");
                            } else if (Math.round(grammarias.get(4)[y - 1] * 100)< 20) {
                                output.append("\n   -" + diet.get(4)[y].name + " " + (Math.round((grammarias.get(4)[y - 1]+y/10.0) * 100)) + "γρ.\n");
                            }else {
                                output.append("\n   -" + diet.get(4)[y].name + " " + Math.round(grammarias.get(4)[y - 1] * 100) + "γρ.\n");
                            }
                        }
                    } else {
                        output.append("Name-> " + diet.get(5)[0].name);
                        for (int y = 1; y < diet.get(5).length; y++) {
                            if (Math.round(grammarias.get(5)[y - 1] * 100) > 250) {
                                output.append("\n   -" + diet.get(5)[y].name + " " + (1.86 * 100) + "γρ.\n");
                            }else if (Math.round(grammarias.get(5)[y - 1] * 100)< 20) {
                                output.append("\n   -" + diet.get(5)[y].name + " " + (Math.round((grammarias.get(5)[y - 1]+y/10.0) * 100)) + "γρ.\n");
                            } else {
                                output.append("\n   -" + diet.get(5)[y].name + " " + Math.round(grammarias.get(5)[y - 1] * 100) + "γρ.\n");
                            }
                        }
                    }
                    break;
                }
                case 5: {
                    if (i == 0 || i == 2 || i == 4 || i == 6) {
                        output.append("Name-> " + diet.get(6)[0].name);
                        for (int y = 1; y < diet.get(6).length; y++) {
                            if (Math.round(grammarias.get(6)[y - 1] * 100) > 250) {
                                output.append("\n   -" + diet.get(6)[y].name + " " + (1.96 * 100) + "γρ.\n");
                            }else if (Math.round(grammarias.get(6)[y - 1] * 100)< 20) {
                                output.append("\n   -" + diet.get(6)[y].name + " " + (Math.round((grammarias.get(6)[y - 1]+y/10.0) * 100)) + "γρ.\n");
                            } else {
                                output.append("\n   -" + diet.get(6)[y].name + " " + Math.round(grammarias.get(6)[y - 1] * 100) + "γρ.\n");
                            }
                        }
                    } else {
                        output.append("Name-> " + diet.get(7)[0].name);
                        for (int y = 1; y < diet.get(7).length; y++) {
                            if (Math.round(grammarias.get(7)[y - 1] * 100) > 250) {
                                output.append("\n   -" + diet.get(7)[y].name + " " + (1.9 * 100) + "γρ.\n");
                            }else if (Math.round(grammarias.get(7)[y - 1] * 100)< 20) {
                                output.append("\n   -" + diet.get(7)[y].name + " " + (Math.round((grammarias.get(7)[y - 1]+y/10.0) * 100)) + "γρ.\n");
                            }else  {
                                output.append("\n   -" + diet.get(7)[y].name + " " + Math.round(grammarias.get(7)[y - 1] * 100) + "γρ.\n");
                            }
                        }
                    }
                    break;
                }
            }
            return output.toString();
        }
    }
        
    
