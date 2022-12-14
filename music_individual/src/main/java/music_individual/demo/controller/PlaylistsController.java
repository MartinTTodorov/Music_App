package music_individual.demo.controller;

import lombok.AllArgsConstructor;
import music_individual.demo.business.IPlaylistsManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/playlists")
@CrossOrigin("http://localhost:3000/")
@AllArgsConstructor
public class PlaylistsController {
    private IPlaylistsManager playlistsManager;

    @GetMapping
    public ResponseEntity GetAllPlaylists(){
        return ResponseEntity.ok(playlistsManager.GetPlaylists());
    }
}
