//package revature;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
//import models.Account;
//import util.*;
public class AccountDao {
    private static List<Account> unapproved = new ArrayList<Account>();
    private static List<Account> approved = new ArrayList<Account>();
    private static String log = new String();
    private static FileWriter dataWriter;
    public void createAccount(Account account) {
        approved.add(account);
            /*
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
        */
    }
    public static void initialize()
    {
        try
        {
        Scanner dataCollect = new Scanner(new File("AccountData.txt"));
        int i = 0;
        while(dataCollect.hasNextLine())
        {
            String data = dataCollect.nextLine();
            String[] values = data.split(",");
            Account addition = new Account(values[0],Integer.parseInt(values[1]),Integer.parseInt(values[3]));
            addition.setId(Integer.parseInt(values[2]));
            i++;
            approved.add(addition);
        }
        Account.setTotal(i);
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public static void close() throws Exception
    {
        dataWriter = new FileWriter("AccountData.txt");
        for(Account account : approved)
        {
            dataWriter.write(account.getName() + "," + account.getBalance() + "," + account.getId() + "," + account.getUserId() + "\n");
        }
        dataWriter.close();
    }
    public void updateLog(String addition)
    {
        log += addition + "\n";
    }
    public void printLog()
    {
        System.out.println(log);
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
        /*
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
        */
        List<Account> endList = new ArrayList<Account>();
        for(int i = 0; i < approved.size() ; i++)
        {
            if(approved.get(i).getUserId() == userId)
            {
                endList.add(approved.get(i));
            }
        }
        return endList;
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
        /*
        try (Connection conn = ConnectionUtil.getConnection()) {
            
            String sql = "delete from accounts where account_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, accountId);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        */
        for(int i = 0; i < approved.size(); i++)
        {
            if(approved.get(i).getId() == accountId)
            {
                approved.remove(i);
            }
        }
    }
    public void updateBalance(Integer accountId, int amount) throws Exception{
        if(amount < 0)
        {
            throw new IndexOutOfBoundsException();
        }
        /*
        try (Connection conn = ConnectionUtil.getConnection()) {
            
            String sql = "update accounts set balance = ? where account_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1,amount);
            ps.setInt(2, accountId);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        */
        for(int i = 0; i < approved.size() ; i++)
        {
            if(approved.get(i).getId() == accountId)
            {
                approved.get(i).setBalance(amount);
            }
        }
    }
}
