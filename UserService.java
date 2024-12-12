import com.google.gson.Gson;

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
            String line;
            users.clear(); // Καθαρίζουμε την υπάρχουσα λίστα πριν φορτώσουμε νέα δεδομένα
            while ((line = reader.readLine()) != null) {
                try {
                    // Μετατρέπουμε κάθε γραμμή JSON σε αντικείμενο User
                    User user = gson.fromJson(line, User.class);
                    users.add(user);
                } catch (Exception e) {
                    System.err.println("Σφάλμα στην ανάγνωση χρήστη από τη γραμμή: " + line);
                    e.printStackTrace();
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
            writer.write("["); // Ξεκινάμε την λίστα JSON
            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                // Γράφουμε τον χρήστη σε μορφή JSON
                writer.write(gson.toJson(user));
                if (i < users.size() - 1) {
                    writer.write(",\n"); // Αν δεν είναι ο τελευταίος χρήστης, προσθέτουμε κόμμα και νέα γραμμή
                }
            }
            writer.write("]"); // Κλείνουμε την λίστα JSON
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
