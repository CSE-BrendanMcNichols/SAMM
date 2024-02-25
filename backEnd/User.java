import java.util.UUID;
/**
 * user class
 * @author Matthew Bojanowski
 */
public class User {
    private String username;
    private String password;
    private String email;
    private String uscid;
    private UUID uuid;
    public User(String username, String password, String email, String uscid, UUID uuid) {
        username = this.username;
        password = this.password;
        email = this.email;
        uscid = this.uscid;
        uuid = this.uuid;
    }
    public void login(){
        System.out.println("login called.");
    }
    protected void signOutOfOtherSessions(){
        System.out.println("sign out successful.");
    }
    private getPassword(){
        return password;
    }
    private String getUser(){
        return username;
    }
    protected String getRole(){
        return "stub output";
    }
    private void rememberLogin(Boolean remember){
        if(remember == true){
            System.out.println("Will remember login.");
        }else{
            System.out.println("Will not  remember login.");
        }
    }

}
