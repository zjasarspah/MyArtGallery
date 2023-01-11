package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.ArtStyle;
import ba.unsa.etf.rpr.domain.ArtWork;
import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.exceptions.ArtGalleryException;
import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * MySQL Implementation of ArtWorkDao
 * @author Zerina Jasarspahic
 */

public class ArtWorkDaoSQLImpl extends AbstractDao<ArtWork> implements ArtWorkDao {

    public static Connection connection;

    public ArtWorkDaoSQLImpl() {
        super("Artworks");
    }

    @Override
    public ArtWork row2object(ResultSet rs) throws ArtGalleryException {
        try {
            ArtWork q = new ArtWork();
            q.setId(rs.getInt("id"));
            q.setName(rs.getString("name"));
            q.setArtist(DaoFactory.artistDao().getById(rs.getInt("id_artist")));
            q.setArtStyle(DaoFactory.artStyleDao().getById(rs.getInt("id_artstyle")));
            return q;
        } catch (Exception e) {
            throw new ArtGalleryException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(ArtWork object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", object.getId());
        row.put("name", object.getName());
        row.put("id_artist", object.getArtist().getId());
        row.put("id_artstyle", object.getArtStyle().getId());
        return row;
    }

    @Override
    public List<ArtWork> searchByArtStyle(ArtStyle artStyle) throws ArtGalleryException {
        return executeQuery("SELECT * FROM Artworks WHERE id_artstyle = ?", new Object[]{artStyle.getId()});
    }

    @Override
    public List<ArtWork> searchByArtist(Artist artist) throws  ArtGalleryException{
        return executeQuery("SELECT * FROM Artworks WHERE id_artist = ?", new Object[]{artist.getId()});
    }
}