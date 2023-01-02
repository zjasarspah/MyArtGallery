package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.exceptions.ArtGalleryException;

import java.util.List;

/**
 * Interface for Business Logic Layer
 * @author Zerina Jasarspahic
 */

public interface Manager <T> {
    List<T> getAll() throws ArtGalleryException;

    void delete(int id) throws ArtGalleryException;

    T getById(int id) throws ArtGalleryException;

    void update(T item) throws ArtGalleryException;

    T add(T item) throws ArtGalleryException;
}
