package music_individual.demo.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Music_rooms")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MusicRoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roomID;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity owner;

//    private List<UserEntity> listeners;

}
