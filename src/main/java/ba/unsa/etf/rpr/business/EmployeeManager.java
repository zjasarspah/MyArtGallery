package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.exceptions.ArtGalleryException;
import java.util.List;

/**
 * Business Logic Layer for Employee
 * @author Zerina Jasarspahic
 */
public class EmployeeManager {

    /**
     * Method that returns every employee from a table in a databse
     * @return list of employees
     * @throws ArtGalleryException
     */
    public List<Employee> getAll() throws ArtGalleryException {
        return DaoFactory.employeeDao().getAll();
    }

    /**
     * Method for checking does employee exist in the database
     * @param employee that we search
     * @throws ArtGalleryException if the information about the employee are not correct
     */
    public void validateEmployee (Employee employee) throws ArtGalleryException {
        for (Employee e : getAll()) {
            if (e.equals(employee)) {
                employee.setId(e.getId());
                return;
            }
        }

        throw new ArtGalleryException("The username or the password isn't correct.");
    }

    /**
     * Method for changing password of an employee
     * @param employee that exist in a database
     * @param newPassword new password of the employee
     * @param verifyPassword password that should be the same as the newPassword
     * @throws ArtGalleryException if verifyPassword and newPassword aren't same, or the employee doesn't exist in the database
     */
    public void verifyPassword (Employee employee, String newPassword, String verifyPassword) throws ArtGalleryException {
        validateEmployee(employee);
        if (newPassword.equals(verifyPassword)) {
            employee.setPassword(newPassword);
        } else throw new ArtGalleryException("The new password and the verified password aren't same.");

        DaoFactory.employeeDao().update(employee);
    }
}