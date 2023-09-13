package it.corso.helper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Base64;

public class SecurityManager {
    
    // Questo metodo converte una stringa in input in una rappresentazione codificata Base64.
    public static String encode(String toEncode) {
        return Base64.getEncoder().encodeToString(toEncode.getBytes());
    }
    
    // Questo metodo decodifica una stringa codificata in Base64 in una stringa di testo.
    public static String decode(String toDecode) {
        return new String(Base64.getDecoder().decode(toDecode));
    }
    
    // Questo metodo genera un token di autenticazione basato sul nome utente e sull'orario corrente.
    public static String generateToken(String username) {
        // Ottiene l'orario corrente.
        LocalDateTime now = LocalDateTime.now();
        
        // Converte l'orario in un'istanza di Instant utilizzando l'offset corrente.
        Instant instant = now.toInstant(OffsetDateTime.now().getOffset());
        
        // Ottiene il timestamp in millisecondi dall'epoca Unix.
        long timestamp = instant.getEpochSecond() * 1000;
        
        // Codifica il nome utente e concatena il timestamp per creare il token.
        return encode(username) + "_" + timestamp;
    }
}
