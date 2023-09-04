package Java.DAO;


import Java.Models.Appointment;
import javafx.collections.ObservableList;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public interface AppointmentDAOInterface {
    public Appointment getAppointment(int appointmentID) throws SQLException;
    public void addAppointment(Appointment appointment) throws SQLException;
    public void updateAppointment(Appointment appointment) throws SQLException;
    public void deleteAppointment(int appointmentID) throws SQLException;
    public void deleteAppointmentsByCustomerID(int customerID) throws SQLException;
    public ObservableList<Appointment> getWeeklyAppointments(LocalDate date) throws SQLException;
    public ObservableList<Appointment> getMonthlyAppointments(LocalDate date) throws SQLException;
    public ObservableList<Appointment> getAllAppointments() throws SQLException;
    public ObservableList<Appointment> getOverlappedAppointmentsWithoutSelf(LocalDateTime start, LocalDateTime end, int ID, int customerId) throws SQLException;
    public ObservableList<Appointment> getOverlappedAppointments(LocalDateTime start, LocalDateTime end, int customerId) throws SQLException;
    public ObservableList<Appointment> get15MinuteAppointments(LocalDateTime loginTime) throws SQLException;
    public ObservableList<Appointment> getAllAppointmentsByDate() throws SQLException;
    public ObservableList<Appointment> getAllAppointmentsMonthly(int month) throws SQLException;
    public ObservableList<Appointment> getAllAppointmentContactIDs(int contactID) throws SQLException;
    public ObservableList<Appointment> getAllAppointmentsByType(String type) throws SQLException;
    public ObservableList<String> getDistinctAppointmentTypes() throws SQLException;
}
