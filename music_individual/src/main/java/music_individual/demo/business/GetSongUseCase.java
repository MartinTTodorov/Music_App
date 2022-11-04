package music_individual.demo.business;


import music_individual.demo.domain.Song;

import java.util.Optional;

public interface GetSongUseCase {
    Optional<Song> getSong(int id);
}
