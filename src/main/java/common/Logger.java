package common;

public class Logger {

    private static final String ERROR_FORMAT = "[ERROR] %s";

    public static void error(final String message) {
        System.out.println(String.format(ERROR_FORMAT, message));
    }
}
