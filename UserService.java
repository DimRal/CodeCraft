import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static final String FILE_NAME = "users.txt"; // Όνομα αρχείου
    private final List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }

    public void loadUsersFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 6) {
                    try {
                        String username = data[0];
                        String email = data[1];
                        String password = data[2];
                        int age = Integer.parseInt(data[3]);
                        double height = Double.parseDouble(data[4]);
                        double weight = Double.parseDouble(data[5]);

                        User user = new User(username, email, password, age, height, weight); // username,email,password,age,height,weight
                    users.add(user);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid number format in line: " + line);
                }
            }
        }
     }catch (IOException e) {
            System.err.println("Error loading users: " + e.getMessage());
        }
    }

    public void addUser(String username, String email, String password , int  age, double height, double weight) {
        User user = new User(username, email, password, age, height, weight);
        users.add(user);
        saveUsersToFile();
    }

    private void saveUsersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (User user : users) {
                writer.write(user.getUsername() + "," + user.getEmail() + "," + user.getPassword() + "," + user.getAge() + "," + user.getHeight() + "," + user.getWeight());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving users: " + e.getMessage());
        }
    }

    public User loginUser(String email, String password) {
        for (User user : users) { // Υποθέτουμε ότι το `users` είναι η λίστα των χρηστών.
            if ((user.getEmail().equals(email) || user.getUsername().equals(email)) && user.getPassword().equals(password)) {
                return user; // Επιστροφή του χρήστη αν τα στοιχεία είναι σωστά.
            }
        }
        return null; // Επιστροφή null αν δεν βρέθηκε χρήστης.
    }

}
