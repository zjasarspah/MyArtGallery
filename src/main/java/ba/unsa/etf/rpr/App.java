package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.ArtStyleManager;
import ba.unsa.etf.rpr.business.ArtWorkManager;
import ba.unsa.etf.rpr.business.ArtistManager;
import ba.unsa.etf.rpr.domain.ArtStyle;
import ba.unsa.etf.rpr.domain.ArtWork;
import ba.unsa.etf.rpr.domain.Artist;
import org.apache.commons.cli.*;
import java.io.PrintWriter;
import java.util.List;

/**
 * CLI (Command Line Interface) implementation in following class
 * Even though this type of presentation layer (called CLI) is becoming past tense for GUI apps
 * it's good to see how you can manipulate data through command line and database also
 * @author Zerina Jašarspahić
 */
public class App {
    /**
     * Defining final variables to describe all code having options
     */
    private static final Option addArtwork = new Option("aw","add-artwork",false, "Adding new artwork to art gallery database");
    private static final Option artistDefinition = new Option(null, "artist",false, "Defining artist for next added artwork");
    private static final Option artStyleDefinition = new Option(null, "artStyle",false, "Defining art style for next added artwork");
    private static final Option addArtist = new Option("a","add-artist",false, "Adding new artist to art gallery database");
    private static final Option addArtStyle = new Option("as","add-artStyle",false, "Adding new art style to art gallery database");

    /**
     *
     * @param options
     *
     */
    public static void printFormattedOptions(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        PrintWriter printWriter = new PrintWriter(System.out);
        helpFormatter.printUsage(printWriter, 150, "java -jar MyArtGallery.jar [option] 'something else if needed' ");
        helpFormatter.printOptions(printWriter, 150, options, 2, 7);
        printWriter.close();
    }

    public static Options addOptions() {
        Options options = new Options();
        options.addOption(addArtwork);
        options.addOption(artistDefinition);
        options.addOption(artStyleDefinition);
        options.addOption(addArtist);
        options.addOption(addArtStyle);
        return options;
    }

    public static Artist searchThroughArtists(List<Artist> listOfArtists, String artistName) {

        Artist artist = null;
        artist = listOfArtists.stream().filter(a -> a.getName().toLowerCase().equals(artistName.toLowerCase())).findAny().get();
        return artist;
    }

    public static ArtStyle searchThroughArtStyles(List<ArtStyle> listOfArtStyles, String artStyleName) {
        ArtStyle artstyle = null;
        artstyle = listOfArtStyles.stream().filter(as -> as.getName().toLowerCase().equals(artStyleName.toLowerCase())).findAny().get();
        return artstyle;
    }

    /**
     *
     * @param args
     * @throws Exception
     */

    public static void main(String[] args) throws Exception {
        Options options = addOptions();

        CommandLineParser commandLineParser = new DefaultParser();

        CommandLine cl = commandLineParser.parse(options, args);

        if((cl.hasOption(addArtwork.getOpt()) || cl.hasOption(addArtwork.getLongOpt())) && cl.hasOption((artistDefinition.getLongOpt())) && cl.hasOption((artStyleDefinition.getLongOpt()))){
            ArtWorkManager artworkManager = new ArtWorkManager();
            ArtistManager artistManager = new ArtistManager();
            ArtStyleManager artStyleManager = new ArtStyleManager();
            Artist artist = null;
            try {
                artist = searchThroughArtists(artistManager.getAll(), cl.getArgList().get(1));
            }catch(Exception e) {
                System.out.println("There is no artist in the list! Try again.");
                System.exit(1);
            }

            ArtStyle artStyle = null;
            try {
                artStyle = searchThroughArtStyles(artStyleManager.getAll(), cl.getArgList().get(1));
            }catch(Exception e) {
                System.out.println("There is no art style in the list! Try again.");
                System.exit(1);
            }


            ArtWork artwork = new ArtWork();
            artwork.setArtist(artist);
            artwork.setArtStyle(artStyle);
            artwork.setName(cl.getArgList().get(0));
            artworkManager.add(artwork);
            System.out.println("You successfully added artwork to database!");

        }
    }
}