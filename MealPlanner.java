import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class Meal {
    private String name;
    private String category;
    private int calories;
    private double protein;
    private double fat;
    private double carbs;
    private List<String> ingredients;

    public Meal(String name, String category, int calories, double protein, double fat, double carbs, List<String> ingredients) {
        this.name = name;
        this.category = category;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
        this.ingredients = ingredients;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public int getCalories() {
        return calories;
    }

    public double getProtein() {
        return protein;
    }

    public double getFat() {
        return fat;
    }

    public double getCarbs() {
        return carbs;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    @Override
    public String toString() {
        return name + " (" + category + ") - Θερμίδες: " + calories + ", Πρωτεΐνες: " + protein + ", Λιπαρά: " + fat + ", Υδατάνθρακες: " + carbs;
    }
}

public class MealPlanner {
    // Φορτώνουμε τα γεύματα από το CSV
    public static List<Meal> loadMeals(String filename) {
        List<Meal> meals = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            br.readLine(); // Αγνοούμε την κεφαλίδα
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                String category = parts[1];
                int calories = Integer.parseInt(parts[2]);
                double protein = Double.parseDouble(parts[3]);
                double fat = Double.parseDouble(parts[4]);
                double carbs = Double.parseDouble(parts[5]);
                List<String> ingredients = Arrays.stream(parts).skip(6).collect(Collectors.toList());
                meals.add(new Meal(name, category, calories, protein, fat, carbs, ingredients));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return meals;
    }

    // Κατηγοριοποιούμε τα γεύματα
    public static Map<String, List<Meal>> categorizeMeals(List<Meal> meals) {
        return meals.stream().collect(Collectors.groupingBy(Meal::getCategory));
    }

    // Διαβάζουμε τις ημέρες της εβδομάδας
    public static List<String> getDaysOfWeek() {
        return Arrays.asList("Δευτέρα", "Τρίτη", "Τετάρτη", "Πέμπτη", "Παρασκευή", "Σάββατο", "Κυριακή");
    }

    // Δημιουργούμε το εβδομαδιαίο πλάνο γευμάτων
    public static void generateMealPlan(Map<String, List<Meal>> categorizedMeals, String outputFilename, 
                                        int targetCalories, double targetProtein, double targetFat, double targetCarbs) {
        List<String> days = getDaysOfWeek();
        Random random = new Random();
        String[] categories = {"Πρωινό", "Δεκατιανό", "Μεσημεριανό", "Απογευματινό", "Βραδινό"};

        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFilename))) {
            // Για κάθε μέρα της εβδομάδας
            for (String day : days) {
                writer.println(day + ":");
                writer.println("---------------------------------------");

                // Για κάθε κατηγορία γεύματος, επιλέγουμε ένα γεύμα
                Set<Meal> dailyMeals = new LinkedHashSet<>();
                int dailyCalories = 0, dailyProtein = 0, dailyFat = 0, dailyCarbs = 0;

                for (String category : categories) {
                    List<Meal> availableMeals = categorizedMeals.getOrDefault(category, Collections.emptyList());
                    if (!availableMeals.isEmpty()) {
                        Meal selectedMeal = availableMeals.get(random.nextInt(availableMeals.size()));
                        dailyMeals.add(selectedMeal);

                        dailyCalories += selectedMeal.getCalories();
                        dailyProtein += selectedMeal.getProtein();
                        dailyFat += selectedMeal.getFat();
                        dailyCarbs += selectedMeal.getCarbs();
                    }
                }

                // Εμφάνιση των γευμάτων της ημέρας χωρίς να εκτυπώνονται οι αριθμοί των υλικών
                dailyMeals.forEach(meal -> {
                    writer.println("  - " + meal.getName() + " (" + meal.getCategory() + ")");
                    // Εμφάνιση των υλικών χωρίς τον αριθμό τους
                    writer.println("    Υλικά: " + String.join(", ", meal.getIngredients()));
                });

                // Ελέγχουμε αν οι απαιτήσεις είναι καλυμμένες
                if (dailyCalories < targetCalories) {
                    adjustMealQuantities(categorizedMeals, dailyMeals, targetCalories - dailyCalories, "calories");
                }
                if (dailyProtein < targetProtein) {
                    adjustMealQuantities(categorizedMeals, dailyMeals, targetProtein - dailyProtein, "protein");
                }
                if (dailyFat < targetFat) {
                    adjustMealQuantities(categorizedMeals, dailyMeals, targetFat - dailyFat, "fat");
                }
                if (dailyCarbs < targetCarbs) {
                    adjustMealQuantities(categorizedMeals, dailyMeals, targetCarbs - dailyCarbs, "carbs");
                }

                writer.println();  // Κενή γραμμή για διαχωρισμό των ημερών
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Μέθοδος για την προσαρμογή των ποσοτήτων των γευμάτων για να καλυφθούν οι απαιτήσεις
    public static void adjustMealQuantities(Map<String, List<Meal>> categorizedMeals, Set<Meal> dailyMeals,
                                            double requiredAmount, String nutrient) {
        // Εδώ μπορείς να προσθέσεις την υλοποίηση για να αυξήσεις τις ποσότητες των γευμάτων
        // για να καλυφθούν οι απαιτήσεις (θερμίδες, πρωτεΐνες, λιπαρά, υδατάνθρακες)
        // Αυτός ο κώδικας εξαρτάται από τον τρόπο που θέλεις να αυξήσεις τις ποσότητες.
    }

    public static void main(String[] args) {
        String inputFile = "meals.csv";  // Αρχείο CSV με τα γεύματα
        String outputFile = "meal_plan.txt";  // Αρχείο εξόδου για το πλάνο γευμάτων

        // Συμπληρώνουμε τα ημερήσια μακροθρεπτικά
        Scanner scanner = new Scanner(System.in);
        System.out.print("Συμπληρώστε τις απαιτούμενες θερμίδες για την ημέρα: ");
        int targetCalories = scanner.nextInt();
        System.out.print("Συμπληρώστε τις απαιτούμενες πρωτεΐνες (σε γραμμάρια): ");
        double targetProtein = scanner.nextDouble();
        System.out.print("Συμπληρώστε τα απαιτούμενα λιπαρά (σε γραμμάρια): ");
        double targetFat = scanner.nextDouble();
        System.out.print("Συμπληρώστε τους απαιτούμενους υδατάνθρακες (σε γραμμάρια): ");
        double targetCarbs = scanner.nextDouble();

        // Φορτώνουμε τα γεύματα από το CSV
        List<Meal> meals = loadMeals(inputFile);

        // Κατηγοριοποιούμε τα γεύματα
        Map<String, List<Meal>> categorizedMeals = categorizeMeals(meals);

        // Δημιουργούμε το εβδομαδιαίο πλάνο γευμάτων
        generateMealPlan(categorizedMeals, outputFile, targetCalories, targetProtein, targetFat, targetCarbs);

        System.out.println("Το εβδομαδιαίο πλάνο γευμάτων δημιουργήθηκε στο αρχείο: " + outputFile);
    }
}
