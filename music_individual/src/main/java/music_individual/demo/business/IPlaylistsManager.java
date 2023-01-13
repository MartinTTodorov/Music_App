package music_individual.demo.business;

import music_individual.demo.persistence.entities.PlaylistEntity;
import music_individual.demo.persistence.entities.SongEntity;
import music_individual.demo.persistence.entities.UserEntity;
import org.apache.catalina.User;

import java.util.List;

public interface IPlaylistsManager {
    public List<PlaylistEntity> GetPlaylists();
    public List<PlaylistEntity> getUserPlaylists(UserEntity user);
    public void addSongToPlaylist(PlaylistEntity playlist, SongEntity song);
    public PlaylistEntity getPlaylistByID(Integer id);
    public void AddPlaylist(PlaylistEntity playlist);
    public void RemovePlaylist(Integer id);

}
