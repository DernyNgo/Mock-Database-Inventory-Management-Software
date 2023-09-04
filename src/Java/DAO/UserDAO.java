package Java.DAO;

import Java.Helper.JDBC;
import static Java.Helper.JDBC.connection;
import Java.Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * @author Daniel Ngo
 * Implementation of the UserDAO Interface.
 */
public class UserDAO implements UserDAOInterface {

    /**
     * Gets a user from the database.
     * @param userID The ID of the user to get
     * @return Returns the user with the given ID
     * @throws SQLException Signals Exception for SQL occurrences
     */
    @Override
    public User getUserID(int userID) throws SQLException {
        String sql = "SELECT * FROM users WHERE User_ID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, userID);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String userName = rs.getString("User_Name");
            String password = rs.getString("Password");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");

            return new User(userID, userName, password, createDate.toLocalDateTime(), createdBy, lastUpdate.toLocalDateTime(), lastUpdatedBy);
        }
        return null;
    }

    /**
     * Gets a user from the database.
     * @param userName The name of the user to get
     * @return Returns the user with the given name
     * @throws SQLException Signals Exception for SQL occurrences
     */
    @Override
    public User getUser(String userName) throws SQLException {
        String sql = "SELECT * FROM users WHERE User_Name = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, userName);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            int userID = rs.getInt("User_ID");
            String password = rs.getString("Password");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");

            return new User(userID, userName, password, createDate.toLocalDateTime(), createdBy, lastUpdate.toLocalDateTime(), lastUpdatedBy);
        }
        return null;
    }

    /**
     * Adds a user to the database.
     * @param user The user to add
     * @throws SQLException Signals Exception for SQL occurrences
     */
    @Override
    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO users (User_Name, Password, Create_Date, Created_By, Last_Update, Last_Updated_By) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, user.getUserName());
        ps.setString(2, user.getPassword());
        ps.setTimestamp(3, Timestamp.valueOf(user.getCreateDate()));
        ps.setString(4, user.getCreatedBy());
        ps.setTimestamp(5, Timestamp.valueOf(user.getLastUpdate()));
        ps.setString(6, user.getLastUpdatedBy());
        ps.executeUpdate();
    }

    /**
     * Updates a user in the database.
     * @param user The user to update
     * @throws SQLException Signals Exception for SQL occurrences
     */
    @Override
    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE users SET User_Name = ?, Password = ?, Last_Update = ?, Last_Updated_By = ? WHERE User_ID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, user.getUserName());
        ps.setString(2, user.getPassword());
        ps.setTimestamp(3, Timestamp.valueOf(user.getLastUpdate()));
        ps.setString(4, user.getLastUpdatedBy());
        ps.setInt(5, user.getUserID());
        ps.executeUpdate();
    }

    /**
     * Deletes a user from the database.
     * @param userID The ID of the user to delete
     * @throws SQLException Signals Exception for SQL occurrences
     */
    @Override
    public void deleteUser(int userID) throws SQLException {
        String sql = "DELETE FROM users WHERE User_ID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, userID);
        ps.executeUpdate();
    }

    /**
     * Gets all users from the database.
     * @return Returns an Observable List of all users
     * @throws SQLException Signals Exception for SQL occurrences
     */
    @Override
    public ObservableList<User> getAllUsers() throws SQLException {
        ObservableList<User> users = FXCollections.observableArrayList();

        String sql = "SELECT * FROM users";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int userID = rs.getInt("User_ID");
            String userName = rs.getString("User_Name");
            String password = rs.getString("Password");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");

            users.add(new User(userID, userName, password, createDate.toLocalDateTime(), createdBy, lastUpdate.toLocalDateTime(), lastUpdatedBy));
        }
        return users;
    }

    /**
     * Gets all usernames from the database.
     * @return Returns an Observable List of all usernames
     * @throws SQLException Signals Exception for SQL occurrences
     */
    @Override
    public ObservableList<String> getAllUserNames() throws SQLException {
        ObservableList<String> userNames = FXCollections.observableArrayList();

        String sql = "SELECT User_Name FROM users";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            userNames.add(rs.getString("User_Name"));
        }
        return userNames;
    }
}
