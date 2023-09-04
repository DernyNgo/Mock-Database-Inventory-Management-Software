package Java.DAO;

import Java.Models.User;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface UserDAOInterface {
    public User getUserID(int userID) throws SQLException;
    public User getUser(String userName) throws SQLException;
    public void addUser(User user) throws SQLException;
    public void updateUser(User user) throws SQLException;
    public void deleteUser(int userID) throws SQLException;
    public ObservableList<User> getAllUsers() throws SQLException;
    public ObservableList<String> getAllUserNames() throws SQLException;
}
