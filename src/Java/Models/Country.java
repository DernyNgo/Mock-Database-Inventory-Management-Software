package Java.Models;

import java.time.LocalDateTime;

/**
 * @author Daniel Ngo
 * Represents the Country class.
 */
public class Country {
    private int countryID;
    private String country;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;
    /**
     * Constructor for the Country class.
     * @param countryID The Country ID parameter
     * @param country The Country parameter
     * @param createDate The Creation Date parameter
     * @param createdBy The Created By parameter
     * @param lastUpdate The Last Update parameter
     * @param lastUpdatedBy The Last Updated By parameter
     */
    public Country(int countryID, String country, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy) {
        this.countryID = countryID;
        this.country = country;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
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
     * @param countryID Country ID parameter
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }
    /**
     * Get Country method.
     * @return Returns the Country
     */
    public String getCountry() {
        return country;
    }
    /**
     * Set Country.
     * @param country Country parameter
     */
    public void setCountry(String country) {
        this.country = country;
    }
    /**
     * Get Creation Date method.
     * @return Returns the Country Creation Date
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }
    /**
     * Set Creation Date.
     * @param createDate Creation Date parameter
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
     * @param createdBy Created By parameter
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
     * Converts the object to a readable string.
     * @return Returns Country
     */
    public String toString() {
        return country;
    }
}
