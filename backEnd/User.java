package backEnd;

import java.util.UUID;
import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private String email;
    private String uscid;
    private UUID uuid = UUID.randomUUID();
    private ArrayList<User> users;
    private User user;

    public User(String username, String password, String email, String uscid) {
        username = this.username;
        password = this.password;
        email = this.email;
        uscid = this.uscid;
    }

    public User(String username, String password, String email, String uscid, UUID uuid) {
        username = this.username;
        password = this.password;
        email = this.email;
        uscid = this.uscid;
        uuid = this.uuid;
    }

    public void login() {
        System.out.println("login called.");
    }

    private String searchUser(String name) { 
        for(int i = 0; i < users.size(); i++) {
            if(users.get(i).equals(name)) {
                return name;
            }
        }
        return null;
    }
    

    protected void signOutOfOtherSessions() {
        System.out.println("sign out successful.");
    }

    public boolean isValidUser(User user) {
        boolean valid = false;
        for(int i = 0; i < users.size(); i++) {
            if(user.equals(users.get(i))) {
                valid = true;
            }
        }
        return valid;
    }

    // Note - generated getters and setters for all the private variables

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUscid() {
        return uscid;
    }

    public void setUscid(String uscid) {
        this.uscid = uscid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    protected String getRole() {
        return "stub output";
    }

    private void rememberLogin(Boolean remember) {
        if (remember == true) {
            System.out.println("Will remember login.");
        } else {
            System.out.println("Will not  remember login.");
        }
    }

}
