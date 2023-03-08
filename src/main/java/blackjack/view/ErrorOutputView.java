package blackjack.view;

public class ErrorOutputView {

    private static final String EXCEPTION_SIGN = "[ERROR] ";

    private ErrorOutputView() {
    }

    public static void printError(RuntimeException exception) {
        System.out.println(EXCEPTION_SIGN + exception.getMessage());
    }
}
