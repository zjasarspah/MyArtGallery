package ba.unsa.etf.rpr.exceptions;

public class ArtGalleryException extends Exception{

    public ArtGalleryException (String message, Exception reason){
        super(message, reason);
    }

    public ArtGalleryException (String message){
        super(message);
    }
}