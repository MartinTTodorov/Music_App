package music_individual.demo.business;

import music_individual.demo.persistence.entities.SongEntity;

import java.util.List;


public interface ISongsManager {
    public List<SongEntity> GetAllSongs();
    public void AddSong(SongEntity song);
    public SongEntity getSongByID(Integer id);
    public void UpdateSong(SongEntity updatedSong);
    public void DeleteSong(Integer id);
}
