package revature.models;


public class User {
    private static int currentNum = 0;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private int id;
    private String type;
    public User(String f, String l, String u, String p, String t)
    {
        currentNum += 1;
        firstName = f;
        lastName = l;
        username = u;
        password = p;
        id = currentNum;
        type = t;
    }
    public User(String u, String p)
    {
        username = u;
        password = p;
        id = -1;
    }
    public static void updateTotal(int x)
    {
        currentNum = x;
    }
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return this.id;
    }

    public String getType()
    {
        return type;
    }
    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", firstname='" + getFirstName() + "'" +
            ", lastname='" + getLastName() + "'" +
            "}";
    }
}
