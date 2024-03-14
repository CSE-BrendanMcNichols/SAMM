package backEnd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class UserList {
    private static UserList instance;
    private HashMap<UUID, User> usersById;
    private HashMap<String, User> usersByEmail;
    private ArrayList<User> users;

    private UserList() {
        usersById = new HashMap<>();
        usersByEmail = new HashMap<>();
        users = new ArrayList<>();
        loadUsers();
    }

    public static synchronized UserList getInstance() {
        if (instance == null) {
            instance = new UserList();
        }
        return instance;
    }

    private void loadUsers() {
        ArrayList<Student> students = DataLoader.getStudents();
        ArrayList<Advisor> advisors = DataLoader.getAdvisors();
        ArrayList<Administrator> administrators = DataLoader.getAdministrators();

        if (students != null) students.forEach(this::addUser);
        if (advisors != null) advisors.forEach(this::addUser);
        if (administrators != null) administrators.forEach(this::addUser);
    }

    public boolean addUser(User user) {
        if (usersById.containsKey(user.getUuid()) || usersByEmail.containsKey(user.getEmail().toLowerCase())) {
            System.out.println("User already exists.");
            return false;
        }
        usersById.put(user.getUuid(), user);
        usersByEmail.put(user.getEmail().toLowerCase(), user);
        users.add(user);
        return true;
    }

    public User getUserByUscUsername(String username) {
        return users.stream()
                    .filter(user -> user.getUsername().equalsIgnoreCase(username))
                    .findFirst()
                    .orElse(null);
    }

    public User getUserByUscId(String uscid) {
        return users.stream()
                    .filter(user -> user.getUscid().equals(uscid))
                    .findFirst()
                    .orElse(null);
    }

    public boolean deleteUser(UUID uuid) {
        User user = usersById.remove(uuid);
        if (user == null) {
            return false;
        }
        usersByEmail.remove(user.getEmail().toLowerCase());
        users.remove(user);
        return true;
    }

    public boolean updateUser(UUID uuid, User updatedUser) {
        if (!usersById.containsKey(uuid)) {
            return false;
        }
        User existingUser = usersById.get(uuid);

        usersByEmail.remove(existingUser.getEmail().toLowerCase());
        users.remove(existingUser);

        usersById.put(uuid, updatedUser);
        usersByEmail.put(updatedUser.getEmail().toLowerCase(), updatedUser);
        users.add(updatedUser);

        return true;
    }

    public boolean usernameExists(String username) {
        return users.stream().anyMatch(user -> user.getUsername().equalsIgnoreCase(username));
    }
}