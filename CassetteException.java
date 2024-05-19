public class CassetteException extends RuntimeException {
    /**
     * Constructor that initialises a custom exception
     * @param errorMessage message displayed when error is thrown
     */
    public CassetteException(String errorMessage) {
        super(errorMessage);
    }
}