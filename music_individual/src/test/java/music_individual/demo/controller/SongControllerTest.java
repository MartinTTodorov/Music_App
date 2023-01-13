package music_individual.demo.controller;
import music_individual.demo.business.ISongsManager;
import music_individual.demo.domain.CreateSongRequest;
import music_individual.demo.persistence.entities.SongEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SongControllerTest {

    @InjectMocks
    private SongController songController;

    @Mock
    private ISongsManager songsManager;

    @Test
    public void testGetAllSongs() {
        // Arrange
        when(songsManager.GetAllSongs()).thenReturn(new ArrayList<>());

        // Act
        ResponseEntity response = songController.GetAllSongs();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testCreateSong() {
        // Arrange
        CreateSongRequest request = new CreateSongRequest();
        request.setName("songName");
        request.setAuthor("songAuthor");
        request.setType("songType");

        // Act
        songsManager.AddSong(songController.songConverter(request));
        ResponseEntity response = songController.CreateSong(request);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Successful", response.getBody());
    }

    @Test
    public void testCreateSongError() {
        // Arrange
        CreateSongRequest request = new CreateSongRequest();
        request.setName("songName");
        request.setAuthor("songAuthor");
        request.setType("songType");

        // Act
        doThrow(new RuntimeException("Error adding song")).when(songsManager).AddSong(any());

        // Assert
        assertThrows(RuntimeException.class,()->songController.CreateSong(request));
    }



    @Test
    public void testSongConverter() {
        // Arrange
        CreateSongRequest request = new CreateSongRequest();
        request.setName("songName");
        request.setAuthor("songAuthor");
        request.setType("songType");

        // Act
        SongEntity song = songController.songConverter(request);

        // Assert
        assertEquals("songName", song.getName());
        assertEquals("songAuthor",song.getAuthor());
        assertEquals("songType",song.getType());
    }


}

