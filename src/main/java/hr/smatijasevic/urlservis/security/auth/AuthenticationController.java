package hr.smatijasevic.urlservis.security.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("/account")
  public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED)
                         .body(service.register(request));
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
    return service.authenticate(request)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid credentials"));

  }
}
