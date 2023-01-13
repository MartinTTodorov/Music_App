package music_individual.demo.business.impl;


import music_individual.demo.business.IPlaylistsManager;
import music_individual.demo.business.exception.FailedCRUDException;
import music_individual.demo.business.exception.ObjectAlreadyIExistsException;
import music_individual.demo.business.exception.ObjectMissingException;
import music_individual.demo.persistence.PlaylistsRepusitory;
import music_individual.demo.persistence.entities.PlaylistEntity;
import music_individual.demo.persistence.entities.SongEntity;
import music_individual.demo.persistence.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlaylistsManagerImpl implements IPlaylistsManager {

    @Autowired
    private PlaylistsRepusitory repo;

    @Override
    public List<PlaylistEntity> GetPlaylists() {
        return repo.findAll().stream().toList();
    }


    @Override
    public List<PlaylistEntity> getUserPlaylists(UserEntity user) {

        return repo.findAllByUserId(user).stream().toList();
    }

    @Override
    public void addSongToPlaylist(PlaylistEntity playlist, SongEntity song) {
        if(playlist.getSongs().contains(song)){
            throw new ObjectAlreadyIExistsException("Song already added to playlist");
        }

        List<SongEntity> songs = playlist.getSongs();
        songs.add(song);
        playlist.setSongs(songs);
        repo.save(playlist);

    }

    @Override
    public PlaylistEntity getPlaylistByID(Integer id) {
        if(!repo.existsById(id)){
            throw new ObjectMissingException("Playlist not found");
        }
        return repo.getPlaylistEntityById(id);
    }

    @Override
    public void AddPlaylist(PlaylistEntity playlist) {
        repo.save(playlist);
    }

    @Override
    public void RemovePlaylist(Integer id) {
        if(!repo.existsById(id)){
            throw new ObjectMissingException("Playlist not found");
        }
        repo.deleteById(id);
    }
}
