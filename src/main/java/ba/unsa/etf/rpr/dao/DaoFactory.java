package ba.unsa.etf.rpr.dao;

/**
 * DaoFactory represents the implementation of Factory design pattern
 * @author Zerina Jasarspahic
 */
public class DaoFactory {

    private static final ArtistDao artistDao =  ArtistDaoSQLImpl.getInstance();
    private static final ArtWorkDao artWorkDao = ArtWorkDaoSQLImpl.getInstance();
    private static final ArtStyleDao artStyleDao = ArtStyleDaoSQLImpl.getInstance();
    private static final EmployeeDao employeeDao = EmployeeDaoSQLImpl.getInstance();

    private DaoFactory(){
    }

    /**
     * Method that returns ArtistDao object
     * @return ArtistDao
     */
    public static ArtistDao artistDao(){
        return artistDao;
    }

    /**
     * Method that returns ArtWorkDao object
     * @return ArtWorkDao
     */
    public static ArtWorkDao artWorkDao(){
        return artWorkDao;
    }

    /**
     * Method that returns ArtStyleDao object
     * @return ArtistDao
     */
    public static ArtStyleDao artStyleDao(){
        return artStyleDao;
    }

    /**
     * Method that returns EmployeeDao object
     * @return ArtistDao
     */
    public static EmployeeDao employeeDao(){
        return employeeDao;
    }

}