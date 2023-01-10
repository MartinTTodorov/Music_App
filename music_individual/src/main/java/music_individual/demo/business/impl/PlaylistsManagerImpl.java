package music_individual.demo.business.impl;


import music_individual.demo.business.IPlaylistsManager;
import music_individual.demo.persistence.PlaylistsRepusitory;
import music_individual.demo.persistence.entities.PlaylistEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistsManagerImpl implements IPlaylistsManager {

    @Autowired
    private PlaylistsRepusitory repo;

    @Override
    public List<PlaylistEntity> GetPlaylists() {
        return repo.findAll().stream().toList();
    }

    @Override
    public List<PlaylistEntity> getUserPlaylists(Integer id) {
        return repo.findAllByUserId(id).stream().toList();
    }

    @Override
    public PlaylistEntity getPlaylistByID(Integer id) {
        return repo.findById(id).get();
    }

    @Override
    public void AddPlaylist(PlaylistEntity playlist) {
        repo.save(playlist);
    }

    @Override
    public void RemovePlaylist(Integer id) {
        repo.deleteById(id);
    }
}
