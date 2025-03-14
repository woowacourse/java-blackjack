package blackjack.view;

public interface InputView {

    String read();

    default void printErrorMessage(final Exception exception) {
        System.out.println("[ERROR] " + exception.getMessage());
    }
}
