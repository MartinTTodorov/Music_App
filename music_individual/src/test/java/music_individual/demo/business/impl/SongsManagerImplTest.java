package music_individual.demo.business.impl;

import music_individual.demo.business.exception.ObjectMissingException;
import music_individual.demo.persistence.SongsRepository;
import music_individual.demo.persistence.entities.SongEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SongsManagerImplTest {
    @Mock
    SongsRepository repoMOCK;

    @InjectMocks
    SongsManagerImpl songsManagerMOCK;

    @Test
    public void testGetAllSongs() {
        // Arrange
        List<SongEntity> expectedSongs = Arrays.asList(new SongEntity(), new SongEntity());
        when(repoMOCK.findAll()).thenReturn(expectedSongs);

        // Act
        List<SongEntity> actualSongs = songsManagerMOCK.GetAllSongs();

        // Assert
        assertEquals(expectedSongs, actualSongs);
        verify(repoMOCK, times(1)).findAll();
    }

    @Test
    public void testAddSong() {
        // Arrange
        SongEntity song = SongEntity.builder().id(1).name("testSong").author("testAuthor").type("testType").build();
        when(repoMOCK.save(any(SongEntity.class))).thenReturn(song);

        // Act
        songsManagerMOCK.AddSong(song);

        // Assert
        verify(repoMOCK, times(1)).save(song);
    }

    @Test
    public void testGetSongByIDSuccess() {
        // Arrange
        Integer id = 1;
        SongEntity expectedSong = SongEntity.builder().id(id).name("testSong").author("testAuthor").type("testType").build();
        when(repoMOCK.existsById(id)).thenReturn(true);
        when(repoMOCK.getSongEntityById(id)).thenReturn(expectedSong);

        // Act
        SongEntity actualSong = songsManagerMOCK.getSongByID(id);

        // Assert
        assertEquals(expectedSong, actualSong);
        verify(repoMOCK, times(1)).existsById(id);
        verify(repoMOCK, times(1)).getSongEntityById(id);
    }

    @Test
    public void testGetSongByIDFail() {
        // Arrange
        Integer id = 1;
        when(repoMOCK.existsById(id)).thenReturn(false);

        // Act and Assert
        assertThrows(ObjectMissingException.class, ()-> songsManagerMOCK.getSongByID(id));
        verify(repoMOCK, times(1)).existsById(id);
        verify(repoMOCK, never()).getSongEntityById(id);
    }

    @Test
    public void testUpdateSongSuccess() {
        // Arrange
        SongEntity updatedSong = SongEntity.builder().id(1).name("updatedTestSong").author("testAuthor").type("testType").build();
        SongEntity existingSong = SongEntity.builder().id(1).name("testSong").author("testAuthor").type("testType").build();
        existingSong.setPlaylists(updatedSong.getPlaylists());
        Optional<SongEntity> songOptional = Optional.of(existingSong);
        when(repoMOCK.findById(updatedSong.getId())).thenReturn(songOptional);

        // Act
        songsManagerMOCK.UpdateSong(updatedSong);

        // Assert
        verify(repoMOCK, times(1)).save(updatedSong);
        assertEquals(existingSong.getPlaylists(), updatedSong.getPlaylists());
    }

    @Test
    public void testUpdateSongFail() {
        // Arrange
        SongEntity updatedSong = SongEntity.builder().id(1).name("updatedTestSong").author("testAuthor").type("testType").build();
        when(repoMOCK.findById(updatedSong.getId())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ObjectMissingException.class, ()-> songsManagerMOCK.UpdateSong(updatedSong));
        verify(repoMOCK, times(1)).findById(1);
        verify(repoMOCK, never()).save(updatedSong);
    }

    @Test
    public void testDeleteSongSuccess() {
        // Arrange
        Integer id = 1;
        when(repoMOCK.existsById(id)).thenReturn(true);

        // Act
        songsManagerMOCK.DeleteSong(id);

        // Assert
        verify(repoMOCK, times(1)).deleteById(id);
    }

    @Test
    public void testDeleteSongFail(){
        //Arrange
        Integer id = 1;
        when(repoMOCK.existsById(id)).thenReturn(false);

        //Act and Assert
        assertThrows(ObjectMissingException.class, () -> songsManagerMOCK.DeleteSong(id));
        verify(repoMOCK, times(1)).existsById(id);
        verify(repoMOCK, never()).deleteById(id);
    }



}