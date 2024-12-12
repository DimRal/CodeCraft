import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static final String FILE_NAME = "users.json"; // Όνομα αρχείου JSON
    private final List<User> users = new ArrayList<>();
    private final Gson gson = new Gson(); // Χρησιμοποιούμε την Gson για την αποθήκευση και φόρτωση δεδομένων

    public List<User> getUsers() {
        return users;
    }

    // Φόρτωση χρηστών από το αρχείο JSON
    public void loadUsersFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("Το αρχείο δεν βρέθηκε. Δημιουργείται ένα νέο αρχείο.");
            return;
        }
    
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            // Διαβάζουμε το JSON array και το μετατρέπουμε σε λίστα χρηστών
            User[] loadedUsers = gson.fromJson(reader, User[].class);
            if (loadedUsers != null) {
                users.clear();
                for (User user : loadedUsers) {
                    users.add(user);
                }
            }
        } catch (IOException e) {
            System.err.println("Σφάλμα κατά την φόρτωση χρηστών: " + e.getMessage());
        }
    }
    
    

     // Προσθήκη χρήστη στη λίστα και αποθήκευση στο αρχείο JSON
    public void addUser(String username, String email, String password , int  age, double height, double weight) {
        User user = new User(username, email, password, age, height, weight);
        users.add(user);
        saveUsersToFile(); // Αποθήκευση χρηστών στο αρχείο JSON
    }

    // Αποθήκευση χρηστών στο αρχείο JSON
    private void saveUsersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            Gson prettyGson = new GsonBuilder().setPrettyPrinting().create(); // Δημιουργία Gson με μορφοποίηση
            String json = prettyGson.toJson(users); // Μετατροπή της λίστας χρηστών σε μορφοποιημένο JSON
         writer.write(json); // Γράψιμο στο αρχείο
        } catch (IOException e) {
            System.err.println("Σφάλμα κατά την αποθήκευση χρηστών: " + e.getMessage());
        }
    }


    // Σύνδεση χρήστη με βάση το email ή το username και τον κωδικό
    public User loginUser(String email, String password) {
        for (User user : users) { // Υποθέτουμε ότι το 'users' είναι η λίστα των χρηστών.
            if ((user.getEmail().equals(email) || user.getUsername().equals(email)) && user.getPassword().equals(password)) {
                return user; // Επιστροφή του χρήστη αν τα στοιχεία είναι σωστά.
            }
        }
        return null; // Επιστροφή null αν δεν βρέθηκε χρήστης.
    }
}
