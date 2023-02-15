package hr.smatijasevic.urlservis.security.auth;

import hr.smatijasevic.urlservis.security.config.JwtService;
import hr.smatijasevic.urlservis.security.user.Account;
import hr.smatijasevic.urlservis.security.user.AccountRepository;
import hr.smatijasevic.urlservis.security.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static hr.smatijasevic.urlservis.service.UrlServiceImpl.ALLOWED_CHARACTERS;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final AccountRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  public RegisterResponse register(RegisterRequest request) {
    Optional<Account> account = repository.findByAccountId(request.getAccountId());
    if (account.isPresent()) {
      return new RegisterResponse(false, "Account id already in use.");
    }

    String password = generatePassword(8);

    Account acc = Account.builder()
            .accountId(request.getAccountId())
            .password(passwordEncoder.encode(password))
            .userRole(Role.USER)
            .build();

    repository.save(acc);

    return new RegisterResponse(true, "Your account is opened.", password);
  }

  private String generatePassword(int length) {
    final StringBuilder password = new StringBuilder("");

    for (int i = 0; i < length; i++) {
      int index = (int) (Math.random() * ALLOWED_CHARACTERS.length());
      password.append(ALLOWED_CHARACTERS.charAt(index));
    }

    return password.toString();
  }

  public Optional<ResponseEntity<AuthenticationResponse>> authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getAccountId(),
            request.getPassword()
        )
    );

    Optional<Account> acc = repository.findByAccountId(request.getAccountId());
    if (acc.isEmpty()) {
      return Optional.empty();
    }
    String jwtToken = jwtService.generateToken(acc.get());

    return Optional.of(ResponseEntity.ok(AuthenticationResponse.builder()
        .token(jwtToken)
        .build()));
  }
}
