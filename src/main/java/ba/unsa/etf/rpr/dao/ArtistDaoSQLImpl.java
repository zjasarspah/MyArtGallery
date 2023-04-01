package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.exceptions.ArtGalleryException;
import java.sql.*;
import java.util.Map;
import java.util.TreeMap;

/**
 * MySQL Implementation of ArtistDao
 * @author Zerina Jasarspahic
 */

public class ArtistDaoSQLImpl extends AbstractDao<Artist> implements ArtistDao {

    private static  ArtistDaoSQLImpl instance = null;
    private ArtistDaoSQLImpl() {
        super("Artists");
    }

    /**
     * Implementation of factory model for singleton design pattern
     * @return ArtistDaoSqlImpl
     */
    public static ArtistDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new ArtistDaoSQLImpl();
        return instance;
    }
    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }

    @Override
    public Artist row2object(ResultSet rs) throws ArtGalleryException {
        try {
            Artist a = new Artist();
            a.setId(rs.getInt("id"));
            a.setName(rs.getString("name"));
            a.setLifespan(rs.getString("lifespan"));
            a.setNationality(rs.getString("nationality"));
            return a;
        } catch (SQLException e) {
            throw new ArtGalleryException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Artist object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", object.getId());
        row.put("name", object.getName());
        row.put("lifespan", object.getLifespan());
        row.put("nationality", object.getNationality());
        return row;
    }
}
