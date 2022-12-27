package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.*;
import ba.unsa.etf.rpr.domain.ArtStyle;
import ba.unsa.etf.rpr.domain.ArtWork;
import ba.unsa.etf.rpr.domain.Artist;

import java.util.List;

public class App {
    public static void main(String[] args) {
        {
            //ArtStyleDao dao = new ArtStyleDaoSQLImpl();
            //ArtStyle as = new ArtStyle();
            //as.setId(461);
            //as.setName("Romanticism");
            //dao.add(as);
            //List<ArtStyle> artstyles = dao.getAll();
            //System.out.println(artstyles);
            //dao.delete(462);
            //dao.update(as);
            //System.out.println(artstyles);
            //System.out.println(dao.getById(460).toString());
        }
        {
            //ArtistDao dao = new ArtistDaoSQLImpl();
            //Artist a = new Artist();
            //a.setId(5);
            //a.setName("Claude Monet");
            //a.setLifespan("1840-1926");
            //dao.add(a);
            //dao.update(a);
            //List<Artist> artists = dao.getAll();
            //System.out.println(artists);
            //System.out.println(dao.getById(130).toString());
            //artists = dao.searchByNationality("Italian");
            //ovo ispod ne radi
            /*ArtStyle as = new ArtStyle();
            as.setId(400);
            as.setName("Renaissance");
            artists = dao.searchByArtStyle(as);
            System.out.println(artists);*/
        }
        {
            //da li kada se doda djelo treba dodati i umjetnik automatksi iako on ne postoji vec u bazi
            /*Artist a = new Artist();
            a.setId(192);
            a.setName("Eugene Delacroix");
            a.setNationality("French");
            ArtWorkDao dao = new ArtWorkDaoSQLImpl();
            ArtWork aw = new ArtWork();
            ArtStyle as = new ArtStyle();
            as.setId(461);
            as.setName("Romanticism");
            aw.setName("Liberty Leading the People");
            aw.setArtist(a);
            aw.setMovement(as);
            aw.setPrice(400000000);
            aw.setId(311);
            //dao.add(aw);
            //dao.update(aw);
            //dao.delete(312);
            //dao.delete(313);
            System.out.println(dao.getById(200).toString());
            List<ArtWork> artworks = dao.getAll();
            System.out.println(artworks);*/
        }
    }
}