public class TheLordOfTheTetrisException extends RuntimeException {

    public TheLordOfTheTetrisException(String msg, Exception innerException) {
        super(msg, innerException);
    }
    public TheLordOfTheTetrisException(String msg) {
        super(msg);
    }
}