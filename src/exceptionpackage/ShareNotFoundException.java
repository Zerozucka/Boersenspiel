package exceptionpackage;

@SuppressWarnings("serial")
public class ShareNotFoundException extends RuntimeException {
    public ShareNotFoundException() { super(); }
    public ShareNotFoundException(String s) { super(s); }
}
