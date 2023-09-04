package Java.Models;

import Java.DAO.ContactDAO;

import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * @author Daniel Ngo
 * Represents the Appointment class.
 */

public class Appointment {
    private int appointmentID;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;
    private int customerID;
    private int userID;
    private int contactID;
    public ContactDAO contactDAO = new ContactDAO();
    /**
     * Constructor for the appointment class.
     * @param appointmentID The Appointment ID
     * @param title The Appointment Title
     * @param description The Appointment Description
     * @param location The Appointment Location
     * @param type The Appointment Type
     * @param start The Appointment Start Time and Date
     * @param end The Appointment End Time and Date
     * @param createDate The Appointment Creation Date
     * @param createdBy The Appointment Created By
     * @param lastUpdate The Appointment Last Update
     * @param lastUpdatedBy The Appointment Last Updated By
     * @param customerID The Appointment Customer ID
     * @param userID The Appointment User ID
     * @param contactID The Appointment Contact ID
     */
    public Appointment(int appointmentID, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy, int customerID, int userID, int contactID) {
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;

    }
    /**
     * Get Appointment ID method.
     * @return Returns the Appointment ID
     */
    public int getAppointmentID() {
        return appointmentID;
    }
    /**
     * Set Appointment ID method.
     * @param appointmentID Appointment ID parameter
     */
    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }
    /**
     * Get Appointment Title method.
     * @return Returns the Appointment title
     */
    public String getTitle() {
        return title;
    }
    /**
     * Set Appointment Title.
     * @param title Title parameter
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * Get Appointment Description method.
     * @return Returns the Appointment description
     */
    public String getDescription() {
        return description;
    }
    /**
     * Set Appointment Description method.
     * @param description Description parameter
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Get Appointment Location method.
     * @return Returns the Appointment Location
     */
    public String getLocation() {
        return location;
    }
    /**
     * Set Appointment Location method.
     * @param location Location parameter
     */
    public void setLocation(String location) {
        this.location = location;
    }
    /**
     * Get Appointment Type method.
     * @return Returns the Appointment Type
     */
    public String getType() {
        return type;
    }
    /**
     * Set Appointment Type method.
     * @param type Type parameter
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     * Get Appointment Start Date and Time method.
     * @return Returns the Appointment Start Date and Time
     */
    public LocalDateTime getStart() {
        return start;
    }
    /**
     * Set Appointment Start Date and Time method.
     * @param start Start Date and Time parameter
     */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }
    /**
     * Get Appointment End Date and Time method.
     * @return Returns the Appointment End Date and Time
     */
    public LocalDateTime getEnd() {
        return end;
    }
    /**
     * Set Appointment End Date and Time method.
     * @param end End Date and Time parameter
     */
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
    /**
     * Get Appointment Creation Date method.
     * @return Returns the Appointment Creation Date
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }
    /**
     * Set Appointment Creation Date.
     * @param createDate Creation Date parameter
     */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
    /**
     * Get Appointment Created By Method.
     * @return Returns the Appointment Created By
     */
    public String getCreatedBy() {
        return createdBy;
    }
    /**
     * Set Appointment Created By.
     * @param createdBy Created By Parameter
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    /**
     * Get Appointment Last Update Method.
     * @return Returns the Appointment Last Update
     */
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }
    /**
     * Set Appointment Last Update.
     * @param lastUpdate Last Update Parameter
     */
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    /**
     * Get Appointment Last Updated By method.
     * @return Returns the Appointment Last Updated By
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }
    /**
     * Set Appointment Last Updated By.
     * @param lastUpdatedBy Last Updated By parameter
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
    /**
     * Get Customer ID method.
     * @return Returns the Customer ID
     */
    public int getCustomerID() {
        return customerID;
    }
    /**
     * Set Customer ID.
     * @param customerID Customer ID parameter
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    /**
     * Get User ID method
     * @return Returns the User ID
     */
    public int getUserID() {
        return userID;
    }
    /**
     * Set Appointment ID.
     * @param userID User ID parameter
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }
    /**
     * Get Contact ID method
     * @return Returns the Contact ID
     */
    public int getContactID() {
        return contactID;
    }
    /**
     * Set Contact ID.
     * @param contactID Contact ID parameter
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }
    /**
     * Converts the Contact ID to Contact Name
     * @return Returns the Contact Name
     */
    public String getContactName() throws SQLException {
        return contactDAO.getContact(contactID).getContactName();
    }
}
