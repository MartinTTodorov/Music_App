package music_individual.demo.controller;

import lombok.RequiredArgsConstructor;
import music_individual.demo.business.ILoginManager;
import music_individual.demo.domain.LoginRequest;
import music_individual.demo.domain.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000/")
public class LoginController {
    private final ILoginManager loginManager;

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request){
        LoginResponse response = loginManager.login(request);
        return ResponseEntity.ok(response);
    }
}
