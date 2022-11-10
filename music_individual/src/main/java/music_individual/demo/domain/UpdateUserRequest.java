package music_individual.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
public class UpdateUserRequest {

    private Integer id;

    @NotBlank
    private String password;

    @NotBlank
    private String email;
}
