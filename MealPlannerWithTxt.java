import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class MealPlannerWithTxt {

    public enum Day {
        Δευτέρα, Τρίτη, Τετάρτη, Πέμπτη, Παρασκευή, Σάββατο, Κυριακή
    }

    public static class Ingredient {
        String name;
        double quantity;

        public Ingredient(String name, double quantity) {
            this.name = name;
            this.quantity = quantity;
        }

        @Override
        public String toString() {
            return String.format("%.2fg %s", quantity, name);
        }
    }

    public static class Meal {
        String name;
        String category;
        List<Ingredient> ingredients;
        int calories;
        double protein;
        double fat;
        double carbohydrates;
        double sugar;

        public Meal(String name, String category, List<Ingredient> ingredients, int calories, double protein, double fat, double carbohydrates, double sugar) {
            this.name = name;
            this.category = category;
            this.ingredients = ingredients;
            this.calories = calories;
            this.protein = protein;
            this.fat = fat;
            this.carbohydrates = carbohydrates;
            this.sugar = sugar;
        }

        @Override
        public String toString() {
            StringBuilder details = new StringBuilder();
            details.append(name).append(":\n");
            for (Ingredient ingredient : ingredients) {
                details.append("      - ").append(ingredient).append("\n");
            }
            details.append(String.format("      (Θερμίδες: %d kcal, Πρωτεΐνες: %.2fg, Λιπαρά: %.2fg, Υδατάνθρακες: %.2fg, Ζάκχαρα: %.2fg)",
                    calories, protein, fat, carbohydrates, sugar));
            return details.toString();
        }
    }

    private static Map<Day, Map<String, Meal>> weeklyMeals = new LinkedHashMap<>();

    public static void saveWeeklyMealsToTxt(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Day day : Day.values()) {
                writer.write(day + ":\n");
                Map<String, Meal> meals = weeklyMeals.get(day);
                if (meals != null) {
                    String[] mealOrder = {"Πρωινό", "Δεκατιανό", "Μεσημεριανό", "Απογευματινό", "Βραδινό"};
                    for (String mealTime : mealOrder) {
                        Meal meal = meals.get(mealTime);
                        if (meal != null) {
                            writer.write("  " + mealTime + ":\n");
                            writer.write("    " + meal + "\n");
                        }
                    }
                }
                writer.write("\n");
            }
            System.out.println("Το πλάνο της εβδομάδας αποθηκεύτηκε στο αρχείο: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Τρεις ποικιλίες για κάθε κατηγορία γεύματος
        List<Meal> breakfastOptions = Arrays.asList(
                new Meal("Ομελέτα", "Πρωινό", Arrays.asList(new Ingredient("Αυγά", 120), new Ingredient("Ελαιόλαδο", 30)), 300, 20, 25, 5, 2),
                new Meal("Βρώμη με γάλα", "Πρωινό", Arrays.asList(new Ingredient("Βρώμη", 100), new Ingredient("Γάλα", 200)), 250, 10, 5, 40, 5),
                new Meal("Τοστ με γαλοπούλα", "Πρωινό", Arrays.asList(new Ingredient("Ψωμί", 80), new Ingredient("Γαλοπούλα", 50), new Ingredient("Τυρί", 30)), 300, 15, 10, 30, 3)
        );

        List<Meal> snackOptions = Arrays.asList(
                new Meal("Γιαούρτι", "Δεκατιανό", Arrays.asList(new Ingredient("Γιαούρτι", 200)), 100, 10, 5, 5, 5),
                new Meal("Μπανάνα", "Δεκατιανό", Arrays.asList(new Ingredient("Μπανάνα", 150)), 90, 1, 0.3, 23, 12),
                new Meal("Ξηροί καρποί", "Δεκατιανό", Arrays.asList(new Ingredient("Αμύγδαλα", 30)), 180, 6, 15, 6, 1)
        );

        List<Meal> lunchOptions = Arrays.asList(
                new Meal("Κοτόπουλο με ρύζι", "Μεσημεριανό", Arrays.asList(new Ingredient("Κοτόπουλο", 200), new Ingredient("Ρύζι", 100)), 600, 40, 10, 60, 2),
                new Meal("Σολωμός με πατάτες", "Μεσημεριανό", Arrays.asList(new Ingredient("Σολωμός", 200), new Ingredient("Πατάτες", 150)), 500, 35, 25, 30, 2),
                new Meal("Μοσχάρι με λαχανικά", "Μεσημεριανό", Arrays.asList(new Ingredient("Μοσχάρι", 200), new Ingredient("Λαχανικά", 150)), 550, 45, 15, 20, 5)
        );

        List<Meal> afternoonSnackOptions = Arrays.asList(
                new Meal("Φρούτο", "Απογευματινό", Arrays.asList(new Ingredient("Μήλο", 150)), 50, 0.5, 0.2, 12, 10),
                new Meal("Μπάρες δημητριακών", "Απογευματινό", Arrays.asList(new Ingredient("Μπάρες δημητριακών", 50)), 200, 5, 10, 30, 15),
                new Meal("Ρυζογκοφρέτες", "Απογευματινό", Arrays.asList(new Ingredient("Ρυζογκοφρέτες", 30)), 120, 2, 0.5, 25, 1)
        );

        List<Meal> dinnerOptions = Arrays.asList(
                new Meal("Σαλάτα με τόνο", "Βραδινό", Arrays.asList(new Ingredient("Τόνος", 100), new Ingredient("Λαχανικά", 200)), 400, 30, 15, 10, 2),
                new Meal("Ομελέτα με λαχανικά", "Βραδινό", Arrays.asList(new Ingredient("Αυγά", 100), new Ingredient("Λαχανικά", 150)), 300, 20, 20, 10, 2),
                new Meal("Φιλέτο κοτόπουλο", "Βραδινό", Arrays.asList(new Ingredient("Κοτόπουλο", 150), new Ingredient("Σαλάτα", 100)), 350, 30, 10, 5, 1)
        );

        // Δημιουργία εβδομαδιαίου πλάνου με εναλλαγή ανά ημέρα (3 επιλογές)
        int dayIndex = 0;
        for (Day day : Day.values()) {
            Map<String, Meal> dailyMeals = new LinkedHashMap<>();
            dailyMeals.put("Πρωινό", breakfastOptions.get(dayIndex % 3));
            dailyMeals.put("Δεκατιανό", snackOptions.get(dayIndex % 3));
            dailyMeals.put("Μεσημεριανό", lunchOptions.get(dayIndex % 3));
            dailyMeals.put("Απογευματινό", afternoonSnackOptions.get(dayIndex % 3));
            dailyMeals.put("Βραδινό", dinnerOptions.get(dayIndex % 3));
            weeklyMeals.put(day, dailyMeals);
            dayIndex++;
        }

        // Αποθήκευση σε αρχείο
        saveWeeklyMealsToTxt("Weekly_Meal_Plan.txt");
    }
}
