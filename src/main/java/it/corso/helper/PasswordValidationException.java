package it.corso.helper;

//PasswordValidationException è una classe di eccezione personalizzata che estende RuntimeException.
public class PasswordValidationException extends RuntimeException {
	private static final long serialVersionUID = 5054532917036415626L;
	// serialVersionUID è un numero di versione dell'eccezione.

	private final String message = "Invalid Password";
	// Il messaggio che verrà restituito quando questa eccezione verrà lanciata.

	// Metodo per ottenere il messaggio di errore associato a questa eccezione.
	public String getMessage() {
		return message;
	}
}
