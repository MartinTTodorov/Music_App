package music_individual.demo.persistence.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserEntity {
    private int id;
    private String username;
    private String password;
}
