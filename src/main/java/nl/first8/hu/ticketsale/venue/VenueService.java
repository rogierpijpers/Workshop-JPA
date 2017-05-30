package nl.first8.hu.ticketsale.venue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Rogier on 30-5-2017.
 */
@Service
public class VenueService {
    private final VenueRepository venueRepository;

    @Autowired
    public VenueService(VenueRepository venueRepository){
        this.venueRepository = venueRepository;
    }

    public Optional<List<Concert>> getByArtistName(final String artistName) {
        return venueRepository.findByArtistName(artistName);
    }

    public Optional<List<Concert>> getByArtistGenre(final String genre) {
        return venueRepository.findByArtistGenre(genre);
    }

    public Optional<List<Concert>> getByDateFrom(final String date) {
        return venueRepository.findByDateFrom(date);
    }

    public Optional<List<Concert>> getByConcertLocation(final String location) {
        return venueRepository.findByConcertLocation(location);
    }
}
