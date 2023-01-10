package music_individual.demo.controller;

import lombok.AllArgsConstructor;
import music_individual.demo.business.IPlaylistsManager;
import music_individual.demo.security.Authorization.isAuthorized;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/playlists")
@CrossOrigin("http://localhost:3000/")
@AllArgsConstructor
public class PlaylistsController {
    private IPlaylistsManager playlistsManager;

//    @isAuthorized
//    @RolesAllowed({"ROLE_ADMIN"})
    @GetMapping
    public ResponseEntity GetAllPlaylists(){
        return ResponseEntity.ok(playlistsManager.GetPlaylists());
    }

//    @isAuthorized
//    @RolesAllowed({"ROLE_ADMIN"})
    @GetMapping("{id}")
    public ResponseEntity getPlaylist(@PathVariable(value = "id") Integer id){
        return ResponseEntity.ok(playlistsManager.getPlaylistByID(id));
    }
}
