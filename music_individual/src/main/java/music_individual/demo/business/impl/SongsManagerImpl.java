package music_individual.demo.business.impl;

import music_individual.demo.business.ISongsManager;
import music_individual.demo.business.exception.ObjectMissingException;
import music_individual.demo.persistence.SongsRepository;
import music_individual.demo.persistence.entities.SongEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongsManagerImpl implements ISongsManager {

    @Autowired
    private SongsRepository repo;

    @Override
    public List<SongEntity> GetAllSongs() {

        return repo.findAll().stream().toList();
    }

    @Override
    public void AddSong(SongEntity song) {

        repo.save(song);
    }

    @Override
    public SongEntity getSongByID(Integer id) {
        if(!repo.existsById(id)){
            throw new ObjectMissingException("Song not found");
        }
        return repo.getSongEntityById(id);
    }

    @Override
    public void UpdateSong(SongEntity updatedSong) {
        Optional<SongEntity> songOptional = repo.findById(updatedSong.getId());
        if(songOptional.isPresent()){
            SongEntity existingSong = songOptional.get();
            updatedSong.setPlaylists(existingSong.getPlaylists());
            repo.save(updatedSong);
        }
        else{
            throw new ObjectMissingException("Song not found");
        }
    }

    @Override
    public void DeleteSong(Integer id) {
        if(!repo.existsById(id)){
            throw new ObjectMissingException("Song not found");
        }
        repo.deleteById(id);
    }


}
