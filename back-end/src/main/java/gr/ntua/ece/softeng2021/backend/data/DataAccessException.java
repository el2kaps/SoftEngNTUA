package gr.ntua.ece.softeng2021.backend.data;
public class DataAccessException extends Exception {

    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}