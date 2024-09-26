import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Auctest {

    public static void register(Connection con){
        String name,  email, password, city, state;
        int ph_no;

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name");
        name = sc.nextLine();
        System.out.println("Enter your email");
        email = sc.nextLine();
        System.out.println("Enter your password");
        password = sc.nextLine();
        System.out.println("Enter your state");
        state = sc.nextLine();
        System.out.println("Enter your city");
        city = sc.nextLine();
        System.out.println("Enter your phone number");
        ph_no = sc.nextInt();
        
        String regisQ = "insert into auctest(name, email, city, state, password, ph_no) values(?,?,?,?,?,?)";
        try(PreparedStatement pstmt = con.prepareStatement(regisQ)){
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3,city);
            pstmt.setString(4, state);
            pstmt.setString(5,  password);
            pstmt.setInt(6, ph_no);
            
            pstmt.executeUpdate();
            login(con);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
    }

    public static void login(Connection con) {
        
        Scanner sc = new Scanner(System.in);
        String lQ = "select * from  auctest where email = ? and password = ?";
        
        System.out.println("Enter your email");
        String email = sc.nextLine();
        System.out.println("Enter your password");
        String password = sc.nextLine();

        try(PreparedStatement pstmt = con.prepareStatement(lQ)){
            pstmt.setString(1, email);
            pstmt.setString(2,  password);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if(rs.next()){
                    int user_id = rs.getInt("user_id");
                    System.out.println("Login Successfull");
                    buyorsell(con,user_id);
                }
                else{
                    System.out.println("Invalid Email or Password");
                }
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void users(Connection con){
        Scanner sc = new Scanner(System.in);
        String rsQ = "select * from  auctest";
        try (PreparedStatement pstmt = con.prepareStatement(rsQ)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    
                    int user_id = rs.getInt("user_id");
                    String name = rs.getString("name");
                    String email = rs.getString("city");
                    String city = rs.getString("city");
                    String state = rs.getString("state");
                    String password = rs.getString("password");
                    int ph_no = rs.getInt("ph_no");

                    System.out.println("------------------------------------------------");
                    System.out.println("id = "+user_id);
                    System.out.println("name = "+name);
                    System.out.println("email = "+email);
                    System.out.println("city = "+city);
                    System.out.println("state = "+state);
                    System.out.println("password = "+password);
                    System.out.println("ph_no = "+ph_no);

                }
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static void buyorsell(Connection con, int user_id) {
        Scanner sc =  new Scanner(System.in);

        System.out.println("Choose if you want to\n1. Bid\n2. Sell");
        int choice = sc.nextInt();
        if(choice==2){
            item(con, user_id);
        }
        else {
            actbid(con, sc);
        }

    }

    public static void item(Connection con, int user_id) {
        Scanner sc = new Scanner(System.in);
    
        String name, description;
        int start_price, min_incr, payout_price;
    
        System.out.println("Enter item name");
        name = sc.nextLine();
        System.out.println("Enter item description");
        description = sc.nextLine();
        System.out.println("Enter start price");
        start_price = sc.nextInt();
        System.out.println("Enter minimum increment");
        min_incr = sc.nextInt();
        System.out.println("Enter payout price");
        payout_price = sc.nextInt();
        int seller_id = user_id;
        System.out.println("Enter end date (dd/mm/yyyy): ");
        String endDateStr = sc.next();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate endDateLd = LocalDate.parse(endDateStr, formatter);
        Timestamp endDateTs = Timestamp.valueOf(endDateLd.atStartOfDay());
    
        String itemQ = "insert into items(name, description, start_price, min_incr, payout_price, end_date, seller_id) values(?,?,?,?,?,?,?)";
        try(PreparedStatement pstmt = con.prepareStatement(itemQ)){
            pstmt.setString(1, name);
            pstmt.setString(2, description);
    
            //FileInputStream fis = new FileInputStream("pix1.jpg"); // Replace "image.png" with the path to your image file
            //pstmt.setBinaryStream(3, fis, fis.available());
    
            pstmt.setInt(3, start_price);
            pstmt.setInt(4, min_incr);
            pstmt.setInt(5, payout_price);
            pstmt.setTimestamp(6, endDateTs);
            pstmt.setInt(7, seller_id);
    
            pstmt.executeUpdate();
            seller(con,seller_id);
    
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void seller(Connection con, int seller_id) {
        String sellerQ = "insert into seller(seller_id, seller_fname, email, city, state, ph_no) " +
                     "select ?, name, email, city, state, ph_no from auctest where user_id = ?";

    try (PreparedStatement pstmt = con.prepareStatement(sellerQ)) {
        pstmt.setInt(1, seller_id);
        pstmt.setInt(2, seller_id);

        pstmt.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

    public static void actbid(Connection con, Scanner sc) {
        String query = "SELECT * FROM items WHERE DATE(end_date) > CURDATE()";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                System.out.println("Active Bids:");
                while (rs.next()) {
                    int item_id = rs.getInt("item_id");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    Timestamp end_date = rs.getTimestamp("end_date");
    
                    System.out.println("Item ID: " + item_id);
                    System.out.println("Name: " + name);
                    System.out.println("Description: " + description);
                    System.out.println("End Date: " + end_date);
                    System.out.println("------------------------");
                }
                System.out.println("would you like to buy?\n1. Yes\n2. No");
                int choice = sc.nextInt();
                if (choice == 1) {
                    System.out.println("Enter the item id you want to buy");
                    int item_id = sc.nextInt();
                    iteminfo(con,item_id);
                }
                else{
                    System.out.println("Exiting the program");
                    System.exit(0);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void iteminfo(Connection con, int item_id) {
        String query = "SELECT * FROM items WHERE item_id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, item_id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("item_id");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    int start_price = rs.getInt("start_price");
                    int min_incr = rs.getInt("min_incr");
                    int payout_price = rs.getInt("payout_price");
                    Timestamp end_date = rs.getTimestamp("end_date");
                    int seller_id = rs.getInt("seller_id");
    
                    System.out.println("Item ID: " + id);
                    System.out.println("Name: " + name);
                    System.out.println("Description: " + description);
                    System.out.println("Start Price: " + start_price);
                    System.out.println("Minimum Increment: " + min_incr);
                    System.out.println("Payout Price: " + payout_price);
                    System.out.println("End Date: " + end_date);
                    System.out.println("Seller ID: " + seller_id);
                } else {
                    System.out.println("Item not found.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            
            Scanner sc = new Scanner(System.in);
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
            
            if(con.isClosed())
            {
                System.out.println("Connection is closed");
            }
            else{
                System.out.println("Connection is open");
            }

            System.out.println("what do you want to do?");
            System.out.println("1. Register\n2. Login\n3.  Users(adminonly)");

            int u_i = sc.nextInt();
            
            switch (u_i) {
                case 1:
                    register(con);
                    break;
                case 2:
                    login(con);
                    break;
                case 3:
                    users(con);
                    break;
                default:
                    break;
            }
            
            con.close();
        }

        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
