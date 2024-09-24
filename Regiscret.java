import java.sql.*;
import java.io.BufferedReader;

public class Regiscret {
    
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

            String q1 = "create table regis(id int(20), name varchar(50), email varchar(200), age int(20))";
            Statement stmt = con.createStatement();

            stmt.executeUpdate(q1);

            String q =  "INSERT INTO student(id,name,age) VALUES (?,?,?)";

            PreparedStatement pstmt = con.prepareStatement(q);

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("id");
            String idStr = br.readLine();
            int id = Integer.parseInt(idStr);

            System.out.println("name");
            String name = br.readLine();

            System.out.println("age");
            String ageStr = br.readLine();
            int age = Integer.parseInt(ageStr);



            pstmt.setInt(1,id);
            pstmt.setString(2,name);
            pstmt.setInt(3,age);

        
            pstmt.executeUpdate();


        }

        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


}
