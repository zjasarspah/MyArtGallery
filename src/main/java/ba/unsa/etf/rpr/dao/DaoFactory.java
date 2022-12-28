package ba.unsa.etf.rpr.dao;

/**
 * Factory method for singleton implementation of DAOs
 * @author Zerina Jasarspahic
 */
public class DaoFactory {

    private static final ArtistDao artistDao = new ArtistDaoSQLImpl();
    private static final ArtWorkDao artWorkDao = new ArtWorkDaoSQLImpl();
    private static final ArtStyleDao artStyleDao = new ArtStyleDaoSQLImpl();

    private DaoFactory(){
    }

    public static ArtistDao artistDao(){
        return artistDao;
    }

    public static ArtWorkDao artWorkDaoDao(){
        return artWorkDao;
    }

    public static ArtStyleDao artStyleDao(){
        return artStyleDao;
    }

}