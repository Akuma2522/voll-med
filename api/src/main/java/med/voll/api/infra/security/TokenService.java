package med.voll.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import med.voll.api.domain.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.secret}")
    private String apiSecret;

    public String generarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret); // llamando al algoritmo de encriptacion de jwt
            return JWT.create() // creando el jwt
                    .withIssuer("voll med")  // con asunto voll med
                    .withSubject(usuario.getUsername()) // con nombre de usuario obtenido
                    .withClaim("id", usuario.getId())  // y mostrar el ID
                    .withExpiresAt(generarExpiracion()) // agregando un tiempo de expiracion
                    .sign(algorithm); // agregando la firma secreta
        } catch (JWTCreationException exception) {
            throw new RuntimeException();
        }
    }

    private Instant generarExpiracion() {
        return LocalDateTime.now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-05:00"));
    }

    public String getSubject(String token) {
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret); // verifica la firma
            verifier = JWT.require(algorithm)
                    // specify an specific claim validations
                    .withIssuer("voll med")
                    // reusable verifier instance
                    .build()
                    .verify(token);
            verifier.getSubject();
        } catch (JWTVerificationException exception) {
            System.out.println(exception.toString());
        }
        if (verifier.getSubject() == null) {
            throw new RuntimeException("verifier invalido");
        }
        return verifier.getSubject();
    }
}
