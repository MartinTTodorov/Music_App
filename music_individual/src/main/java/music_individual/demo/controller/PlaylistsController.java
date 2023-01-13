package music_individual.demo.controller;

import lombok.AllArgsConstructor;
import music_individual.demo.business.IPlaylistsManager;
import music_individual.demo.business.ISongsManager;
import music_individual.demo.business.IUsersManager;
import music_individual.demo.business.exception.ObjectAlreadyIExistsException;
import music_individual.demo.business.exception.ObjectMissingException;
import music_individual.demo.domain.AddSongToPlaylistRequest;
import music_individual.demo.persistence.entities.PlaylistEntity;
import music_individual.demo.persistence.entities.SongEntity;
import music_individual.demo.persistence.entities.UserEntity;
import music_individual.demo.security.Authorization.isAuthorized;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/playlists")
@CrossOrigin("http://localhost:3000/")
@AllArgsConstructor
public class PlaylistsController {
    private IPlaylistsManager playlistsManager;
    private IUsersManager usersManager;
    private ISongsManager songsManager;

    @isAuthorized
    @RolesAllowed({"ROLE_ADMIN"})
    @GetMapping
    public ResponseEntity GetAllPlaylists(){
        return ResponseEntity.ok(playlistsManager.GetPlaylists());
    }

    @isAuthorized
    @RolesAllowed({"ROLE_ADMIN"})
    @GetMapping("{id}")
    public ResponseEntity getPlaylist(@PathVariable(value = "id") Integer id){
        PlaylistEntity playlist;
        try{
            playlist = playlistsManager.getPlaylistByID(id);
        }
        catch (ObjectMissingException e){
            return ResponseEntity.status(405).body(e.getMessage());
        }
        return ResponseEntity.ok(playlist);
    }

    @GetMapping("user/{userId}")
    public ResponseEntity getUserPlaylists(@PathVariable(value = "userId") Integer id){

        UserEntity user;
        try{
            user = usersManager.GetUserByID(id);
        }
        catch(ObjectMissingException e){
            return ResponseEntity.status(405).body(e.getMessage());
        }
        return ResponseEntity.ok(playlistsManager.getUserPlaylists(user));
    }

    @PostMapping("{id}")
    public ResponseEntity addSongToPlaylist(@PathVariable(value = "id") Integer id, @RequestBody AddSongToPlaylistRequest request){
        try{
            SongEntity song = songsManager.getSongByID(request.getSongId());
            PlaylistEntity playlist = playlistsManager.getPlaylistByID(id);
            playlistsManager.addSongToPlaylist(playlist, song);
        }
        catch(ObjectMissingException e){
            return ResponseEntity.status(405).body(e.getMessage());
        }
        catch(ObjectAlreadyIExistsException e){
            return ResponseEntity.status(406).body(e.getMessage());
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity DeletePlaylist(@PathVariable Integer playlistId){

        try{
            songsManager.DeleteSong(playlistId);
        }
        catch(ObjectMissingException e){
            return ResponseEntity.status(405).body(e.getMessage());
        }
        return ResponseEntity.noContent().build();
    }



}
