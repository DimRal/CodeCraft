import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MealPlannerGUI {
    private JFrame frame;
    private JPanel panel;
    private JTextField usernameField, emailField, passwordField, ageField, heightField, weightField;
    private JRadioButton maleRadioButton, femaleRadioButton;
    private ButtonGroup genderGroup;
    private JComboBox<String> goalComboBox;
    private JButton registerButton, loginButton;

    private UserService userService;

    public MealPlannerGUI(UserService userService) {
        this.userService = userService;
        userService.loadUsersFromFile();  // Φορτώνουμε τους χρήστες από το αρχείο

        frame = new JFrame("MealPlanner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2));  // Κάνουμε το panel να έχει 2 στήλες

        frame.add(panel, BorderLayout.CENTER);

        addRegistrationForm();

        frame.setVisible(true);
    }

    private void addRegistrationForm() {
        // Εισαγωγή ονόματος χρήστη
        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        // Εισαγωγή email
        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);

        // Εισαγωγή κωδικού
        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        // Εισαγωγή φύλου
        panel.add(new JLabel("Gender:"));
        maleRadioButton = new JRadioButton("Male");
        femaleRadioButton = new JRadioButton("Female");
        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);

        JPanel genderPanel = new JPanel();
        genderPanel.add(maleRadioButton);
        genderPanel.add(femaleRadioButton);

        panel.add(genderPanel);

        // Εισαγωγή ηλικίας
        panel.add(new JLabel("Age:"));
        ageField = new JTextField();
        panel.add(ageField);

        // Εισαγωγή ύψους
        panel.add(new JLabel("Height (cm):"));
        heightField = new JTextField();
        panel.add(heightField);

        // Εισαγωγή βάρους
        panel.add(new JLabel("Weight (kg):"));
        weightField = new JTextField();
        panel.add(weightField);

        // Στόχος
        panel.add(new JLabel("Goal:"));
        goalComboBox = new JComboBox<>(new String[]{"Lose weight", "Gain muscle", "Maintain weight"});
        panel.add(goalComboBox);

        // Κουμπί εγγραφής
        registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
        panel.add(registerButton);

        // Κουμπί σύνδεσης
        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginUser();
            }
        });
        panel.add(loginButton);
    }

    private void registerUser() {
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        if (userService.registerUser(username, email, password)) {
            JOptionPane.showMessageDialog(frame, "Registration successful! Now log in.");
            addLoginForm();  // Αν η εγγραφή είναι επιτυχής, δείχνουμε τη φόρμα σύνδεσης
        }
    }

    private void loginUser() {
        String email = emailField.getText();
        String password = passwordField.getText();

        User loggedInUser = userService.loginUser(email, password);
        if (loggedInUser != null) {
            collectAdditionalInfo(loggedInUser);
        } else {
            JOptionPane.showMessageDialog(frame, "Login failed! Incorrect email or password.");
        }
    }

    private void collectAdditionalInfo(User user) {
        String gender = maleRadioButton.isSelected() ? "Male" : "Female";
        user.setGender(gender);

        int age = Integer.parseInt(ageField.getText());
        user.setAge(age);

        double height = Double.parseDouble(heightField.getText());
        user.setHeight(height);

        double weight = Double.parseDouble(weightField.getText());
        user.setWeight(weight);

        String goal = (String) goalComboBox.getSelectedItem();
        user.setGoal(goal);

        JOptionPane.showMessageDialog(frame, "Your details have been saved successfully!");
    }

    private void addLoginForm() {
        // Σβήνουμε τα πεδία εγγραφής και εμφανίζουμε τη φόρμα σύνδεσης
        panel.removeAll();
        panel.add(new JLabel("Email:"));
        panel.add(emailField);

        panel.add(new JLabel("Password:"));
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginUser();
            }
        });
        panel.add(loginButton);

        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                UserService userService = new UserService();
                new MealPlannerGUI(userService);
            }
        });
    }
}
