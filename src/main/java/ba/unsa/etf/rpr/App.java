package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.*;
import ba.unsa.etf.rpr.domain.ArtStyle;
import ba.unsa.etf.rpr.domain.ArtWork;
import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.exceptions.ArtGalleryException;

import java.util.List;

public class App {
    public static void main(String[] args) {
        {
            /*try {
                //ArtStyleDao dao = new ArtStyleDaoSQLImpl();
                //ArtStyle as = new ArtStyle();
                //System.out.println(dao.getById(3).getName());
                //as.setId(6);
                //as.setName("Romanticism");
                //as.setYear("1888-1000");
                //dao.add(as);
                //dao.update(as);
                //dao.delete(6);
                //List<ArtStyle> artstyles = dao.getAll();
                //System.out.println(artstyles);
                //System.out.println(artstyles);
                //System.out.println(dao.getById(460).toString());
            } catch (ArtGalleryException e) {
                System.out.println(e);
            }*/
        }
        {
            /*try {
                ArtistDao dao = new ArtistDaoSQLImpl();
                //Artist a = new Artist();
                //a.setId(9);
                //a.setName("Emir");
                //a.setLifespan("1840-1926");
                //dao.delete(9);
                //dao.update(a);
                List<Artist> artists = dao.getAll();
                //System.out.println(artists);
                //System.out.println(dao.getById(2).getName());

                //ovo ispod ne radi
            ArtStyle as = new ArtStyle();
            as.setId(400);
            as.setName("Renaissance");
            //artists = dao.searchByArtStyle(as);
            System.out.println(artists);
            } catch (ArtGalleryException e) {
                System.out.println(e);
            }*/
        }
        {
           try {
            Artist a = new Artist();
            a.setId(0);
            a.setName("Eugène Delacroix");
            a.setLifespan("1798-1863");
            ArtWorkDao dao = new ArtWorkDaoSQLImpl();
            ArtWork aw = new ArtWork();
            ArtStyle as = new ArtStyle();
            as.setId(1);
            as.setName("Renaissance");
            as.setDuration("1300-1520");
            aw.setName("Liberty Leading the People");
            a = new ArtistDaoSQLImpl().add(a);
            as = new ArtStyleDaoSQLImpl().add(as);
            aw.setArtist(a);
            aw.setArtStyle(as);
            aw.setId(311);
            //dao.add(aw);
            //dao.update(aw);
            dao.delete(10);
            //dao.delete(313);
            System.out.println(dao.searchByArtStyle(as));
            //List<ArtWork> artworks = dao.getAll();
            //System.out.println(artworks);
            } catch (ArtGalleryException e) {
                    System.out.println(e);
                }
        }
    }
}