package Java.DAO;

import Java.Models.FirstLevelDivision;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface DivisionDAOInterface {
    public FirstLevelDivision getFirstLevelDivision(int divisionID) throws SQLException;
    public void addFirstLevelDivision(FirstLevelDivision division) throws SQLException;
    public void updateFirstLevelDivision(FirstLevelDivision division) throws SQLException;
    public void deleteFirstLevelDivision(int divisionID) throws SQLException;
    public ObservableList<FirstLevelDivision> getAllDivisions() throws SQLException;
    public ObservableList<FirstLevelDivision> getDivisionsByCountryID(int countryID) throws SQLException;
}

