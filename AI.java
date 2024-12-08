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
       //λιστα με ολα τα γευματα
        ArrayList<Macros[]> diet = new ArrayList<>();

        //μεταβλητη που καταχωρουντε τα γευματα ετσι ωστε να το διβαζει το ΑΙ και να μην τα ξανα χρησιμοποιει
        String ai_messege_input1 = "ΔΕΝ ΠΡΕΠΕΙ ΜΕ ΤΙΠΟΤΑ ,ΟΠΩΣ ΚΑΙ ΔΗΠΟΤΕ ΝΑ ΕΧΕΙ ΚΑΠΟΙΑ ΣΥΝΤΑΓΗ ΤΑ ΠΑΡΑΚΑΤΩ: ΑΥΓΑ"+
                "ΤΟ ΓΕΥΜΑ ΠΟΥ ΘΑ ΦΤΙΑΞΕΙΣ ΠΡΠΕΙ ΝΑ ΜΗΝ ΜΟΙΑΖΕΙ ΟΥΤΕ ΜΕ ΛΙΓΟ ΜΕ ΤΑ ΠΑΡΑΚΑΤΩ:  ";
        String ai_messege_input2 = "ΔΕΝ ΠΡΕΠΕΙ ΜΕ ΤΙΠΟΤΑ ,ΟΠΩΣ ΚΑΙ ΔΗΠΟΤΕ ΝΑ ΕΧΕΙ ΚΑΠΟΙΑ ΣΥΝΤΑΓΗ ΤΑ ΠΑΡΑΚΑΤΩ: ΑΥΓΑ"+
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
        retryMakeFood(diet, 3, "ΑΥΤΟ ΘΕΛΩ ΝΑ ΕΙΝΑΙ  ΚΑΤΗΓΟΡΙΑ ΜΕ ΛΕΥΚΟ ΚΡΕΑΣ ΓΕΥΜΑ"); // Βραδινό
        System.out.println("\n");
        retryMakeFood(diet, 3, "ΑΥΤΟ ΘΕΛΩ ΝΑ ΕΙΝΑΙ  ΚΑΤΗΓΟΡΙΑ ΛΑΔΕΡΟ ΓΕΥΜΑ"); // Βραδινό
        System.out.println("\n");
        retryMakeFood(diet, 3, "ΑΥΤΟ ΘΕΛΩ ΝΑ ΕΙΝΑΙ  ΚΑΤΗΓΟΡΙΑ ΟΣΠΡΙΑ ΓΕΥΜΑ"); // Βραδινό
        System.out.println("\n");
        retryMakeFood(diet, 3, "ΑΥΤΟ ΘΕΛΩ ΝΑ ΕΙΝΑΙ  ΚΑΤΗΓΟΡΙΑ ΨΑΡΙ ΓΕΥΜΑ"); // Βραδινό
        System.out.println("\n");

        //ημερησια μακροθρεπτικα
        Macros macros=(retryMakeFood(112.0, 175,20,"Α",500,"Kick box",1));
    }

//ΕΛΕΓΧΟΣ ΓΙΑ ΝΑ ΕΠΑΝΑΛΜΒΑΝΕΤΕ ΜΙΑ ΔΙΑΔΙΚΑΣΙΑ ΟΤΑΝ ΥΠΑΡΧΕΙ ΣΦΑΛΜΑ,ΚΑΙ ΣΥΓΚΕΚΡΗΜΕΝΑ ΓΙΑ ΤΙΣ ΛΑΘΟΣ ΑΠΟΑΝΤΗΣΕΙΣ ΤΟΥ ΑΙ
    public static void retryMakeFood(ArrayList<Macros[]> diet, int mealType, String requirements) {
        boolean success = false;
        while (!success) {
            try {
                diet.add(make_food(mealType, requirements)); // Κλήση της make_food
                success = true; // Αν πετύχει, τερματίζει
            } catch (Exception e) {
                System.err.println("Προέκυψε σφάλμα: " + e.getMessage());
                System.out.println("Επανάληψη προσπάθειας...");
            }
        }
    }
    //ΕΛΕΓΧΟΣ ΓΙΑ ΝΑ ΕΠΑΝΑΛΜΒΑΝΕΤΕ ΜΙΑ ΔΙΑΔΙΚΑΣΙΑ ΟΤΑΝ ΥΠΑΡΧΕΙ ΣΦΑΛΜΑ,ΚΑΙ ΣΥΓΚΕΚΡΗΜΕΝΑ ΓΙΑ ΤΙΣ ΛΑΘΟΣ ΑΠΟΑΝΤΗΣΕΙΣ ΤΟΥ ΑΙ
    public static Macros retryMakeFood(double weightKg, double heightCm, int age, String gender, double durationMin, String sport,int target) {
        boolean success = false;// ΕΛΕΓΧΟΣ ΓΙΑ ΤΟ ΚΑΛΕΣΜΑ ΤΟΥ DAYS_MACROS
        boolean success2 = false;//ΕΛΕΓΧΟΣ ΓΙΑ ΟΛΟ ΤΟ ΠΡΟΓΡΑΜΜΑ
        //ΛΙΣΤΑ ΠΟΥ ΑΠΟΘΗΚΕΥΟΝΤΑΙ ΤΑ ΚΑΛΕΣΜΑΤΑ ΤΟΥ DAYS_MACROS ΓΙΑ ΝΑ ΣΥΓΚΡΗΝΟΥΜΕ ΚΑΙ ΝΑ ΒΡΟΥΜΕ ΑΠΟ ΟΛΑ ΑΥΤΑ ΤΟ ΣΩΣΤΟ ΜΕΣΟ ΤΟ ΜΕΤ
        ArrayList<Macros> x = new ArrayList<>();
        //ΑΡΧΟΚΟΠΟΙΗΣΗ ΜΕΤΑΒΛΗΤΗΣ ΜΕ ΤΑ ΗΜΕΡΗΣΙΑ ΜΑΚΡΟΣ ΚΑΙ ΤΗΝ ΤΕΛΙΚΗ ΕΚΔΟΧΗ
        Macros final_version_DM = null;

        // Επανάληψη μέχρι να ολοκληρωθεί σωστά καθως οι απαντησεισ του ΑΙ δεν ειναι παντα ορθες
        while (!success2) {
            try {
                // Συμπλήρωση της λίστας με BMR
                for (int i = 0; i < 7; i++) {// Επανάληψη μέχρι να ολοκληρωθεί σωστά καθως οι απαντησεισ του ΑΙ δεν ειναι παντα ορθες
                    while (!success) {
                        try {
                            final_version_DM = DAYS_MACROS(weightKg, heightCm, age, gender, durationMin, sport,target);
                            success = true;  // Επιτυχής υπολογισμός
                        } catch (Exception e) {
                            System.err.println("Προέκυψε σφάλμα: " + e.getMessage());
                            System.out.println("Επανάληψη προσπάθειας...");
                        }
                    }
                    x.add(final_version_DM);  // Προσθήκη στη λίστα
                    success = false;  // Επαναφορά για την επόμενη επανάληψη
                }

                // Εύρεση της πιο συχνής τιμής BMR
                Macros max = null;
                for (int i = 0; i < 7; i++) {
                    int count = 0;
                    for (int h = 0; h < 7; h++) {
                        if (Double.compare(x.get(i).bmr, x.get(h).bmr) == 0) {
                            count++;
                        }
                    }
                    if (max == null || count > max.fores) {
                        max = new Macros(i, count);  // Εκχώρηση νέας μέγιστης τιμής
                    }
                }

                if (max != null) {
                    final_version_DM = x.get(max.thesi);
                    success2 = true;  // Ενημέρωση επιτυχίας
                }

            } catch (Exception t) {
                System.err.println("Προέκυψε σφάλμα: " + t.getMessage());
                System.out.println("Επανάληψη προσπάθειας...");
            }
        }
        //ΕΚΤΥΠΩΣΗ ΓΙΑ ΕΠΙΒΕΒΑΙΩΣΗ
        System.out.println("Ηλικία: " + age + " ετών");
        System.out.println("Θερμίδες Καύσης: " + Math.round(final_version_DM.calories2) + " kcal");
        System.out.println("Πρωτεΐνες: " + Math.round(final_version_DM.protein2) + " γρ");
        System.out.println("Υδατάνθρακες: " + Math.round(final_version_DM.carbs2) + " γρ");
        System.out.println("Λίπη: " + Math.round(final_version_DM.fat2) + " γρ");
        //ΕΠΙΣΤΡΟΦΗ ΜΑΚΡΟΘΡΕΠΤΙΚΩΝ
        return final_version_DM;
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
        //μηνηματα που χρησιμοποιουμε στο ΑΙ για να μασ δωσει τησ απαντησεισ που θελουμε
        if (x == 1) {
            return "Γεύμα,με λιγα υλικα  και τα υλικά που χρειαζόμαστε για αυτό το γεύμα.";
        } else if (x == 2) {
            return "Θέλω να μου δώσεις μόνο τα υλικά του γεύματος σε μορφή: " +
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
        }else if (x == 9){
            return "η απαντηση πρεπει να ειναι ενας αριθμος π.χ. 7.0" +
                    "σου εχω γραψει πριν το αθλημα" +
                    "Άσκηση και τα MET τους" +
                    "Περπάτημα (4 km/h) 3.5" +
                    "Περπάτημα (6 km/h) 5.0" +
                    "Τρέξιμο (8 km/h) 8.0" +
                    "Τρέξιμο (10 km/h) 10.0" +
                    "Τρέξιμο (12 km/h) 11.5" +
                    "Τρέξιμο (15 km/h) 13.0" +
                    "Τρέξιμο (18 km/h) 15.0" +
                    "Ποδήλατο (10-12 km/h) 4.0" +
                    "Ποδήλατο (16-19 km/h) 8.5" +
                    "Ποδήλατο (22-25 km/h) 10.0" +
                    "Ποδήλατο (30+ km/h) 12.0" +
                    "Κολύμβηση 9.0" +
                    "Σχοινάκι  10.0" +
                    "Βάρη  7.0" +
                    "Γιόγκα 3.0" +
                    "Πιλάτες 3.5" +
                    "Χορός  6.0" +
                    "CrossFit / HIIT 9.0" +
                    "Αναρρίχηση 4.0" +
                    "Ποδόσφαιρο 10.0" +
                    "Μπάσκετn 8.0" +
                    "Τένις 8.0" +
                    "Βόλεϊ (αγωνιστικό) 6.0" +
                    "Πυγμαχία 12.0" +
                    "Πολεμικές τέχνες 10.0" +
                    "Καράτε1 0.0" +
                    "Τάε Κβο Ντο 11.0" +
                    "Σκι 10.0 " +
                    "Καγιάκ 5.0 " +
                    "Κωπηλασία   10.0 " +
                    "Περπάτημα με βάρη 6.0 " +
                    "Πεζοπορία  9.0";
        }
        return "";
    }

    public static Macros[] make_food (int choose_meal,String requirements) throws URISyntaxException {
        ArrayList<String> foodlist = new ArrayList<>();//λιστα υλικων
        ArrayList<Float> calorieslist = new ArrayList<>();//λιστα θερμιδων
        ArrayList<Float> proteinlist = new ArrayList<>();//λιστα πρωτεινων
        ArrayList<Float> carbslist = new ArrayList<>();//λιστα υδατανθρακων
        ArrayList<Float> fatlist = new ArrayList<>();//λιστα λιπαρων

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
        System.out.println(meal_panel[0].name);
        for (int t = 1; t < meal_panel.length; t++) {
            System.out.println(meal_panel[t].name + ", " + meal_panel[t].calories + ", " + meal_panel[t].protein + ", " + meal_panel[t].carbs + ", " + meal_panel[t].fat);
        }
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
        return meal_list;
    }

    public static Macros DAYS_MACROS(double weightKg, double heightCm, int age, String gender, double durationMin, String sport, int target) throws URISyntaxException {
        //ΑΡΧΙΚΟΠΟΙΗΣΗ ΒΜR ΚΑΙ ΥΠΟΛΟΓΙΣΜΟΣ ΤΟΥ
        double BMR = 0;
        if (gender.equalsIgnoreCase("Α")) {
            BMR= 10 * weightKg + 6.25 * heightCm - 5 * age + 5;
        } else if (gender.equalsIgnoreCase("Γ")) {
            BMR= 10 * weightKg + 6.25 * heightCm - 5 * age - 161;
        }
        //ΑΡΧΟΚΟΠΟΙΗΣΗ ΜΕΤ
        Double ΜΕΤ= Double.valueOf(chatGPT("μορφη απαντησησ π.χ. 10.0 θελω το MET του αθληματος" + sport + "σου δινω επισησ και αυτα τα δεδδομενα"+ geumata(9)));

        // Υπολογισμός θερμίδων καύσης
        double caloriesBurned= ΜΕΤ * 0.0175 * weightKg * durationMin;


        // Υπολογισμός μακροθρεπτικών ισοροπιας
            double proteinCalories = caloriesBurned * 0.25;  // 0,25% πρωτεΐνες
            double carbCalories = caloriesBurned * 0.50;   // 50% υδατάνθρακες
            double fatCalories = caloriesBurned * 0.25;   // 25% λίπη

            double proteinGrams = proteinCalories / 4;
            double carbGrams = carbCalories / 4;
            double fatGrams = fatCalories / 9;
            //NA ΜΕΙΩΣΕΙ ΚΙΛΑ
        if (target==1) {
            double caloriesBurned1 = (BMR-BMR*15/100) + caloriesBurned / 7;
            double proteinCalories1 = proteinCalories +proteinCalories * 0.25;  // 0,25% πρωτεΐνες
            double carbCalories1 = carbCalories -carbCalories * 0.15;   // 50% υδατάνθρακες
            double fatCalories1 = fatCalories -fatCalories * 0.10;   // 25% λίπη

            double proteinGrams1 = proteinCalories / 4;
            double carbGrams1 = carbCalories / 4;
            double fatGrams1 = fatCalories / 9;
            return new Macros(String.valueOf(age),caloriesBurned1,proteinCalories1,carbCalories1,fatCalories1,ΜΕΤ);
            //ΝΑ ΑΥΞΗΣΗ ΚΙΛΑ
        }else if (target==2){
            double caloriesBurned2 = (BMR+BMR*15/100) + caloriesBurned / 7;
            double proteinCalories2 = proteinCalories +proteinCalories * 0.20;  // 0,25% πρωτεΐνες
            double carbCalories2 = carbCalories +carbCalories * 20;   // 50% υδατάνθρακες
            double fatCalories2 = fatCalories +fatCalories * 0.5;   // 25% λίπη

            double proteinGrams2 = proteinCalories / 4;
            double carbGrams2 = carbCalories / 4;
            double fatGrams2 = fatCalories / 9;
            return new Macros(String.valueOf(age),caloriesBurned2,proteinCalories2,carbCalories2,fatCalories2,ΜΕΤ);

        }
        caloriesBurned = BMR + caloriesBurned / 7;
        return new Macros(String.valueOf(age),caloriesBurned,proteinCalories,carbCalories,fatCalories,ΜΕΤ);
    }
}

