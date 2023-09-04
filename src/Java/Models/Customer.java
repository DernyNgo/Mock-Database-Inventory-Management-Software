package Java.Models;

import Java.DAO.DivisionDAO;

import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * @author Daniel Ngo
 * Represents the Customer class.
 */
public class Customer {
    private int customerID;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;
    private int divisionID;
    private DivisionDAO divisionDAO = new DivisionDAO();
    /**
     * Constructor for the Customer class.
     * @param customerID The Customer ID parameter
     * @param customerName The Customer Name parameter
     * @param address The Address parameter
     * @param postalCode The Postal Code parameter
     * @param phone The Phone parameter
     * @param createDate The Create Date parameter
     * @param createdBy The Created By parameter
     * @param lastUpdate The Last Update parameter
     * @param lastUpdatedBy The Last Updated By parameter
     * @param divisionID The Division ID parameter
     */
    public Customer(int customerID, String customerName, String address, String postalCode, String phone, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy, int divisionID) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionID = divisionID;
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
     * @param customerID The Customer ID parameter
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    /**
     * Get Customer Name method.
     * @return Returns the Customer Name
     */
    public String getCustomerName() {
        return customerName;
    }
    /**
     * Set Customer Name.
     * @param customerName The Customer Name parameter
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    /**
     * Get Address method.
     * @return Returns the Address
     */
    public String getAddress() {
        return address;
    }
    /**
     * Set Address.
     * @param address The Address parameter
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * Get Postal Code method.
     * @return Returns the Postal Code
     */
    public String getPostalCode() {
        return postalCode;
    }
    /**
     * Set the Postal Code.
     * @param postalCode The Postal Code parameter
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    /**
     * Get Phone method.
     * @return Returns the phone
     */
    public String getPhone() {
        return phone;
    }
    /**
     * Set Phone.
     * @param phone The Phone parameter
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    /**
     * Get Creation Date method.
     * @return Returns the Creation Date
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }
    /**
     * Set Creation Date.
     * @param createDate The Creation Date parameter
     */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
    /**
     * Get Created By method.
     * @return Returns the Created By
     */
    public String getCreatedBy() {
        return createdBy;
    }
    /**
     * Set Created By.
     * @param createdBy The Created By parameter
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    /**
     * Get Last Update method.
     * @return Returns the Last Update
     */
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }
    /**
     * Set Last Update.
     * @param lastUpdate The Last Update parameter
     */
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    /**
     * Get Last Updated By method.
     * @return Returns the Last Updated By
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }
    /**
     * Set Last Updated By.
     * @param lastUpdatedBy The Last Updated By parameter
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
    /**
     * Get Division ID method.
     * @return Returns the Division ID
     */
    public int getDivisionID() {
        return divisionID;
    }
    /**
     * Set Division ID.
     * @param divisionID The Division ID parameter
     */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }
    /**
     * Converts the object to a readable string.
     * @return Returns the Customer ID and Customer Name
     */
    public String toString() {
        return customerID + " : " + customerName;
    }
    /**
     * @return Returns the Division Name.
     * @throws SQLException Signals Exception for SQL occurrences
     */
    public String getDivisionName() throws SQLException {
        return divisionDAO.getFirstLevelDivision(divisionID).getDivision();
    }
}
