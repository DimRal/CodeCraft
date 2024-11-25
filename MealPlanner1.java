public class MealPlanner1 {
    public static void main(String[] args) {
        UserService userService = new UserService();
        new MealPlannerGUI(userService);
    }
}

