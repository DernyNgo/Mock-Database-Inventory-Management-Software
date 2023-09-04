package Java.DAO;

import Java.Helper.JDBC;
import Java.Models.FirstLevelDivision;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import static Java.Helper.JDBC.connection;

/**
 * @author Daniel Ngo
 * Implementation of the DivisionDAO Interface.
 */
public class DivisionDAO implements DivisionDAOInterface {

    /**
     * Gets a division from the database.
     * @param divisionID The ID of the division to get
     * @return Returns the division with the specific ID
     * @throws SQLException Signals Exception for SQL occurrences
     */
    @Override
    public FirstLevelDivision getFirstLevelDivision(int divisionID) throws SQLException {
        String sql = "SELECT * FROM first_level_divisions WHERE Division_ID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, divisionID);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String division = rs.getString("Division");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int countryID = rs.getInt("Country_ID");

            return new FirstLevelDivision(divisionID, division, createDate.toLocalDateTime(), createdBy,
                    lastUpdate.toLocalDateTime(), lastUpdatedBy, countryID);
        }
        return null;
    }

    /**
     * Adds a division to the database.
     * @param division The division to add
     * @throws SQLException Signals Exception for SQL occurrences
     */
    @Override
    public void addFirstLevelDivision(FirstLevelDivision division) throws SQLException {
        String sql = "INSERT INTO first_level_divisions (Division, Create_Date, Created_By, Last_Update, Last_Updated_By, Country_ID) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, division.getDivision());
        ps.setTimestamp(2, Timestamp.valueOf(division.getCreateDate()));
        ps.setString(3, division.getCreatedBy());
        ps.setTimestamp(4, Timestamp.valueOf(division.getLastUpdate()));
        ps.setString(5, division.getLastUpdatedBy());
        ps.setInt(6, division.getCountryID());
        ps.executeUpdate();
    }

    /**
     * Updates a division in the database.
     * @param division The division to update
     * @throws SQLException Signals Exception for SQL occurrences
     */
    @Override
    public void updateFirstLevelDivision(FirstLevelDivision division) throws SQLException {
        String sql = "UPDATE first_level_divisions SET Division = ?, Last_Update = ?, Last_Updated_By = ?, Country_ID = ?, WHERE Division_ID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, division.getDivision());
        ps.setTimestamp(2, Timestamp.valueOf(division.getLastUpdate()));
        ps.setString(3, division.getLastUpdatedBy());
        ps.setInt(4, division.getCountryID());
        ps.setInt(5, division.getDivisionID());
        ps.executeUpdate();
    }

    /**
     * Deletes a division from the database.
     * @param divisionID The ID of the division to delete
     * @throws SQLException Signals Exception for SQL occurrences
     */
    @Override
    public void deleteFirstLevelDivision(int divisionID) throws SQLException {
        String sql = "DELETE FROM first_level_divisions WHERE Division_ID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, divisionID);
        ps.executeUpdate();
    }

    /**
     * Gets all divisions from the database.
     * @return Returns an Observable List of all divisions
     * @throws SQLException Signals Exception for SQL occurrences
     */
    @Override
    public ObservableList<FirstLevelDivision> getAllDivisions() throws SQLException {
        ObservableList<FirstLevelDivision> divisions = FXCollections.observableArrayList();

        String sql = "SELECT * FROM first_level_divisions";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int divisionID = rs.getInt("Division_ID");
            String division = rs.getString("Division");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int countryID = rs.getInt("Country_ID");

            divisions.add(new FirstLevelDivision(divisionID, division, createDate.toLocalDateTime(), createdBy,
                    lastUpdate.toLocalDateTime(), lastUpdatedBy, countryID));
        }
        return divisions;
    }

    /**
     * Gets all divisions from a specific country.
     * @param countryID The ID of the country to get divisions from
     * @return Returns an Observable List of all divisions from the specific country
     * @throws SQLException Signals Exception for SQL occurrences
     */
    @Override
    public ObservableList<FirstLevelDivision> getDivisionsByCountryID(int countryID) throws SQLException {
        ObservableList<FirstLevelDivision> divisions = FXCollections.observableArrayList();

        String sql = "SELECT * FROM first_level_divisions WHERE Country_ID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, countryID);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int divisionID = rs.getInt("Division_ID");
            String division = rs.getString("Division");
            Timestamp createDate = rs.getTimestamp("Create_Date");
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");

            divisions.add(new FirstLevelDivision(divisionID, division, createDate.toLocalDateTime(), createdBy,
                    lastUpdate.toLocalDateTime(), lastUpdatedBy, countryID));
        }
        return divisions;
    }
}
