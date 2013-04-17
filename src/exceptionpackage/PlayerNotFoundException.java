package exceptionpackage;

@SuppressWarnings("serial")
public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException() { super(); }
    public PlayerNotFoundException(String s) { super(s); }
}
