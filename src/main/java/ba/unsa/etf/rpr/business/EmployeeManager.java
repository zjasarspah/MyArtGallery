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

    public List<Employee> getAll() throws ArtGalleryException {
        return DaoFactory.employeeDao().getAll();
    }
    public void isValid (Employee employee) throws ArtGalleryException {
        for (Employee e : getAll()) {
            if (e.equals(employee)) {
                employee.setId(e.getId());
                return;
            }
        }

        throw new ArtGalleryException("The username or the password isn't correct.");
    }
    public void update (Employee employee, String newPassword, String verifyPassword) throws ArtGalleryException {
        isValid(employee);
        if (newPassword.equals(verifyPassword)) {
            employee.setPassword(newPassword);
        } else throw new ArtGalleryException("The new password and verified password aren't same.");

        DaoFactory.employeeDao().update(employee);
    }

}