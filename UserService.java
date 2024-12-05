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
                if (data.length == 3) {
                    User user = new User(data[1], data[0], data[2]); // username, email, password
                    users.add(user);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading users: " + e.getMessage());
        }
    }

    public void addUser(String username, String email, String password) {
        User user = new User(username, email, password);
        users.add(user);
        saveUsersToFile();
    }

    private void saveUsersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (User user : users) {
                writer.write(user.getEmail() + "," + user.getUsername() + "," + user.getPassword());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving users: " + e.getMessage());
        }
    }
}
