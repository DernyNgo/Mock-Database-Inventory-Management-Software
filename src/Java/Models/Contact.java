package Java.Models;

/**
 * @author Daniel Ngo
 * Represents the Contact class.
 */
public class Contact {
    private int contactID;
    private String contactName;
    private String email;
    /**
     * Constructor for the Contact class.
     * @param contactID The Contact ID parameter
     * @param contactName The Contact Name parameter
     * @param email The Contact email parameter
     */
    public Contact(int contactID, String contactName, String email) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.email = email;
    }
    /**
     * Get Contact ID method.
     * @return Returns the Contact ID
     */
    public int getContactID() {
        return contactID;
    }
    /**
     * Set Contact ID method.
     * @param contactID Contact ID parameter
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }
    /**
     * Get Contact Name method.
     * @return Returns the Contact Name
     */
    public String getContactName() {
        return contactName;
    }
    /**
     * Set Contact Name method.
     * @param contactName Contact Name parameter
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    /**
     * Get Email method.
     * @return Returns the Email
     */
    public String getEmail() {
        return email;
    }
    /**
     * Set Email method.
     * @param email The email parameter
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Converts the object to a readable string.
     * @return Returns the Contact Name
     */
    public String toString() {
        return contactName;
    }
}
