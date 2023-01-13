package music_individual.demo.business.impl;

import music_individual.demo.business.exception.ObjectAlreadyIExistsException;
import music_individual.demo.business.exception.ObjectMissingException;
import music_individual.demo.persistence.PlaylistsRepusitory;
import music_individual.demo.persistence.entities.PlaylistEntity;
import music_individual.demo.persistence.entities.SongEntity;
import music_individual.demo.persistence.entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlaylistsManagerImplTest {

    @Mock
    private PlaylistsRepusitory repoMOCK;

    @InjectMocks
    private PlaylistsManagerImpl playlistsManagerMOCK;

    @Test
    public void testGetPlaylistsSuccess() {
        // Arrange
        List<PlaylistEntity> expectedPlaylists = new ArrayList<>();
        expectedPlaylists.add(PlaylistEntity.builder().id(1).name("testPlaylist").userId(new UserEntity()).build());
        expectedPlaylists.add(PlaylistEntity.builder().id(2).name("testPlaylist2").userId(new UserEntity()).build());
        when(repoMOCK.findAll()).thenReturn(expectedPlaylists);

        // Act
        List<PlaylistEntity> actualPlaylists = playlistsManagerMOCK.GetPlaylists();

        // Assert
        assertEquals(expectedPlaylists, actualPlaylists);
    }

    @Test
    public void testGetPlaylistsFail() {
        // Arrange
        Exception exception = new RuntimeException("An error has occurred.");
        when(repoMOCK.findAll()).thenThrow(exception);

        // Act and Assert
        assertThrows(Exception.class, () -> {
            playlistsManagerMOCK.GetPlaylists();
        });


    }

    @Test
    public void testGetUserPlaylistsSuccess() {
        // Arrange
        UserEntity user = UserEntity.builder().id(1).username("testUser").password("1234").email("test@gmail.com").role("tester").build();
        PlaylistEntity playlist1 = PlaylistEntity.builder().id(1).name("testPlaylist").userId(user).build();
        PlaylistEntity playlist2 = PlaylistEntity.builder().id(2).name("testPlaylist2").userId(user).build();
        List<PlaylistEntity> expectedPlaylists = Arrays.asList(playlist1, playlist2);
        when(repoMOCK.findAllByUserId(user)).thenReturn(expectedPlaylists);

        // Act
        List<PlaylistEntity> actualPlaylists = playlistsManagerMOCK.getUserPlaylists(user);

        // Assert
        assertEquals(expectedPlaylists, actualPlaylists);
        verify(repoMOCK).findAllByUserId(user);
    }

    @Test
    public void testGetUserPlaylistsFail() {
        // Arrange
        UserEntity user = UserEntity.builder().id(1).username("testUser").password("1234").email("test@gmail.com").role("tester").build();
        when(repoMOCK.findAllByUserId(user)).thenReturn(Collections.emptyList());

        // Act
        List<PlaylistEntity> actualPlaylists = playlistsManagerMOCK.getUserPlaylists(user);

        // Assert
        assertTrue(actualPlaylists.isEmpty());
        verify(repoMOCK).findAllByUserId(user);
    }


    @Test
    public void addSongToPlaylist_songDoesNotExist_songAddedToPlaylist() {
        // Arrange
        PlaylistEntity playlist = new PlaylistEntity();
        SongEntity song = new SongEntity();

        // Act
        playlistsManagerMOCK.addSongToPlaylist(playlist,song);

        // Assert
        assertTrue(playlist.getSongs().contains(song));
        assertEquals(1, playlist.getSongs().size());
        verify(repoMOCK, times(1)).save(playlist);
    }

    @Test
    public void addSongToPlaylistFail() {
        // Arrange
        PlaylistEntity playlist = new PlaylistEntity();
        SongEntity song = new SongEntity();
        playlist.getSongs().add(song);

        // Act and Assert
        assertThrows(ObjectAlreadyIExistsException.class, () -> {
            playlistsManagerMOCK.addSongToPlaylist(playlist, song);
            verify(repoMOCK, never()).save(playlist);
        });
    }

    @Test
    public void getPlaylistByIDSuccess() {
        // Arrange
        Integer id = 1;
        PlaylistEntity expectedPlaylist = new PlaylistEntity();
        when(repoMOCK.existsById(id)).thenReturn(true);
        when(repoMOCK.getPlaylistEntityById(id)).thenReturn(expectedPlaylist);

        // Act
        PlaylistEntity result = playlistsManagerMOCK.getPlaylistByID(id);

        // Assert
        assertEquals(expectedPlaylist, result);
        verify(repoMOCK, times(1)).existsById(id);
        verify(repoMOCK, times(1)).getPlaylistEntityById(id);
    }

    @Test
    public void getPlaylistByIDFail() {
        // Arrange
        Integer id = 1;
        when(repoMOCK.existsById(id)).thenReturn(false);

        // Act and Assert
        assertThrows(ObjectMissingException.class, () -> playlistsManagerMOCK.getPlaylistByID(id));
        verify(repoMOCK, times(1)).existsById(id);
        verify(repoMOCK, never()).findById(id);
    }

    @Test
    public void AddPlaylistSuccess() {
        // Arrange
        PlaylistEntity playlist = new PlaylistEntity();

        // Act
        playlistsManagerMOCK.AddPlaylist(playlist);

        // Assert
        verify(repoMOCK, times(1)).save(playlist);
    }

    @Test
    public void RemovePlaylistSuccess() {
        // Arrange
        Integer id = 1;
        when(repoMOCK.existsById(id)).thenReturn(true);

        // Act
        playlistsManagerMOCK.RemovePlaylist(id);

        // Assert
        verify(repoMOCK, times(1)).deleteById(id);
    }

    @Test
    public void RemovePlaylistFail() {
        // Arrange
        Integer id = 1;
        when(repoMOCK.existsById(id)).thenReturn(false);

        // Act and Assert
        assertThrows(ObjectMissingException.class, () -> {
            playlistsManagerMOCK.RemovePlaylist(id);
        });
    }


}