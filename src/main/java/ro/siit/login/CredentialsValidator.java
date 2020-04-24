package ro.siit.login;

import java.sql.*;

public class CredentialsValidator {
    private Connection connection;

    public CredentialsValidator(){
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?user=postgres&password=postgri$$$");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkCredentials(String username, String password){
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM login WHERE username = ? AND pwd = ?");
            ps.setString(1,username);
            ps.setString(2,password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                return true;
            }
            else return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
