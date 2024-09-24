import java.sql.*;
class Firsttest{
    public static void main(String[] args) {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
            con.close();
            if(con.isClosed())
            {
                System.out.println("Connection is closed");
            }
            else{
                System.out.println("Connection is open");
            }

            

        }

        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}