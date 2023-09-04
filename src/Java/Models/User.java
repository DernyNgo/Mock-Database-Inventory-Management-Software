package Java.Models;

import java.time.LocalDateTime;

/**
 * @author Daniel Ngo
 * Represents the User class.
 */
public class User {
    private int userID;
    private String userName;
    private String password;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;
    /**
     * Constructor for the Division Class.
     * @param userID The User ID parameter
     * @param userName The Username parameter
     * @param password The Password parameter
     * @param createDate The Creation Date parameter
     * @param createdBy The Created By parameter
     * @param lastUpdate The Last Update parameter
     * @param lastUpdatedBy The Last Updated By parameter
     */
    public User(int userID, String userName, String password, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }
    /**
     * Get User ID method.
     * @return Returns the User ID
     */
    public int getUserID() {
        return userID;
    }
    /**
     * Set User ID.
     * @param userID The User ID parameter
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }
    /**
     * Get Username method.
     * @return Returns the Username
     */
    public String getUserName() {
        return userName;
    }
    /**
     * Set Username.
     * @param userName The Username parameter
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**
     * Get Password method.
     * @return Returns the Password
     */
    public String getPassword() {
        return password;
    }
    /**
     * Set Password.
     * @param password The password parameter
     */
    public void setPassword(String password) {
        this.password = password;
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
     * Get Last Updated By Method.
     * @return Returns Last Updated By
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
     * Converts the object to a readable string.
     * @return Returns the User ID and Username
     */
    public String toString() {
        return userID + " : " + userName;
    }
}
