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

    private Connection connection;

    public ArtStyleDaoSQLImpl(){
        super ("ArtStyles");
    }

    @Override
    public ArtStyle row2object(ResultSet rs) throws ArtGalleryException {
        try {
            ArtStyle cat = new ArtStyle();
            cat.setId(rs.getInt("id"));
            cat.setName(rs.getString("name"));
            cat.setDuration(rs.getString("duration"));
            return cat;
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