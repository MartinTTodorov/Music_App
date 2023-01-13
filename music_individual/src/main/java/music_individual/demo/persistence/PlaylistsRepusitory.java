package music_individual.demo.persistence;

import music_individual.demo.persistence.entities.PlaylistEntity;
import music_individual.demo.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaylistsRepusitory extends JpaRepository<PlaylistEntity, Integer> {
    List<PlaylistEntity> findAllByUserId(UserEntity user);
    PlaylistEntity getPlaylistEntityById(Integer id);
}
