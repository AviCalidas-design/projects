package revature.models;
public class Account {
    private static int total = 0;
    private String name;
    private int balance;
    private int id;
    private int userId;
    public Account(String n, int bal,int u)
    {
        total += 1;
        name = n;
        balance = bal;
        id = total;
        userId = u;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return this.balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
    public int getId() {
        return this.id;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", balance='" + getBalance() + "'" +
            ", holder ID='" + getUserId() + "'" +
            "}";
    }
}
