package Java.DAO;

import Java.Helper.JDBC;
import Java.Models.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Java.Helper.JDBC.connection;

/**
 * @author Daniel Ngo
 * Implements the Contact DAO Interface.
 */
public class ContactDAO implements ContactDAOInterface {

    /**
     * Gets a contact from the database.
     * @param contactID The ID of the contact to get
     * @return Returns the contact
     * @throws SQLException Signals Exception for SQL occurrences
     */
    @Override
    public Contact getContact(int contactID) throws SQLException {
        String sql = "SELECT * FROM contacts WHERE Contact_ID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, contactID);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String contactName = rs.getString("Contact_Name");
            String email = rs.getString("Email");

            return new Contact(contactID, contactName, email);
        }
        return null;
    }

    /**
     * Adds a contact to the database.
     * @param contact The contact to add
     * @throws SQLException Signals Exception for SQL occurrences
     */
    @Override
    public void addContact(Contact contact) throws SQLException {
        String sql = "INSERT INTO contacts (Contact_Name, Email) VALUES (?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, contact.getContactName());
        ps.setString(2, contact.getEmail());
        ps.executeUpdate();
    }

    /**
     * Updates a contact in the database.
     * @param contact The contact to update
     * @throws SQLException Signals Exception for SQL occurrences
     */
    @Override
    public void updateContact(Contact contact) throws SQLException {
        String sql = "UPDATE contacts SET Contact_Name = ?, Email = ? WHERE Contact_ID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, contact.getContactName());
        ps.setString(2, contact.getEmail());
        ps.setInt(3, contact.getContactID());
        ps.executeUpdate();
    }

    /**
     * Deletes a contact from the database.
     * @param contactID The ID of the contact to delete.
     * @throws SQLException Signals Exception for SQL occurrences
     */
    @Override
    public void deleteContact(int contactID) throws SQLException {
        String sql = "DELETE FROM contacts WHERE Contact_ID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, contactID);
        ps.executeUpdate();
    }

    /**
     * Gets all contacts from the database.
     * @return Returns an Observable List of all contacts.
     * @throws SQLException Signals Exception for SQL occurrences
     */
    @Override
    public ObservableList<Contact> getAllContacts() throws SQLException {
        ObservableList<Contact> contacts = FXCollections.observableArrayList();

        String sql = "SELECT * FROM contacts";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int contactID = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            String email = rs.getString("Email");

            contacts.add(new Contact(contactID, contactName, email));
        }
        return contacts;
    }
}
