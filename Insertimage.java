import java.io.*;
import java.sql.*;


public class Insertimage {
    public static void main(String[] args) {
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
        
        if(con.isClosed())
        {
            System.out.println("Connection is closed");
        }
        else{
            System.out.println("Connection is open");
        }

        String q = "insert into images(pic) values(?)";

        PreparedStatement pstmt=con.prepareStatement(q);

        //pstmt.setString(1,"Value");

        FileInputStream fis = new FileInputStream("pix2.png");

        pstmt.setBinaryStream(1,fis,fis.available());

        pstmt.executeUpdate();

        System.out.println("done");
    }

    catch(Exception e)
    {
        e.printStackTrace();
    }
    
}
}
