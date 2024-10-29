import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MealPlanner {

    public static void main(String[] args) {
        // Φόρτωση φαγητών από το CSV αρχείο
        List<Food> foods = loadFoodsFromCSV("foods.csv");

        // Στόχοι μακροθρεπτικών του χρήστη ανά ημέρα
        double dailyCalories = 2000;
        double dailyProtein = 150;  // σε γραμμάρια
        double dailyCarbs = 250;    // σε γραμμάρια
        double dailyFat = 70;       // σε γραμμάρια

        // Κατανομή φαγητών για την εβδομάδα
        for (int day = 1; day <= 7; day++) {
            System.out.println("Ημέρα " + day + ":");
            planDailyMeals(foods, dailyCalories, dailyProtein, dailyCarbs, dailyFat);
            System.out.println("-----------------------------");
        }
    }

    public static List<Food> loadFoodsFromCSV(String filePath) {
        List<Food> foods = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Παράλειψη της πρώτης γραμμής (κεφαλίδες)
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String name = values[0];
                double calories = Double.parseDouble(values[1]);
                double protein = Double.parseDouble(values[2]);
                double carbs = Double.parseDouble(values[3]);
                double fat = Double.parseDouble(values[4]);
                String category = values[5];
                foods.add(new Food(name, calories, protein, carbs, fat,category));
            }
        } catch (IOException e) {
            System.out.println("Σφάλμα κατά τη φόρτωση του αρχείου: " + e.getMessage());
        }
        return foods;
    }

    public static void planDailyMeals(List<Food> foods, double dailyCalories, double dailyProtein, double dailyCarbs, double dailyFat) {
        // Κατηγορίες γευμάτων
        String[] mealTypes = {"Πρωινό", "Δεκατιανό", "Μεσημεριανό", "Απογευματινό", "Βραδινό"};
        for (String mealType : mealTypes) {
            System.out.println(mealType + ":");
            // Κατανομή φαγητών για το κάθε γεύμα
            assignMealsForMealType(foods, dailyCalories / 5, dailyProtein / 5, dailyCarbs / 5, dailyFat / 5);
            System.out.println("-----------------------------");
        }
    }

    public static void assignMealsForMealType(List<Food> foods, double mealCalories, double mealProtein, double mealCarbs, double mealFat) {
        double totalCalories = 0;
        double totalProtein = 0;
        double totalCarbs = 0;
        double totalFat = 0;

        for (Food food : foods) {
            if (totalCalories + food.calories <= mealCalories &&
                totalProtein + food.protein <= mealProtein &&
                totalCarbs + food.carbs <= mealCarbs &&
                totalFat + food.fat <= mealFat) {

                System.out.println("Προσθήκη: " + food);
                totalCalories += food.calories;
                totalProtein += food.protein;
                totalCarbs += food.carbs;
                totalFat += food.fat;
            }

            if (totalCalories >= mealCalories &&
                totalProtein >= mealProtein &&
                totalCarbs >= mealCarbs &&
                totalFat >= mealFat) {
                break;
            }
        }

        System.out.println("Σύνολο για το γεύμα: " + "Θερμίδες: " + totalCalories + ", Πρωτεινη: " + totalProtein + "g, Υδατάνθρακες: " + totalCarbs + "g, Λίπος: " + totalFat + "g");
    }
}
