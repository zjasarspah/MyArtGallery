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
                return;
            }
        }

        throw new ArtGalleryException("The username or the password isn't correct.");
    }

}