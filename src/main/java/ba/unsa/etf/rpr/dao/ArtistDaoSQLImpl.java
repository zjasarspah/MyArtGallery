package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.ArtStyle;
import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.exceptions.ArtGalleryException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ArtistDaoSQLImpl extends AbstractDao<Artist> implements ArtistDao {

    private Connection connection;

    public ArtistDaoSQLImpl() {
        super("Artists");
    }

    @Override
    public Artist row2object(ResultSet rs) throws ArtGalleryException {
        try {
            Artist cat = new Artist();
            cat.setId(rs.getInt("id"));
            cat.setName(rs.getString("name"));
            cat.setLifespan(rs.getString("lifespan"));
            return cat;
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
        return row;
    }

    @Override
    public List<Artist> searchByArtStyle(ArtStyle movement) {
        return null;
    }


}
