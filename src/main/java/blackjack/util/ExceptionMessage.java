package blackjack.util;

public class ExceptionMessage {

    private static final String FORMAT = "[ERROR] %s";

//    public static String makeMessage(final String input) {
//        return String.format(FORMAT, input);
//    }

    public static String makeMessage(final String input, final Object... args) {
        String message = String.format(input, args);
        return String.format(FORMAT, message);
    }
}
