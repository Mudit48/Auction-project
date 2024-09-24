import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class InsertJDBC {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
            if(con.isClosed())
            {
                System.out.println("Connection is closed");
            }
            else{
                System.out.println("Connection is open");
            }

            //create query
            
            String q = "create table user (id int(5), name varchar(50))";

            Statement stmt = con.createStatement();

            stmt.executeUpdate(q);

            System.out.println("table created in database");

            con.close();

        }

        catch(Exception e){
            e.printStackTrace();

        }
    }
}
