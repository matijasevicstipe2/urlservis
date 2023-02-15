package hr.smatijasevic.urlservis.security.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {
    private boolean succes;
    private String description;
    private String password;

    public RegisterResponse(boolean succes, String description) {
    }
}
