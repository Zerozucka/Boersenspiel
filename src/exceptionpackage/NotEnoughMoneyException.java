package exceptionpackage;

public class NotEnoughMoneyException extends Exception {
    public NotEnoughMoneyException() { super(); };

    public NotEnoughMoneyException(String s) { super(s); };
}