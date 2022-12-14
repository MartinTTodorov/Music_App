package music_individual.demo.business;

import music_individual.demo.persistence.entities.PlaylistEntity;

import java.util.List;

public interface IPlaylistsManager {
    public List<PlaylistEntity> GetPlaylists();
    public void AddPlaylist(PlaylistEntity playlist);
    public void RemovePlaylist(Integer id);

}
