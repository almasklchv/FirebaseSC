package classes;

public class Person {
    private String name;
    private String iin;
    private String username;
    private String password;
    private String key;

    // constructor
    public Person() {

    }
    public Person(String name, String iin, String username, String password) {
        this.name = name;
        this.iin = iin;
        this.username = username;
        this.password = password;
    }
    

    // getter
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getKey() {
        return key;
    }

    // setter
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setKey(String key) {
        this.key = key;
    }
}
