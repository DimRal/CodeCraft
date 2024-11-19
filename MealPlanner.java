import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MealPlanner {

    // Δημιουργία ενός Enum για τις ημέρες της εβδομάδας
    public enum Day {
        Δευτέρα, Τρίτη, Τετάρτη, Πέμπτη, Παρασκευή, Σάββατο, Κυριακή
    }

    // Δημιουργία μιας δομής για να κρατάμε τα γεύματα
    public static class Meal {
        String name;
        String category;
        int quantity;  // Ποσότητα
        int calories;
        double protein;
        double fat;
        double carbohydrates;
        double sugar;

        public Meal(String name, String category, int quantity, int calories, double protein, double fat, double carbohydrates, double sugar) {
            this.name = name;
            this.category = category;
            this.quantity = quantity;
            this.calories = calories;
            this.protein = protein;
            this.fat = fat;
            this.carbohydrates = carbohydrates;
            this.sugar = sugar;
        }

        @Override
        public String toString() {
            return name + " (Κατηγορία: " + category + ", Ποσότητα: " + quantity + "g, Θερμίδες: " + calories + " kcal, Πρωτεΐνες: " + protein + "g, Λιπαρά: " + fat + "g, Υδατάνθρακες: " + carbohydrates + "g, Ζάκχαρα: " + sugar + "g)";
        }
    }

    // Χάρτης για να αποθηκεύουμε τα γεύματα ανά ημέρα και γεύμα
    private static Map<Day, Map<String, List<Meal>>> weeklyMeals = new HashMap<>();

    // Φόρτωση γευμάτων από το αρχείο CSV
    public static void loadMealsFromCSV(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            // Παράκαμψη της πρώτης γραμμής με τις επικεφαλίδες
            br.readLine();

            // Ανάγνωση κάθε γραμμής
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue; // Παράλειψη κενών γραμμών
                }

                String[] values = line.split(",");

                // Έλεγχος αν η γραμμή περιέχει τον σωστό αριθμό στηλών
                if (values.length < 7) {
                    System.out.println("Λανθασμένη μορφή γραμμής: " + line);
                    continue;
                }

                // Ανάγνωση δεδομένων από το CSV
                String mealName = values[0].trim();
                String category = values[1].trim();
                int quantity = Integer.parseInt(values[2].trim());
                int calories = Integer.parseInt(values[3].trim());
                double protein = Double.parseDouble(values[4].trim());
                double fat = Double.parseDouble(values[5].trim());
                double carbohydrates = Double.parseDouble(values[6].trim());
                double sugar = 0; // Προεπιλεγμένη τιμή για τα ζάχαρα (αν δεν υπάρχουν)

                // Έλεγχος για την ύπαρξη του 8ου πεδίου (ζάχαρα), αν υπάρχει, το αναθέτουμε
                if (values.length > 7) {
                    sugar = Double.parseDouble(values[7].trim());
                }

                // Δημιουργία του Meal αντικειμένου
                Meal meal = new Meal(mealName, category, quantity, calories, protein, fat, carbohydrates, sugar);

                // Ελέγξτε αν υπάρχει για την ημέρα και γεύμα, αν όχι, δημιουργούμε νέα καταχώρηση
                for (Day day : Day.values()) {
                    if (!weeklyMeals.containsKey(day)) {
                        weeklyMeals.put(day, new HashMap<>());
                    }
                    Map<String, List<Meal>> dayMeals = weeklyMeals.get(day);

                    // Αν η κατηγορία (π.χ. "Πρωινό") δεν υπάρχει για την ημέρα, δημιουργούμε νέα λίστα
                    if (!dayMeals.containsKey(category)) {
                        dayMeals.put(category, new ArrayList<>());
                    }

                    // Προσθήκη του γεύματος στη σωστή ημέρα και κατηγορία
                    dayMeals.get(category).add(meal);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Δημιουργία του ημερήσιου πλάνου για κάθε ημέρα
    public static Map<Day, Map<String, List<Meal>>> generateDailyPlans() {
        Map<Day, Map<String, List<Meal>>> dailyPlans = new HashMap<>();

        // Για κάθε ημέρα, δημιουργούμε το πλάνο με 1-2 γεύματα ανά κατηγορία
        for (Day day : Day.values()) {
            Map<String, List<Meal>> mealsForDay = new HashMap<>();
            for (String category : Arrays.asList("Πρωινό", "Δεκατιανό", "Μεσημεριανό", "Απογευματινό", "Βραδινό")) {
                List<Meal> availableMeals = weeklyMeals.get(day).get(category);

                // Αν υπάρχουν γεύματα για αυτή την κατηγορία, επιλέγουμε 1 ή 2 τυχαία γεύματα
                if (availableMeals != null && !availableMeals.isEmpty()) {
                    Random rand = new Random();
                    int numMeals = rand.nextInt(2) + 1; // Επιλέγουμε 1 ή 2 γεύματα
                    List<Meal> selectedMeals = new ArrayList<>();

                    // Επιλογή τυχαίων γευμάτων
                    for (int i = 0; i < numMeals; i++) {
                        Meal selectedMeal = availableMeals.get(rand.nextInt(availableMeals.size()));
                        selectedMeals.add(selectedMeal);
                    }

                    mealsForDay.put(category, selectedMeals);
                }
            }
            dailyPlans.put(day, mealsForDay);
        }
        return dailyPlans;
    }

    // Εκτύπωση του πλάνου της εβδομάδας
    public static void printWeeklyMeals() {
        Map<Day, Map<String, List<Meal>>> dailyPlans = generateDailyPlans();

        for (Day day : Day.values()) {
            System.out.println(day + ":");
            Map<String, List<Meal>> meals = dailyPlans.get(day);
            if (meals != null) {
                for (String mealTime : meals.keySet()) {
                    System.out.println("  " + mealTime + ":");
                    for (Meal meal : meals.get(mealTime)) {
                        System.out.println("    " + meal);
                    }
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Φόρτωση των γευμάτων από το αρχείο CSV
        loadMealsFromCSV("meals.csv");

        // Εκτύπωση των γευμάτων για την εβδομάδα
        printWeeklyMeals();
    }
}





