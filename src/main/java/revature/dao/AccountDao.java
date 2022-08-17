package revature.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import revature.models.*;
import revature.util.*;
public class AccountDao {
    private static List<Account> unapproved = new ArrayList<Account>();
    public void createAccount(Account account) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            
            String sql = "insert into accounts (account_name, balance, account_id, holder_id) values (?, ?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, account.getName());
            ps.setInt(2, account.getBalance());
            ps.setInt(3, account.getId());
            ps.setInt(4, account.getUserId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    public void addAccount(Account acc)
    {
        unapproved.add(acc);
    }
    public void approve(int i)
    {
        Account toApprove = unapproved.get(i);
        unapproved.remove(i);
        createAccount(toApprove);
    }
    public void printUnapproved()
    {
        System.out.println("Unapproved Accounts:");
        for(int i = 0; i < unapproved.size() ; i++)
        {
            System.out.println((i+1) + ". " + unapproved.get(i));
        }
    }
    public List<Account> getAccounts(Integer userId)
    {
        List<Account> accounts = new ArrayList<>();

        try (Connection conn = ConnectionUtil.getConnection()) {
            
            String sql = "select * from accounts where holder_id = ? order by account_id;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, userId);


            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                accounts.add(new Account(rs.getString(1),rs.getInt(2),rs.getInt(3)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounts;
    }
    /*
    private int findBalance(Integer accountId)
    {
        try (Connection conn = ConnectionUtil.getConnection()) {
            
            String sql = "select * from accounts where account_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, accountId);


            ResultSet rs = ps.executeQuery();

            rs.next();
            return rs.getInt(2);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    */
    public void deleteAccount(Integer accountId) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            
            String sql = "delete from accounts where account_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, accountId);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateBalance(Integer accountId, int amount) throws Exception{
        if(amount < 0)
        {
            throw new IndexOutOfBoundsException();
        }
        try (Connection conn = ConnectionUtil.getConnection()) {
            
            String sql = "update accounts set balance = ? where account_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1,amount);
            ps.setInt(2, accountId);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
