public class InvalidPasswordException extends RuntimeException {
    /**
     * Constructor that initialises a custom exception
     * @param errorMessage message displayed when error is thrown
     */
    public InvalidPasswordException(String errorMessage) {
        super(errorMessage);
    }
}