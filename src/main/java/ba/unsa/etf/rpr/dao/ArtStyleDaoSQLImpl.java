package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.ArtStyle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtStyleDaoSQLImpl implements ArtStyleDao {

    private Connection connection;

    public ArtStyleDaoSQLImpl(){
        try {
            this.connection = DataBaseDao.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArtStyle getById(int id) {

        String query = "SELECT * FROM ArtStyles WHERE id = ?";

        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()){ // result set is iterator.
                ArtStyle item = new ArtStyle();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setYear(rs.getString("year"));
                rs.close();
                return item;
            }
        } catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }

        return null;
    }


    @Override
    public ArtStyle add(ArtStyle item) {

        String insert = "INSERT INTO ArtStyles(name, year) VALUES(?, ?)";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, item.getName());
            stmt.setString(2, item.getYear());
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
    public ArtStyle update(ArtStyle item) {
        String insert = "UPDATE ArtStyles SET name = ?, year = ? WHERE id = ?";

        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert);
            stmt.setObject(1, item.getName());
            stmt.setObject(2, item.getYear());
            stmt.setObject(3, item.getId());
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return item;
    }

    @Override
    public void delete(int id) {
        String insert = "DELETE FROM ArtStyles WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<ArtStyle> getAll() {
        String query = "SELECT * FROM ArtStyles";
        List<ArtStyle> artStyles = new ArrayList<>();

        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ArtStyle item = new ArtStyle();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setYear(rs.getString("year"));
                artStyles.add(item);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace(); // poor error handling
        }

        return artStyles;
    }
}