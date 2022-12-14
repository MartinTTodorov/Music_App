package music_individual.demo.persistence;

import music_individual.demo.persistence.entities.PlaylistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistsRepusitory extends JpaRepository<PlaylistEntity, Integer> {

}
