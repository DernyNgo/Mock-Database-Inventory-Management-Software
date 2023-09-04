package Java.Models;

import java.time.LocalDateTime;

/**
 * @author Daniel Ngo
 * Represents the Division class.
 */
public class FirstLevelDivision {
    private int divisionID;
    private String division;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;
    private int countryID;
    /**
     * Constructor for the Division Class.
     * @param divisionID The Division ID parameter
     * @param division The Division parameter
     * @param createDate The Creation Date parameter
     * @param createdBy The Created By parameter
     * @param lastUpdate The Last Update parameter
     * @param lastUpdatedBy The Last Updated By parameter
     * @param countryID The Country ID parameter
     */
    public FirstLevelDivision(int divisionID, String division, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy, int countryID) {
        this.divisionID = divisionID;
        this.division = division;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.countryID = countryID;
    }
    /**
     * Get Division ID method.
     * @return Returns the Division ID
     */
    public int getDivisionID() {
        return divisionID;
    }
    /**
     * Set Customer ID.
     * @param divisionID The Division ID parameter
     */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }
    /**
     * Get Division method.
     * @return Returns the Division
     */
    public String getDivision() {
        return division;
    }
    /**
     * Set Division.
     * @param division The Division parameter
     */
    public void setDivision(String division) {
        this.division = division;
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
     * @param lastUpdate Returns the Last Update
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
     * Get Country ID method.
     * @return Returns the Country ID
     */
    public int getCountryID() {
        return countryID;
    }
    /**
     * Set Country ID.
     * @param countryID The Country ID parameter
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    /**
     * Converts the object to a readable string.
     * @return Returns the Division
     */
    public String toString() {
        return division;
    }
}
