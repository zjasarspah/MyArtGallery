package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.ArtStyle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtStyleDaoSQLImpl implements ArtStyleDao {

    private Connection connection;

    public ArtStyleDaoSQLImpl(){
        try{
            this.connection = DriverManager.getConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public ArtStyle getById(int id) {

        String query = "SELECT * FROM categories WHERE id = ?";

        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()){ // result set is iterator.
                ArtStyle artstyle = new ArtStyle();
                artstyle.setId(rs.getInt("id"));
                artstyle.setName(rs.getString("name"));
                rs.close();
                return artstyle;
            }else{
                return null; // if there is no elements in the result set return null
            }
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return null;
    }


    @Override
    public ArtStyle add(ArtStyle item) {

        String insert = "INSERT INTO categories(id, name) VALUES(?)";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, item.getId());
            stmt.setString(2, item.getName());
            stmt.executeUpdate();
            return item;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ArtStyle update(ArtStyle item) {
        String insert = "UPDATE categories SET name = ? WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, item.getName());
            stmt.setObject(2, item.getId());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void delete(int id) {
        String insert = "DELETE FROM categories WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public List<ArtStyle> getAll() {
        String query = "SELECT * FROM categories";
        List<ArtStyle> artStyles = new ArrayList<>();
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ArtStyle artStyle = new ArtStyle();
                artStyle.setId(rs.getInt("id"));
                artStyle.setName(rs.getString("name"));
                artStyles.add(artStyle);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // poor error handling
        }

        return artStyles;
    }
}