import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<User> users = new ArrayList<>();
    private static final String FILE_NAME = "users.txt";

    // Εγγραφή χρήστη
    public boolean registerUser(String username, String email, String password) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                System.out.println("Το email χρησιμοποιείται ήδη.");
                return false;
            }
        }

        User newUser = new User(username, email, password);
        users.add(newUser);
        saveUsersToFile();  // Αποθήκευση των χρηστών στο αρχείο
        System.out.println("Η εγγραφή ήταν επιτυχής!");
        return true;
    }

    // Σύνδεση χρήστη
    public User loginUser(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                System.out.println("Η σύνδεση ήταν επιτυχής!");
                return user;
            }
        }
        System.out.println("Λάθος email ή κωδικός.");
        return null;
    }

    // Αποθήκευση χρηστών σε αρχείο
    private void saveUsersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (User user : users) {
                writer.write(user.getEmail() + "," + user.getUsername() + "," + user.getPassword());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Φόρτωση χρηστών από αρχείο
    public void loadUsersFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    User user = new User(data[1], data[0], data[2]);
                    users.add(user);
                }
            }
        } catch (IOException e) {
            System.out.println("Δεν βρέθηκε αρχείο χρηστών.");
        }
    }
}

