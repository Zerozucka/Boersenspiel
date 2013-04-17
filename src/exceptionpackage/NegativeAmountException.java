package exceptionpackage;

@SuppressWarnings("serial")
public class NegativeAmountException extends RuntimeException {
    public NegativeAmountException() { super(); };

    public NegativeAmountException(String s) { super(s); };
}