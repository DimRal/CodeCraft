import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;

public class Welcome extends Application {
    private UserService userService; // Αναφορά στο UserService
    
    public static void main (String []args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage window) {

        userService = new UserService();
        userService.loadUsersFromFile();


        //Ρύθμιση διαστάσεων και ονόματος εισαγωγικού παραθύρου
        window.setWidth(700);
        window.setHeight(500);
        window.setTitle("MealPlanner");
        Label titleLabel = new Label("Welcome to MealPlanner!");
        titleLabel.setStyle("-fx-font-size: 23px; -fx-alignment: center; -fx-padding: 20;");

        //Δημιουργία κουμπιών
        Button button;
        Button button2;
        button=new Button();
        button2=new Button();
        button.setText("Let's get started!");
        button2.setText("I already have an account");
        button.setStyle("-fx-font-size:18px;");
        button2.setStyle("-fx-font-size:18px;");
        button.setOnAction(e -> window.setScene(createInput()));
        button2.setOnAction(e -> window.setScene(login()));

        VBox layout = new VBox(10); // Απόσταση 10 pixel ανάμεσα στα στοιχεία και κουμπιά
        layout.getChildren().addAll(titleLabel,button,button2);
        Scene mainScene=new Scene(layout);

        layout.setStyle("-fx-alignment: center; -fx-padding: 20;  -fx-background-color: #6ec475");
        window.setScene(mainScene);
        window.show();
    }
    private Scene createInput() {

        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 25; -fx-background-color: #6ec475");

        //Τίτλος register στο παράθυρο register
        Label registerTitle = new Label("Register");
        registerTitle.setStyle("-fx-font-size: 23px; -fx-alignment: center; -fx-padding: 20;");

        //Ρύθμιση καθορισμένου πλάτους για τα ορθογώνια συμπλήρωσης
        double fieldWidth = 450;

        // Προσθήκη πεδίων εισαγωγής στοιχείων
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter your username");
        usernameField.setPrefWidth(fieldWidth);
        usernameField.setMaxWidth(fieldWidth);

        TextField emailField = new TextField();
        emailField.setPromptText("Enter your email");
        emailField.setPrefWidth(fieldWidth);
        emailField.setMaxWidth(fieldWidth);


        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");
        passwordField.setPrefWidth(fieldWidth);
        passwordField.setMaxWidth(fieldWidth);

        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Confirm your password");
        confirmPasswordField.setPrefWidth(fieldWidth);
        confirmPasswordField.setMaxWidth(fieldWidth);

        TextField ageField = new TextField();
        ageField.setPromptText("Enter your age");
        ageField.setPrefWidth(fieldWidth);
        ageField.setMaxWidth(fieldWidth);

        ToggleGroup gender = new ToggleGroup();
        RadioButton option1 = new RadioButton("Male");
        option1.setToggleGroup(gender);
        RadioButton option2 = new RadioButton("Female");
        option2.setToggleGroup(gender);

        TextField heightField = new TextField();
        heightField.setPromptText("Enter your height in cm");
        usernameField.setPrefWidth(fieldWidth);
        heightField.setMaxWidth(fieldWidth);

        TextField weightField = new TextField();
        weightField.setPromptText("Enter your weight in kgs");
        weightField.setPrefWidth(fieldWidth);
        weightField.setMaxWidth(fieldWidth);

        ComboBox<String> goalComboBox = new ComboBox<>();
        goalComboBox.getItems().addAll("Lose weight", "Gain muscle", "Maintain weight");
        goalComboBox.setPromptText("Select your goal");

        Button signupButton = new Button("Sign up!");
        signupButton.setOnAction(e -> {
            String username = usernameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();

            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "All fields are required!");
    
            }

            // Αποθήκευση λογικής
            if (passwordField.getText().equals(confirmPasswordField.getText())) {
                userService.addUser(usernameField.getText(), emailField.getText(), passwordField.getText());
                JOptionPane.showMessageDialog(null, "User registered successfully!");
                
            }else {
                JOptionPane.showMessageDialog(null, "Passwords do not match!");
            }

    });
    
    

        layout.getChildren().addAll(
                registerTitle,
                new Label("Username:"), usernameField,
                new Label("Email:"), emailField,
                new Label("Password:"), passwordField,
                new Label("Confirm Password"),confirmPasswordField,
                new Label("Age"),ageField,
                new Label("Gender"),option1,option2,
                new Label("Height"),heightField,
                new Label("Weight"),weightField,
                new Label("Goal:"), goalComboBox,
                signupButton
        );

        //Δημιουργία μπάρα κύλησης
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(layout);
        scrollPane.setFitToWidth(true); // Κάνει το πλάτος να προσαρμόζεται
        scrollPane.setPannable(true);  // Επιτρέπει κύλιση με drag
        return new Scene(scrollPane, 700, 500);
    }
    private Scene login() {
        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 25; -fx-background-color: #6ec475");

        TextField usernameLogin = new TextField();
        usernameLogin.setPromptText("Username or Email");

        TextField passwordLogin = new TextField();
        passwordLogin.setPromptText("Password");
        

        //κουμπί σύνδεσης
        Button loginButton = new Button("Login");
        loginButton.setOnAction(e ->{
            String email = usernameLogin.getText();
            String Password = passwordLogin.getText();
            //Λογική σύνδεσης
            User loggesInUser = userService.loginUser(email, Password); 
            if (loggesInUser != null) {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Login Successful");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Welcome, " + loggesInUser.getUsername() + "!");
                successAlert.showAndWait();
            } else {
                Alert failureAlert = new Alert(Alert.AlertType.ERROR);
                failureAlert.setTitle("Login Failed");
                failureAlert.setHeaderText(null);
                failureAlert.setContentText("Incorrect email or password. Please try again.");
                failureAlert.showAndWait();
            }
    
        });
        layout.getChildren().addAll(
        new Label("Login to MealPlanner"),
        usernameLogin,passwordLogin, loginButton
        );

         return new Scene(layout, 700, 500); // Επιστρέφει τη σκηνή στο τέλος
    }
}