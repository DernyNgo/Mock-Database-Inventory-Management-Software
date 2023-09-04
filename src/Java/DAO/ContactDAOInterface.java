package Java.DAO;

import Java.Models.Contact;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface ContactDAOInterface {
    Contact getContact(int contactID) throws SQLException;
    void addContact(Contact contact) throws SQLException;
    void updateContact(Contact contact) throws SQLException;
    void deleteContact(int contactID) throws SQLException;
    ObservableList<Contact> getAllContacts() throws SQLException;
}
