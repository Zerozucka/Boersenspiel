package exceptionpackage;

@SuppressWarnings("serial")
public class NotFoundException extends RuntimeException {
    public NotFoundException() { super(); }
    public NotFoundException(String s) { super(s); }
}
