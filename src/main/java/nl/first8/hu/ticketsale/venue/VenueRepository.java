package nl.first8.hu.ticketsale.venue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class VenueRepository {

    private final EntityManager entityManager;

    @Autowired
    public VenueRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<Concert> findConcertById(Long concertId) {
        return Optional.ofNullable(entityManager.find(Concert.class, concertId));
    }

    public Optional<List<Concert>> findByArtistName(String artistName){
        try{
            String query = "SELECT c FROM Concert c WHERE c.artist.name =:name";
            Optional<List<Concert>> result = Optional.of(entityManager.createQuery(query, Concert.class).setParameter("name", artistName).getResultList());
            return result;
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    public Optional<List<Concert>> findByArtistGenre(String artistGenre){
        try{
            String query = "SELECT c FROM Concert c WHERE c.artist.genre = :genre";
            Optional<List<Concert>> result = Optional.of(entityManager.createQuery(query, Concert.class).setParameter("genre", artistGenre).getResultList());
            return result;
        }catch(NoResultException ex) {
            return Optional.empty();
        }
    }

    public Optional<List<Concert>> findByDateFrom(String fromDate){
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            Date date = formatter.parse(fromDate);

            String query = "SELECT c FROM Concert c WHERE c.date >:date";
            Optional<List<Concert>> result = Optional.of(entityManager.createQuery(query, Concert.class).setParameter("date", date).getResultList());
            return result;
        }catch(NoResultException | ParseException pe) {
            return Optional.empty();
        }
    }

    public Optional<List<Concert>> findByConcertLocation(String location){
        try{
            String query = "SELECT c FROM Concert c WHERE c.location.name = :location";
            Optional<List<Concert>> result =  Optional.of(entityManager.createQuery(query, Concert.class).setParameter("location", location).getResultList());
            return result;
        }catch(NoResultException ex) {
            return Optional.empty();
        }
    }
}
