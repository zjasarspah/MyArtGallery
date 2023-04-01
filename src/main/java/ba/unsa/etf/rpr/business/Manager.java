package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.exceptions.ArtGalleryException;

import java.util.List;

/**
 * Interface for Business Logic Layer
 * @author Zerina Jasarspahic
 */

public interface Manager <T> {

    /**
     * Method that returns every element from a table in a databse
     * @return list of elements
     * @throws ArtGalleryException
     */
    List<T> getAll() throws ArtGalleryException;

    /**
     * Method which deletes an element from the database
     * @param id of the element we want to delete
     * @throws ArtGalleryException
     */
    void delete(Integer id) throws ArtGalleryException;

    /**
     * Method that returns an item from the database that has the same id
     * @param id we search
     * @return item with provided id
     * @throws ArtGalleryException if something goes wrong
     */
    T getById(Integer id) throws ArtGalleryException;

    /**
     * Method which updates an item in the database
     * @param item we want to update
     * @throws ArtGalleryException
     */
    void update(T item) throws ArtGalleryException;

    /**
     * Method that adds an item to the database
     * @param item we want to add
     * @throws ArtGalleryException
     */
    T add(T item) throws ArtGalleryException;

    /**
     * Method that returns the list of items from the database that have the same name
     * @param name we search
     * @throws ArtGalleryException
     */
    List<T> searchByName (String name) throws ArtGalleryException;
}
