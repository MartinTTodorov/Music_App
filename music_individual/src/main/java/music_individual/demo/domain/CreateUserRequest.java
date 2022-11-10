package music_individual.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
public class CreateUserRequest {
    
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
