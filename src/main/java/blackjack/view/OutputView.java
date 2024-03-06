package blackjack.view;

public class OutputView {
    private static final String ERROR_PREFIX = "[ERROR] ";

    public void printException(IllegalArgumentException e) {
        System.out.println(ERROR_PREFIX + e.getMessage());
    }
}
