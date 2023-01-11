package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.exceptions.ArtGalleryException;
import java.sql.*;
import java.util.Map;
import java.util.TreeMap;

/**
 * MySQL Implementation of EmployeeDao
 * @author Zerina Jasarspahic
 */
public class EmployeeDaoSQLImpl extends AbstractDao<Employee> implements EmployeeDao {

    private static  EmployeeDaoSQLImpl instance = null;
    private EmployeeDaoSQLImpl() {
        super("Employees");
    }

    public static EmployeeDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new EmployeeDaoSQLImpl();
        return instance;
    }
    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }

    @Override
    public Employee row2object(ResultSet rs) throws ArtGalleryException {
        try {
            Employee e = new Employee();
            e.setId(rs.getInt("id"));
            e.setUsername(rs.getString("username"));
            e.setPassword(rs.getString("password"));
            return e;
        } catch (SQLException e) {
            throw new ArtGalleryException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Employee object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", object.getId());
        row.put("username", object.getUsername());
        row.put("password", object.getPassword());
        return row;
    }

}