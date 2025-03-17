package view;

public enum Error {
    ERROR_BASE_MESSAGE("[ERROR] %s");

    private final String errorMessage;

    Error(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static String formatMessage(String message) {
        return String.format(ERROR_BASE_MESSAGE.errorMessage, message);
    }
}
