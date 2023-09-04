package Java.DAO;

import Java.Models.Country;
import javafx.collections.ObservableList;
import java.sql.SQLException;

public interface CountryDAOInterface {
    public Country getCountry(int countryID) throws SQLException;
    public void addCountry(Country country) throws SQLException;
    public void updateCountry(Country country) throws SQLException;
    public void deleteCountry(int countryID) throws SQLException;
    public ObservableList<Country> getAllCountries() throws SQLException;
}
