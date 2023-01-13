package music_individual.demo.business;

import music_individual.demo.persistence.entities.ListenedPlaylistsEntity;

import java.util.List;

public interface IPlaylistStatisticsManager {
    public void registerListenedPlaylist(ListenedPlaylistsEntity listenedPlaylist);
    public List<ListenedPlaylistsEntity> getTop5PlaylistsLast7Days();
}
