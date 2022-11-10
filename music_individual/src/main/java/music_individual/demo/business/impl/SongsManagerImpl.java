package music_individual.demo.business.impl;

import music_individual.demo.business.ISongsManager;
import music_individual.demo.persistence.SongsRepository;
import music_individual.demo.persistence.entities.SongEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
