package code.craft;

import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.LinearConstraintSet;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.SimpleBounds;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math3.optim.linear.SimplexSolver;

public class optimazation {


  // Calculates and returns the optimized quantity of ingredients present in the
  // meal, based on the goal and the percentage provided
  public static double[] optimizeMealIngredients(Macros[] meal, Macros totalGoal, double percentageOfGoal) {
    if (meal.length < 2) {
      throw new IllegalArgumentException("Meal must contain the name and at least two ingredients.");
    }

    // Calculate meal goal, based on percentage
    // Current example results: 500 calories, 67.5 proteins, 50 carbscarbs, 10 fats
    Macros goal = calculateMealGoal(totalGoal, percentageOfGoal);

    // Extraqct meal ingredients
    Macros[] ingredients = getMealIngredients(meal);

    int n = ingredients.length;

    // Objective: minimize total quantity used (or deviation from the goal)
    double[] objectiveCoefficients = new double[n]; // Minimize the sum of quantities
    for (int i = 0; i < n; i++) {
      objectiveCoefficients[i] = 1.0;
    }
    LinearObjectiveFunction objectiveFunction = new LinearObjectiveFunction(objectiveCoefficients, 1);

    // Constraints
    List<LinearConstraint> constraints = new ArrayList<>();

    // Calories constraint: sum(quantity[i] * calories[i]) >= goal.calories
    double[] calorieCoefficients = new double[n];
    for (int i = 0; i < n; i++) {
      calorieCoefficients[i] = ingredients[i].calories;
    }
    constraints.add(new LinearConstraint(calorieCoefficients, Relationship.GEQ, goal.calories));

    // Protein constraint: sum(quantity[i] * protein[i]) >= goal.protein
    double[] proteinCoefficients = new double[n];
    for (int i = 0; i < n; i++) {
      proteinCoefficients[i] = ingredients[i].protein;
    }
    constraints.add(new LinearConstraint(proteinCoefficients, Relationship.GEQ, goal.protein));

    // Carbs constraint: sum(quantity[i] * carbs[i]) >= goal.carbs
    double[] carbCoefficients = new double[n];
    for (int i = 0; i < n; i++) {
      carbCoefficients[i] = ingredients[i].carbs;
    }
    constraints.add(new LinearConstraint(carbCoefficients, Relationship.GEQ, goal.carbs));

    // Fat constraint: sum(quantity[i] * fat[i]) >= goal.fat
    double[] fatCoefficients = new double[n];
    for (int i = 0; i < n; i++) {
      fatCoefficients[i] = ingredients[i].fat;
    }
    constraints.add(new LinearConstraint(fatCoefficients, Relationship.GEQ, goal.fat));

    // Non-negative quantities: quantity[i] >= 0
    for (int i = 0; i < n; i++) {
      double[] nonNegative = new double[n];
      nonNegative[i] = 10.0;

      // The value arg determines the least amount of each ingredient
      // 1 means that all ingredients will be present)
      constraints.add(new LinearConstraint(nonNegative, Relationship.GEQ, 0.5));  
    }

    LinearConstraintSet constraintSet = new LinearConstraintSet(constraints);


    double[] lB = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,0.0 };

    // Upper bound (i.e. the most extreme value) for each macro: Change as needed
    double[] uB = new double[] { 10000.0, 1000.0, 1000.0, 1000.0, 1000.0,1000.0 };

    // Solve the optimization problem
    SimplexSolver solver = new SimplexSolver();
    PointValuePair solution = solver.optimize(
        objectiveFunction,
        constraintSet,
        GoalType.MINIMIZE,
        new SimpleBounds(lB, uB));

    // Extract solution
    double[] quantities = solution.getPoint();
    return quantities;
  }

  // Calculates and returns macro goals for a meal, based on the percentage
  // provided
  public static Macros calculateMealGoal(Macros goal, double percentageOfGoal) {
    double mealCaloriesGoal = goal.calories * percentageOfGoal / 100;
    double mealProteinGoal = goal.protein * percentageOfGoal / 100;
    double mealCarbsGoal = goal.carbs * percentageOfGoal / 100;
    double mealFatGoal = goal.fat * percentageOfGoal / 100;

    return new Macros(mealCaloriesGoal, mealProteinGoal, mealCarbsGoal, mealFatGoal);
  }

  // Constructs and returns an array containing only the meal ingredients
  public static Macros[] getMealIngredients(Macros[] meal) {
    Macros[] ings = new Macros[meal.length - 1];

    // First position includes the meal name, so we start from the second position
    // (index: 1)
    for (int i = 1; i < meal.length; i++) {
      ings[i - 1] = new Macros(meal[i].name, meal[i].calories, meal[i].protein, meal[i].carbs, meal[i].fat);
    }

    return ings;
  }
  }



