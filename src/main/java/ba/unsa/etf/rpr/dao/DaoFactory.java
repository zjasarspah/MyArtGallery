package ba.unsa.etf.rpr.dao;

/**
 * Factory method for singleton implementation of DAOs
 * @author Zerina Jasarspahic
 */
public class DaoFactory {

    private static final ArtistDao artistDao =  ArtistDaoSQLImpl.getInstance();
    private static final ArtWorkDao artWorkDao = ArtWorkDaoSQLImpl.getInstance();
    private static final ArtStyleDao artStyleDao = ArtStyleDaoSQLImpl.getInstance();
    private static final EmployeeDao employeeDao = EmployeeDaoSQLImpl.getInstance();

    private DaoFactory(){
    }
    public static ArtistDao artistDao(){
        return artistDao;
    }

    public static ArtWorkDao artWorkDao(){
        return artWorkDao;
    }

    public static ArtStyleDao artStyleDao(){
        return artStyleDao;
    }

    public static EmployeeDao employeeDao(){
        return employeeDao;
    }

}