package backEnd;

import java.util.UUID;

public abstract class User {
    private UserType type;
    private UUID uuid;
    private String uscid;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;

    /**
     * Constructor for User to be created with provided user details.
     * This constructor generates a new unique UUID for the user.
     *
     * @param firstName User's first name.
     * @param lastName User's last name.
     * @param uscid User's University ID.
     * @param email User's email address.
     * @param username User's username.
     * @param password User's password.
     * @param type User's type (STUDENT, ADVISOR, etc.).
     */
    public User(String firstName, String lastName, String uscid, String email, String username, String password, UserType type) {
        this.uuid = UUID.randomUUID(); // Automatically generates a unique identifier for a new user
        this.firstName = firstName;
        this.lastName = lastName;
        this.uscid = uscid;
        this.email = email;
        this.username = username;
        this.password = password;
        this.type = type;
    }

    /**
     * Constructor for User importing data from the database.
     * This constructor is used when user data is being loaded from an external source
     * where the UUID is already defined.
     *
     * @param uuid User's UUID.
     * @param firstName User's first name.
     * @param lastName User's last name.
     * @param uscid User's University ID.
     * @param email User's email address.
     * @param username User's username.
     * @param password User's password.
     * @param type User's type (STUDENT, ADVISOR, etc.).
     */
    public User(UUID uuid, String firstName, String lastName, String uscid, String email, String username, String password, UserType type) {
        this.uuid = uuid; // Uses the provided UUID, for existing users loaded from a database
        this.firstName = firstName;
        this.lastName = lastName;
        this.uscid = uscid;
        this.email = email;
        this.username = username;
        this.password = password;
        this.type = type;
    }

     // Getter methods
     public UserType getType() {
        return type;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getUscid() {
        return uscid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // Setter methods
    public void setType(UserType type) {
        this.type = type;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setUscid(String uscid) {
        this.uscid = uscid;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}