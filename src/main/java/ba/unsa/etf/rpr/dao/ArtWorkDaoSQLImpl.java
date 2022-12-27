package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.ArtStyle;
import ba.unsa.etf.rpr.domain.ArtWork;
import ba.unsa.etf.rpr.domain.Artist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtWorkDaoSQLImpl implements ArtWorkDao {

    public static Connection connection;

    public ArtWorkDaoSQLImpl() {
        try {
            this.connection = DataBaseDao.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection returnConnection () {
        return connection;
    }
    @Override
    public List<ArtWork> searchByArtStyle(ArtStyle movement) {
        String query = "SELECT * FROM ArtWorks WHERE id_artstyle = ?";
        List<ArtWork> artWorks = new ArrayList<>();

        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, movement.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                artWorks.add((new ArtWorkDaoSQLImpl()).getById(rs.getInt("id")));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return artWorks;
    }

    @Override
    public List<ArtWork> searchByArtist(Artist artist) {
        String query = "SELECT aw.name FROM Artist a, ArtWork aw WHERE a.id = ? AND a.id = aw.artist_id;";
        List<ArtWork> artWorks = new ArrayList<>();

        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, artist.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                artWorks.add((new ArtWorkDaoSQLImpl()).getById(rs.getInt("id")));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return artWorks;
    }


    @Override
    public ArtWork getById(int id) {
        String query = "SELECT * FROM ArtWorks WHERE id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ArtWork item = new ArtWork();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setMovement((new ArtStyleDaoSQLImpl()).getById(rs.getInt("id_artstyle")));
                item.setArtist((new ArtistDaoSQLImpl()).getById(rs.getInt("id_artist")));
                rs.close();
                return item;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ArtWork add(ArtWork item) {
        String insert = "INSERT INTO ArtWorks(name, id_artist, id_artstyle) VALUES(?,?,?)";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, item.getName());
            stmt.setInt(2, item.getArtist().getId());
            stmt.setInt(3, item.getMovement().getId());
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
    public ArtWork update(ArtWork item) {
        String insert = "UPDATE ArtWorks SET name = ?, id_artist = ?, id_artstyle = ? WHERE id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(insert);
            stmt.setString(1, item.getName());
            stmt.setInt(2, item.getArtist().getId());
            stmt.setInt(3, item.getMovement().getId());
            stmt.setInt(4, item.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return item;
    }

    @Override
    public void delete(int id) {
        String insert = "DELETE FROM ArtWorks WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<ArtWork> getAll() {
        String query = "SELECT * FROM ArtWorks";
        List<ArtWork> artWorks = new ArrayList<>();
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ArtWork item = new ArtWork();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setMovement((new ArtStyleDaoSQLImpl()).getById(rs.getInt("id_artstyle")));
                item.setArtist((new ArtistDaoSQLImpl()).getById(rs.getInt("id_artist")));
                artWorks.add(item);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return artWorks;
    }
}