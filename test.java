import java.sql.*;
class Test{
    public static void main(String[] args) {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");

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