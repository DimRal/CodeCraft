package code.craft;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.Toggle;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextFlow;

public class Welcome extends Application {
    private UserService userService; // Αναφορά στο UserService
        private RadioButton newToggle;

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
            //Ρύθμιση αρχικών τίτλων
            Label titleLabel = new Label("Welcome to MealPlanner!");
            titleLabel.setStyle("-fx-font-size: 35px; -fx-font-weight: bold; -fx-text-fill: white;-fx-effect: dropshadow(gaussian, black, 5, 0.5, 1, 1);");
            Label secondTitle = new Label("Easy meals, better living.");
            secondTitle.setStyle("-fx-font-size: 25px;-fx-text-fill: white; ");

            //Δημιουργία κουμπιών
            Button button;
            Button button2;
            button=new Button();
            button2=new Button();
            button.setText("Let's get started!");
            button2.setText("I already have an account");
            button.setStyle("-fx-font-size: 20px; -fx-background-color: #5F6C51; -fx-text-fill: white; -fx-background-radius: 10;");
            button2.setStyle("-fx-font-size: 20px; -fx-background-color: #5F6C51; -fx-text-fill: white; -fx-background-radius: 10;");
            button.setOnAction(e -> window.setScene(createInput(window)));
            button2.setOnAction(e -> window.setScene(login(window,null)));


            // Δημιουργία VBox για τον τίτλο με padding
            VBox topLayout = new VBox();
            topLayout.getChildren().add(titleLabel);
            topLayout.getChildren().add(secondTitle);
            topLayout.setAlignment(Pos.CENTER); // Ευθυγράμμιση οριζόντια στο κέντρο
            topLayout.setPadding(new Insets(30, 0, 0, 0)); // 30 pixels από πάνω

            // Δημιουργία VBox για τα κουμπιά
            VBox buttonLayout = new VBox(10); // Απόσταση 10 pixel ανάμεσα στα κουμπιά
            buttonLayout.getChildren().addAll(button, button2);
            buttonLayout.setAlignment(Pos.CENTER); // Κουμπιά στο κέντρο

            // Δημιουργία κύριου VBox
            VBox layout = new VBox(220); // Απόσταση ανάμεσα στον τίτλο και τα κουμπιά
            layout.getChildren().addAll(topLayout, buttonLayout);
            layout.setStyle("-fx-font-family: 'Times New Roman'; -fx-padding: 20; -fx-background-image: url('background.png'); -fx-background-size: cover; -fx-background-repeat: no-repeat;");
            layout.setAlignment(Pos.TOP_CENTER); // Όλα τα περιεχόμενα στην κορυφή, κεντραρισμένα

            Scene mainScene = new Scene(layout);
            window.setScene(mainScene);
            window.show();
        }

        private Scene createInput(Stage window) {

            VBox layout = new VBox(10);
            layout.setStyle("-fx-alignment: center; -fx-font-family: 'Times New Roman'; -fx-padding: 20;-fx-background-image: url('background2.jpg');-fx-background-image: fill;-fx-background-size: cover; -fx-background-repeat: no-repeat;");

            //Τίτλος register στο παράθυρο register
            Label registerTitle = new Label("Register");
            registerTitle.setStyle( "-fx-font-size: 23px;-fx-font-weight: bold;-fx-text-fill: white;-fx-effect: dropshadow(gaussian, black, 5, 0.5, 1, 1);");

            //Ρύθμιση καθορισμένου πλάτους για τα ορθογώνια συμπλήρωσης
            double fieldWidth = 350;

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
            heightField.setPromptText("Enter your height in cm(for example: 1.75)");
            usernameField.setPrefWidth(fieldWidth);
            heightField.setMaxWidth(fieldWidth);

            TextField weightField = new TextField();
            weightField.setPromptText("Enter your weight in kgs(for example: 113)");
            weightField.setPrefWidth(fieldWidth);
            weightField.setMaxWidth(fieldWidth);

            TextField preferencesField = new TextField();
            preferencesField.setPromptText("Ingredients to avoid: αυγά,γάλα...");
            preferencesField.setPrefWidth(fieldWidth);
            preferencesField.setMaxWidth(fieldWidth);

            ComboBox<String> goalComboBox = new ComboBox<>();
            goalComboBox.getItems().addAll("Lose weight","Gain muscle");
            goalComboBox.setPromptText("Select your goal");

            ComboBox<String> sportComboBox = new ComboBox<>();
            sportComboBox.getItems().addAll("Περπάτημα","Τρέξιμο","Ποδήλατο","Κολύμβηση","Σχοινάκι","Βάρη","Γιόγκα","Πιλάτες","Χορός","CrossFit/HIIT","Αναρρίχηση","Ποδόσφαιρο","Μπάσκετ","Τένις","Βόλει(αγωνιστικό)","Πυγμαχία","Πολεμικές τέχνες","Καράτε","Τάε Κβο Ντο","Σκι","Κγιάκ","Κωπηλασία","Περπάτημα με βάρη","Πεζοπορία");
            sportComboBox.setPromptText("Select your sport");

           TextField trainingField = new TextField();
           trainingField.setPromptText("Weekly Training Hours: 5.5");
           trainingField.setVisible(false); // Αρχικά το πεδίο είναι κρυφό

           // Όταν επιλέγεται άθλημα, εμφανίζεται το πεδίο για τον αριθμό προπονήσεων
           sportComboBox.setOnAction(e -> {
             String selectedSport = sportComboBox.getValue();
             if (selectedSport != null) {
                trainingField.setVisible(true);
                trainingField.setText("");  // Καθαρίζει το πεδίο κάθε φορά που αλλάζει το άθλημα
             }
           });

            Button signupButton = new Button("Sign up!");
            signupButton.setOnAction(e -> {
            String username = usernameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();
            String age = ageField.getText();
            RadioButton selectedRadioButton = (RadioButton) gender.getSelectedToggle();
            String gender1;

            if (selectedRadioButton != null) {
                gender1 = selectedRadioButton.getText();
            } else {
                gender1 = "Not selected";
            }
            String height = heightField.getText();
            String weight = weightField.getText();
            String goal = goalComboBox.getValue();
            String pref = preferencesField.getText();
            String sports1 = sportComboBox.getValue();
            String training = trainingField.getText();

            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR); // Τύπος alert: Σφάλμα
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("All fields are required!"); // Μήνυμα
            alert.showAndWait();
            }  if  (!password.equals(confirmPassword)) {
                     Alert alert = new Alert(Alert.AlertType.INFORMATION); // Τύπος alert: Ενημέρωση
                     alert.setTitle("Error");
                     alert.setHeaderText(null);
                     alert.setContentText("Passwords do not match!"); // Μήνυμα
                     alert.showAndWait();
            } else if (password.equals(confirmPassword)) {
                try {
                    int age1 = Integer.
                    parseInt(ageField.getText());
                    double height1 = Double.parseDouble(heightField.getText());
                    double weight1 = Double.parseDouble(weightField.getText());
                    userService.addUser(username,email,password,age1,gender1,height1,weight1,goal,pref,sports1,training);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION); // Τύπος alert: Ενημέρωση
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("User registered succesfully!"); // Μήνυμα
                    alert.showAndWait();
                    double tr1=Double.parseDouble(training);
                    if (sports1==null){
                        sports1="";
                        tr1=0;
                    }
                    //window.setScene(waiting())
                    AI ai =new AI();
                    ArrayList<String> programm=ai.AI_meals(weight1, height1, age1, gender1, tr1*60,sports1,goal, pref);

                    window.setScene(login(window,programm));

                } catch (NumberFormatException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR); // Τύπος alert: Σφάλμα
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Age, height, and weight must be numeric values!"); // Μήνυμα
                    alert.showAndWait();
                }catch (URISyntaxException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
    layout.getChildren().addAll(
                registerTitle,
                new Label("Username:"), usernameField,
                new Label("Email:"), emailField,
                new Label("Password:"), passwordField,
                new Label("Confirm Password"),confirmPasswordField,
                new Label("Age:"),ageField,
                new Label("Gender:"),option1,option2,
                new Label("Height:"),heightField,
                new Label("Weight:"),weightField,
                new Label("Ingredients To Avoid:"),preferencesField,
                new Label("Goal:"), goalComboBox,
                new Label("Sport and Weekly Training Hours"), sportComboBox,trainingField,
                signupButton
        );

        //Δημιουργία μπάρα κύλησης
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(layout);
        scrollPane.setFitToWidth(true); // Κάνει το πλάτος να προσαρμόζεται
        scrollPane.setPannable(true);  // Επιτρέπει κύλιση με drag
        return new Scene(scrollPane, 700, 500);
    }
    private Scene loginScene;
    private Scene login(Stage window,ArrayList<String>program) {
        if (loginScene != null) {
            return loginScene; // Επιστροφή της ήδη δημιουργημένης σκηνής
        }
        VBox layout = new VBox(10);
        layout.setStyle("-fx-alignment: center; -fx-font-family: 'Times New Roman'; -fx-padding: 20;-fx-background-image:url('background2.jpg');-fx-background-size: cover; -fx-background-repeat: no-repeat;");

        double widthField = 350;

        TextField usernameLogin = new TextField();
        usernameLogin.setPromptText("Username or Email");
        usernameLogin.setPrefWidth(widthField);
        usernameLogin.setMaxWidth(widthField);

        TextField passwordLogin = new TextField();
        passwordLogin.setPromptText("Password");
        passwordLogin.setPrefWidth(widthField);
        passwordLogin.setMaxWidth(widthField);

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
                window.setScene(Programm(program));
            } else {
                Alert failureAlert = new Alert(Alert.AlertType.ERROR);
                failureAlert.setTitle("Login Failed");
                failureAlert.setHeaderText(null);
                failureAlert.setContentText("Incorrect email or password. Please try again.");
                failureAlert.showAndWait();
            }

        });
        Label loginTitle = new Label("Login to MealPlanner");
        loginTitle.setStyle("-fx-font-size: 23px;-fx-font-weight: bold;-fx-text-fill: white;-fx-effect: dropshadow(gaussian, black, 5, 0.5, 1, 1);");

        layout.getChildren().addAll(
        loginTitle,usernameLogin,passwordLogin, loginButton
        );

         return new Scene(layout, 700, 500); // Επιστρέφει τη σκηνή στο τέλος
    }

private Scene Programm(ArrayList<String>program) {
        VBox layout = new VBox();
        layout.setStyle("-fx-alignment: center; -fx-font-family: 'Times New Roman'; -fx-padding: 20;-fx-background-image:url('background2.jpg');-fx-background-size: cover; -fx-background-repeat: no-repeat;");
 
        Label welcomeLabel = new Label("Welcome to MealPlanner!");
        welcomeLabel.setStyle("-fx-font-size: 30px;-fx-font-weight: bold;-fx-text-fill: white;-fx-effect: dropshadow(gaussian, black, 5, 0.5, 1, 1);");
        Label Title = new Label("Your programm");
        Title.setStyle("-fx-font-size: 23px;-fx-font-weight: bold;-fx-text-fill: white;-fx-effect: dropshadow(gaussian, black, 5, 0.5, 1, 1);");
        VBox.setMargin(welcomeLabel, new Insets(-300, 0, 10, 0));
        VBox.setMargin(Title, new Insets(20, 0, 10, 0));//0
        VBox layout1 = new VBox(220);
       
       
 
       // Δημιουργία GridPane
    GridPane gridPane = new GridPane();
    gridPane.setHgap(10); // Απόσταση μεταξύ στηλών
    gridPane.setVgap(5); // Απόσταση μεταξύ γραμμών
    gridPane.setStyle("-fx-alignment: top-center;");
 
    String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    String[][] mealData = {{program.get(0)},{program.get(1)},{program.get(2)},{program.get(3)},{program.get(4)},{program.get(5)},{program.get(6)}};
    // Προσθήκη τίτλων (Ημέρες) στην πρώτη γραμμή
    for (int i = 0; i < days.length; i++) {
        Label dayLabel = new Label(days[i]);
        dayLabel.setStyle("-fx-font-size: 15px;-fx-font-weight: bold;-fx-text-fill: white;-fx-effect: dropshadow(gaussian, black, 5, 0.5, 1, 1);");
        gridPane.add(dayLabel, i + 1, 0); // Από τη στήλη 1 και πάνω
    }
 
 

 
    // Προσθήκη δεδομένων στις υπόλοιπες στήλες (ημέρες)
    for (int i = 0; i < mealData.length; i++) {
        for (int j = 0; j < mealData[i].length; j++) {
            Label dataLabel = new Label(mealData[i][j]);
            dataLabel.setStyle("-fx-font-size: 8px; -fx-text-fill: black;");
            gridPane.add(dataLabel, i + 1, j + 1); // Στήλες μετά την πρώτη
        }
    }
 
 
 
 
 
 
    VBox.setMargin(gridPane, new Insets(60, 0, 0, 0));//20
    layout.getChildren().addAll(welcomeLabel, Title, gridPane);
    layout.setAlignment(Pos.TOP_CENTER);
       
 
        return new Scene(layout, 700, 500); // Επιστρέφει τη σκηνή στο τέλος
}
}
