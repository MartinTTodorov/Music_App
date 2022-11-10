package music_individual.demo.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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

    //Created by the user with ID
    //One to many?
    //@ManyToMany(mappedBy = "playlists")
    @Column
    private Integer userId;
}
