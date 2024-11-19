import java.util.*;

public class MealPlanner1 {
    // Διαθέσιμα γεύματα
    private static final String[] MEALS = {
        "Μακαρόνια", "Κοτόπουλο", "Ψάρι", "Σαλάτα", 
        "Ριζότο", "Μπριζόλα", "Λαχανικά στον ατμό"
    };

    // Ημέρες της εβδομάδας
    private static final String[] DAYS = {
        "Δευτέρα", "Τρίτη", "Τετάρτη", 
        "Πέμπτη", "Παρασκευή", "Σάββατο", "Κυριακή"
    };

    public static void main(String[] args) {
        // Χάρτης για την κατανομή των γευμάτων στις ημέρες
        Map<String, String> mealPlan = new HashMap<>();

        // Τυχαία δειγματοληψία για να εξασφαλίσουμε ποικιλία
        List<String> availableMeals = new ArrayList<>(Arrays.asList(MEALS));
        Random random = new Random();

        for (String day : DAYS) {
            if (availableMeals.isEmpty()) {
                // Επαναφορά της λίστας αν εξαντληθούν τα γεύματα
                availableMeals = new ArrayList<>(Arrays.asList(MEALS));
            }

            // Επιλογή τυχαίου γεύματος
            int randomIndex = random.nextInt(availableMeals.size());
            String selectedMeal = availableMeals.get(randomIndex);

            // Ανάθεση του γεύματος στην ημέρα
            mealPlan.put(day, selectedMeal);

            // Αφαίρεση του επιλεγμένου γεύματος για να μην επαναληφθεί άμεσα
            availableMeals.remove(randomIndex);
        }

        // Εκτύπωση του προγράμματος γευμάτων
        System.out.println("Πρόγραμμα γευμάτων για την εβδομάδα:");
        for (String day : DAYS) {
            System.out.println(day + ": " + mealPlan.get(day));
        }
    }
}

