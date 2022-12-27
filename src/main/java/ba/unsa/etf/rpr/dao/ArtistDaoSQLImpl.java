package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.ArtStyle;
import ba.unsa.etf.rpr.domain.Artist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistDaoSQLImpl implements ArtistDao {

    private Connection connection;

    public ArtistDaoSQLImpl() {
        try {
            this.connection = DataBaseDao.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Artist> searchByArtStyle(ArtStyle movement) {
        String query = "SELECT DISTINCT a.name FROM Artists a, ArtWorks aw, ArtStyles ass WHERE aw.artstyle_id = ? AND a.id = aw.artist_id AND ass.id = aw.artstyle_id";
        List<Artist> artists = new ArrayList<>();

        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, movement.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                artists.add((new ArtistDaoSQLImpl()).getById(rs.getInt(1)));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return artists;
    }

    @Override
    public Artist getById(int id) {
        String query = "SELECT * FROM Artists WHERE id = ?";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Artist item = new Artist();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setLifespan(rs.getString("lifespan"));
                rs.close();
                return item;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Artist add(Artist item) {
        String insert = "INSERT INTO Artists(name, lifespan) VALUES(?, ?)";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, item.getName());
            stmt.setString(2, item.getLifespan());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            item.setId(rs.getInt(1));
            return item;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return item;
    }

    @Override
    public Artist update(Artist item) {
        String insert = "UPDATE Artists SET name = ?, lifespan = ? WHERE id = ?";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(insert);
            stmt.setString(1, item.getName());
            stmt.setString(2, item.getLifespan());
            stmt.setInt(3, item.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return item;
    }

    @Override
    public void delete(int id) {
        String insert = "DELETE FROM Artists WHERE id = ?";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(insert);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Artist> getAll() {
        String query = "SELECT * FROM Artists";
        List<Artist> artists = new ArrayList<>();

        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Artist item = new Artist();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setLifespan(rs.getString("lifespan"));
                artists.add(item);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return artists;
    }
}
