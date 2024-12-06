import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Welcome extends Application {
    private UserService userService; // Αναφορά στο UserService
    
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage window) {
        userService = new UserService();
        userService.loadUsersFromFile();

        // Ρύθμιση αρχικής σκηνής
        window.setWidth(700);
        window.setHeight(500);
        window.setTitle("MealPlanner");
        window.setScene(createMainScene(window));
        window.show();
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private Scene createMainScene(Stage window) {
        VBox layout = new VBox(10);
        layout.setStyle("-fx-alignment: center; -fx-padding: 20; -fx-background-color: #6ec475");

        Label titleLabel = new Label("Welcome to MealPlanner!");
        titleLabel.setStyle("-fx-font-size: 23px; -fx-padding: 20;");

        Button registerButton = new Button("Let's get started!");
        registerButton.setStyle("-fx-font-size:18px;");
        registerButton.setOnAction(e -> window.setScene(createInput(window)));

        Button loginButton = new Button("I already have an account");
        loginButton.setStyle("-fx-font-size:18px;");
        loginButton.setOnAction(e -> window.setScene(login(window)));

        layout.getChildren().addAll(titleLabel, registerButton, loginButton);
        return new Scene(layout, 700, 500);
    }

    private Scene createInput(Stage window) {
        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 25; -fx-background-color: #6ec475");

        Label registerTitle = new Label("Register");
        registerTitle.setStyle("-fx-font-size: 23px; -fx-padding: 20;");

        double fieldWidth = 450;

        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter your username");
        usernameField.setPrefWidth(fieldWidth);

        TextField emailField = new TextField();
        emailField.setPromptText("Enter your email");
        emailField.setPrefWidth(fieldWidth);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");
        passwordField.setPrefWidth(fieldWidth);

        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Confirm your password");
        confirmPasswordField.setPrefWidth(fieldWidth);

        TextField ageField = new TextField();
        ageField.setPromptText("Enter your age");
        ageField.setPrefWidth(fieldWidth);

        ToggleGroup genderGroup = new ToggleGroup();
        RadioButton maleOption = new RadioButton("Male");
        maleOption.setToggleGroup(genderGroup);
        RadioButton femaleOption = new RadioButton("Female");
        femaleOption.setToggleGroup(genderGroup);

        TextField heightField = new TextField();
        heightField.setPromptText("Enter your height in cm");
        heightField.setPrefWidth(fieldWidth);

        TextField weightField = new TextField();
        weightField.setPromptText("Enter your weight in kgs");
        weightField.setPrefWidth(fieldWidth);

        ComboBox<String> goalComboBox = new ComboBox<>();
        goalComboBox.getItems().addAll("Lose weight", "Gain muscle", "Maintain weight");
        goalComboBox.setPromptText("Select your goal");

        Button signupButton = new Button("Sign up!");
        signupButton.setOnAction(e -> {
            String username = usernameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();
            String ageText = ageField.getText();
            String heightText = heightField.getText();
            String weightText = weightField.getText();
            String goal = goalComboBox.getValue();

            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() ||
                ageText.isEmpty() || heightText.isEmpty() || weightText.isEmpty() || goal == null ||
                genderGroup.getSelectedToggle() == null) {
                showAlert(Alert.AlertType.ERROR, "Error", "All fields must be filled!");
                return;
            }

            try {
                int age = Integer.parseInt(ageText);
                double height = Double.parseDouble(heightText);
                double weight = Double.parseDouble(weightText);

                if (age < 1 || age > 130) {
                    showAlert(Alert.AlertType.ERROR, "Error", "Age must be between 1 and 130");
                    return;
                }
                if (height < 50 || height > 300) {
                    showAlert(Alert.AlertType.ERROR, "Error", "Height must be between 50 and 300 cm");
                    return;
                }
                if (weight < 1 || weight > 700) {
                    showAlert(Alert.AlertType.ERROR, "Error", "Weight must be between 1 and 700 kg");
                    return;
                }
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Error", "Age, height, and weight must be valid numbers");
                return;
            }

            if (!password.equals(confirmPassword)) {
                showAlert(Alert.AlertType.ERROR, "Error", "Passwords do not match!");
                return;
            }

            String gender = ((RadioButton) genderGroup.getSelectedToggle()).getText();
            userService.addUser(username, email, password, Integer.parseInt(ageText), Double.parseDouble(heightText), Double.parseDouble(weightText), goal);
            showAlert(Alert.AlertType.INFORMATION, "Success", "User registered successfully!");
            window.setScene(createMainScene(window));
        });

        Button backButton = new Button("Back to Home");
        backButton.setOnAction(e -> window.setScene(createMainScene(window)));

        layout.getChildren().addAll(
            registerTitle, new Label("Username:"), usernameField,
            new Label("Email:"), emailField, new Label("Password:"), passwordField,
            new Label("Confirm Password"), confirmPasswordField, new Label("Age"), ageField,
            new Label("Gender"), maleOption, femaleOption, new Label("Height(cm)"), heightField,
            new Label("Weight(kg)"), weightField, new Label("Goal:"), goalComboBox,
            signupButton, backButton
        );

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(layout);
        scrollPane.setFitToWidth(true);
        return new Scene(scrollPane, 700, 500);
    }

    private Scene login(Stage window) {
        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 25; -fx-background-color: #6ec475");

        TextField usernameLogin = new TextField();
        usernameLogin.setPromptText("Username or Email");

        PasswordField passwordLogin = new PasswordField();
        passwordLogin.setPromptText("Password");

        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            String email = usernameLogin.getText();
            String password = passwordLogin.getText();
            User loggedInUser = userService.loginUser(email, password);
            if (loggedInUser != null) {
                showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome, " + loggedInUser.getUsername() + "!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Login Failed", "Incorrect email or password. Please try again.");
            }
        });

        Button backButton = new Button("Back to Home");
        backButton.setOnAction(e -> window.setScene(createMainScene(window)));

        layout.getChildren().addAll(new Label("Login to MealPlanner"), usernameLogin, passwordLogin, loginButton, backButton);
        return new Scene(layout, 700, 500);
    }
}