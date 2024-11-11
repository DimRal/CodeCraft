public class MealOptimizer {

    // Ημερήσιοι μακροθρεπτικοί στόχοι
    static double targetCalories = 2000;
    static double targetProtein = 150;
    static double targetCarbs = 250;
    static double targetFat = 70;

    // Πληροφορίες για τα φαγητά [Θερμίδες ανά 100γρ, Πρωτεΐνη, Υδατάνθρακες, Λίπος]
    static double[][] foodData = {
        {200, 30, 0, 5},    // Στήθος Κοτόπουλου
        {215, 5, 45, 1},    // Καφέ Ρύζι
        {55, 4, 10, 0.5},   // Μπρόκολο
        {120, 0, 0, 14},    // Ελαιόλαδο
        {80, 1, 20, 0},     // Πατάτα
        {150, 12, 5, 10},   // Ξηροί Καρποί
        {60, 1, 15, 0},     // Μήλο
        {45, 0, 12, 0},     // Μπανάνα
        {170, 15, 0, 10},   // Σολομός
        {95, 6, 15, 1}      // Όσπρια
    };

    static String[] foodNames = {"Στήθος Κοτόπουλου", "Καφέ Ρύζι", "Μπρόκολο", "Ελαιόλαδο", 
                                 "Πατάτα", "Ξηροί Καρποί", "Μήλο", "Μπανάνα", "Σολομός", "Όσπρια"};

    // Κατανομή θερμίδων ανά γεύμα
    static double[] mealProportion = {0.25, 0.1, 0.3, 0.1, 0.25}; // Πρωινό, Δεκατιανό, Μεσημεριανό, Απογευματινό, Βραδινό
    static String[] mealNames = {"Πρωινό", "Δεκατιανό", "Μεσημεριανό", "Απογευματινό", "Βραδινό"};

    public static void main(String[] args) {
        // Εβδομάδα (Δευτέρα έως Κυριακή)
        String[] daysOfWeek = {"Δευτέρα", "Τρίτη", "Τετάρτη", "Πέμπτη", "Παρασκευή", "Σάββατο", "Κυριακή"};

        for (String day : daysOfWeek) {
            System.out.println("Ημέρα: " + day);
            for (int mealIndex = 0; mealIndex < mealNames.length; mealIndex++) {
                System.out.println("Γεύμα: " + mealNames[mealIndex]);

                // Υπολογισμός στόχων για το συγκεκριμένο γεύμα
                double mealCalories = targetCalories * mealProportion[mealIndex];
                double mealProtein = targetProtein * mealProportion[mealIndex];
                double mealCarbs = targetCarbs * mealProportion[mealIndex];
                double mealFat = targetFat * mealProportion[mealIndex];

                // Βέλτιστη κατανομή φαγητών για το συγκεκριμένο γεύμα
                int[] bestCombination = findBestCombination(mealCalories, mealProtein, mealCarbs, mealFat);
                if (bestCombination != null) {
                    for (int i = 0; i < bestCombination.length; i++) {
                        double grams = bestCombination[i] * 100;  // Κάθε μερίδα ισοδυναμεί με 100 γραμμάρια
                        System.out.printf("%s: %.0f γραμμάρια%n", foodNames[i], grams);
                    }
                } else {
                    System.out.println("Δεν βρέθηκε λύση για τους στόχους του γεύματος.");
                }
                System.out.println();
            }
            System.out.println("------------");
        }
    }

    // Μέθοδος για εύρεση της καλύτερης κατανομής τροφών για συγκεκριμένους μακροθρεπτικούς στόχους
    private static int[] findBestCombination(double targetCalories, double targetProtein, double targetCarbs, double targetFat) {
        int[] bestCombination = null;
        double bestDeviation = Double.MAX_VALUE;

        // Brute force για μερίδες από 0 έως 10
        for (int i1 = 0; i1 <= 10; i1++) {
            for (int i2 = 0; i2 <= 10; i2++) {
                for (int i3 = 0; i3 <= 10; i3++) {
                    for (int i4 = 0; i4 <= 10; i4++) {
                        for (int i5 = 0; i5 <= 10; i5++) {
                            for (int i6 = 0; i6 <= 10; i6++) {
                                for (int i7 = 0; i7 <= 10; i7++) {
                                    for (int i8 = 0; i8 <= 10; i8++) {
                                        for (int i9 = 0; i9 <= 10; i9++) {
                                            for (int i10 = 0; i10 <= 10; i10++) {
                                                int[] quantities = {i1, i2, i3, i4, i5, i6, i7, i8, i9, i10};
                                                double deviation = calculateDeviation(quantities, targetCalories, targetProtein, targetCarbs, targetFat);

                                                // Αποθήκευση του καλύτερου συνδυασμού
                                                if (deviation < bestDeviation) {
                                                    bestDeviation = deviation;
                                                    bestCombination = quantities.clone();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return bestCombination;
    }

    // Υπολογισμός της απόκλισης από τους στόχους για συγκεκριμένο συνδυασμό
    private static double calculateDeviation(int[] quantities, double targetCalories, double targetProtein, double targetCarbs, double targetFat) {
        double totalCalories = 0;
        double totalProtein = 0;
        double totalCarbs = 0;
        double totalFat = 0;

        // Υπολογισμός των συνολικών θρεπτικών του συνδυασμού
        for (int i = 0; i < quantities.length; i++) {
            totalCalories += foodData[i][0] * quantities[i];
            totalProtein += foodData[i][1] * quantities[i];
            totalCarbs += foodData[i][2] * quantities[i];
            totalFat += foodData[i][3] * quantities[i];
        }

        // Υπολογισμός της απόκλισης από τους στόχους
        double deviation = Math.pow(targetCalories - totalCalories, 2)
                + Math.pow(targetProtein - totalProtein, 2)
                + Math.pow(targetCarbs - totalCarbs, 2)
                + Math.pow(targetFat - totalFat, 2);

        return deviation;
    }
}
