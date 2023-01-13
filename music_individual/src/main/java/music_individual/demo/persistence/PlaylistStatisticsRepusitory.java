package music_individual.demo.persistence;

import music_individual.demo.persistence.entities.ListenedPlaylistsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistStatisticsRepusitory extends JpaRepository<ListenedPlaylistsEntity, Integer> {

    @Query(value = "SELECT playlist_id, COUNT(*) " +
            "FROM musicindividual.listened_playlists " +
            "WHERE played_on BETWEEN date_sub(current_date(), interval 7 DAY) AND current_date() " +
            "GROUP BY playlist_id " +
            "ORDER BY count(*) DESC" +
            "LIMIT 5;", nativeQuery = true)
    public List<ListenedPlaylistsEntity> getTop5ListenedPlaylistsLast7Days();
}
