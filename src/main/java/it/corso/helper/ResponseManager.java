package it.corso.helper;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

//La seguente annotazione @Component indica che questa classe è un componente gestito da Spring.
@Component
public class ResponseManager {
 private ObjectMapper mapper;

 // Costruttore della classe ResponseManager.
 public ResponseManager() {
     // Inizializza un'istanza dell'ObjectMapper, una classe utilizzata per la gestione di oggetti JSON.
     mapper = new ObjectMapper();
 }

 // Questo metodo genera un oggetto JSON di risposta con un codice e un messaggio specificati.
 public ObjectNode getResponse(int code, String message) {
     // Crea un nuovo oggetto ObjectNode, che rappresenta un oggetto JSON.
     ObjectNode response = mapper.createObjectNode();
     
     // Aggiunge un campo "code" all'oggetto JSON con il codice specificato.
     response.put("code", code);
     
     // Aggiunge un campo "message" all'oggetto JSON con il messaggio specificato.
     response.put("message", message);
     
     // Restituisce l'oggetto JSON di risposta.
     return response;
 }
}