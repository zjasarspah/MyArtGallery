package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.ArtWork;
import ba.unsa.etf.rpr.domain.Idable;
import ba.unsa.etf.rpr.exceptions.ArtGalleryException;

import java.util.List;

public interface Manager <T> {
    public List<T> getAll() throws ArtGalleryException;

    public void delete(int id) throws ArtGalleryException;

    public T getById(int id) throws ArtGalleryException;

    public void update(T item) throws ArtGalleryException;

    public T add(T item) throws ArtGalleryException;
}
