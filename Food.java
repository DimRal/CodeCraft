public class Food {
    String name;
    double calories;
    double  protein;
    double carbs;
    double fat;
    String category;

    public Food(String name, double calories, double protein, double carbs, double fat, String category) {
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
        this.category=category;
    } 

    @Override
    public String toString() {
        return name + " (Θερμίδες: " + calories + ", Πρωτεΐνη: " + protein + "g, Υδατάνθρακες: " + carbs + "g, Λίπος: " + fat + "g)";
    }
}
