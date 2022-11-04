package music_individual.demo.persistence;
import music_individual.demo.persistence.entities.SongEntity;

import java.util.List;
import java.util.Optional;

public interface SongsRepo {


    List<SongEntity> GetAllSongs();

    Optional<SongEntity> GetSongByID(int id);
}
