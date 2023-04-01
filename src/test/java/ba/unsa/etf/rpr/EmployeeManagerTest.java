package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.EmployeeManager;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.exceptions.ArtGalleryException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * Test class for Employee
 * @author Zerina Jasarspahic
 *
 */

class EmployeeManagerTest {

    private EmployeeManager employeeManager;
    private Employee employee;

    /**
     * This method will be called before each test method
     */
    @BeforeEach
    public void initializeObjectsWeNeed() {
        employeeManager = Mockito.mock(EmployeeManager.class);
        employee = new Employee();
        employee.setUsername("Zerina");
        employee.setPassword("Zerina123");
    }

    /**
     * In this method it will be tested validateEmployee for employee that exists in the base
     */
    @Test
    void validateEmployee() {
        try {
            Mockito.doCallRealMethod().when(employeeManager).validateEmployee(employee);
        } catch (ArtGalleryException e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }

    /**
     * In this method it will be tested validateEmployeeUsername for employee with incorrect username
     */
    @Test
    void validateEmployeeUsername() throws ArtGalleryException {
        Employee incorrect = new Employee("Zer", "Zerina123");
        Mockito.doCallRealMethod().when(employeeManager).validateEmployee(incorrect);
        ArtGalleryException asException1 = Assertions.assertThrows(ArtGalleryException.class, () -> employeeManager.validateEmployee(incorrect), "The username or the password isn't correct.");
        Assertions.assertEquals("The username or the password isn't correct.", asException1.getMessage());
    }

    /**
     * In this method it will be tested validateEmployeePassword for employee that exists, but the password is incorrect
     */
    @Test
    void validateEmployeePassword() throws ArtGalleryException {
        Employee incorrect = new Employee("Zerina", "Zerina");
        Mockito.doCallRealMethod().when(employeeManager).validateEmployee(incorrect);
        ArtGalleryException asException1 = Assertions.assertThrows(ArtGalleryException.class, () -> employeeManager.validateEmployee(incorrect), "The username or the password isn't correct.");
        Assertions.assertEquals("The username or the password isn't correct.", asException1.getMessage());
    }
}


