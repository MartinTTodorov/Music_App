package music_individual.demo.business.impl;

import lombok.AllArgsConstructor;
import music_individual.demo.business.GetSongUseCase;
import music_individual.demo.domain.Song;
import music_individual.demo.persistence.SongsRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetSongUseCaseImpl implements GetSongUseCase {

    private SongsRepo repo;
    @Override
    public Optional<Song> getSong(int id) {
        return repo.GetSongByID(id).map(SongConverter::convert);
    }
}
