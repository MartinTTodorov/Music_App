package music_individual.demo.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import music_individual.demo.persistence.entities.SongEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "playlists")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @NotBlank
    @Column
    private String name;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity userId;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "playlist_songs",
            joinColumns = {@JoinColumn(name = "playlist_id", referencedColumnName = "id"
            )},
            inverseJoinColumns = {@JoinColumn(name = "song_id", referencedColumnName = "id"
            )}
    )
    private List<SongEntity> songs = new ArrayList<>();
}
