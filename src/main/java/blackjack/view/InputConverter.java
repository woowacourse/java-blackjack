package blackjack.view;

import blackjack.domain.game.Answer;
import blackjack.domain.game.Players;

public class InputConverter {

    private static final Enterable enterable = new Enter();

    private InputConverter() {
    }

    public static Players createPlayers() {
        try {
            return new Players(InputView.inputPlayerNames(enterable));
        } catch (IllegalArgumentException exception) {
            OutputView.printExceptionMessage(exception.getMessage());
            return createPlayers();
        }
    }

    public static String createBetting() {
        try {
            return InputView.inputBetting(enterable);
        } catch (IllegalArgumentException exception) {
            OutputView.printExceptionMessage(exception.getMessage());
            return createBetting();
        }
    }

    public static boolean isDrawing(final String name) {
        try {
            OutputView.printDrawInstruction(name);
            String input = InputView.inputDrawingAnswer(enterable);
            return Answer.isDraw(input);
        } catch (IllegalArgumentException exception) {
            OutputView.printExceptionMessage(exception.getMessage());
            return isDrawing(name);
        }
    }
}
