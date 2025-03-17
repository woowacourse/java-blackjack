package view;

public class Error {
    private static final String ERROR_MESSAGE = "[ERROR] %s";

    public static String formatMessage(String message) {
        return String.format(ERROR_MESSAGE, message);
    }
}
