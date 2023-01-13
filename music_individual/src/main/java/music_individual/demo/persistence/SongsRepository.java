package music_individual.demo.persistence;

import music_individual.demo.persistence.entities.SongEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongsRepository extends JpaRepository<SongEntity, Integer> {
    SongEntity getSongEntityById(Integer id);
}
