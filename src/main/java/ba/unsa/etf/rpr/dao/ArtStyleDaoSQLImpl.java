package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.ArtStyle;
import ba.unsa.etf.rpr.exceptions.ArtGalleryException;
import java.sql.*;
import java.util.Map;
import java.util.TreeMap;

/**
 * MySQL Implementation of ArtStyleDao
 * @author Zerina Jasarspahic
 */

public class ArtStyleDaoSQLImpl extends AbstractDao<ArtStyle> implements ArtStyleDao {

    private static  ArtStyleDaoSQLImpl instance = null;

    private ArtStyleDaoSQLImpl(){
        super ("ArtStyles");
    }

    /**
     * Implementation of factory model for singleton design pattern
     * @return ArtStyleDaoSqlImpl
     */
    public static ArtStyleDaoSQLImpl getInstance(){
        if(instance==null)
            instance = new ArtStyleDaoSQLImpl();
        return instance;
    }
    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }

    @Override
    public ArtStyle row2object(ResultSet rs) throws ArtGalleryException {
        try {
            ArtStyle as = new ArtStyle();
            as.setId(rs.getInt("id"));
            as.setName(rs.getString("name"));
            as.setDuration(rs.getString("duration"));
            return as;
        } catch (SQLException e) {
            throw new ArtGalleryException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(ArtStyle object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", object.getId());
        row.put("name", object.getName());
        row.put("duration", object.getDuration());
        return row;
    }
}