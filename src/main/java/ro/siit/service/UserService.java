package ro.siit.service;
import ro.siit.model.User;

import java.sql.*;
import java.util.UUID;

public class UserService {
    private Connection connection;

    public UserService(){
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?user=postgres&password=postgri$$$");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean registerUser(User user){
        try{
            PreparedStatement ps = connection.prepareStatement("INSERT INTO login (id, username, pwd) VALUES (?, ?, ?)");
            ps.setObject(1,user.getId());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            if (ps.executeUpdate() == 1){
                return true;
            }
            else return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean usernameExists(String username){
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM login WHERE username = ?");
            ps.setString(1,username);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


    public User checkCredentials(String username, String password){
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM login WHERE username = ? AND pwd = ?");
            ps.setString(1,username);
            ps.setString(2,password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                return new User(UUID.fromString(rs.getObject(1).toString()),rs.getString(2),rs.getString(3));
            }
            else return null;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
