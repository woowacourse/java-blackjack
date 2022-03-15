package blackjack.view;

import blackjack.domain.game.Answer;
import blackjack.domain.game.Player;
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

    public static boolean isDrawing(final Player player) {
        try {
            OutputView.printDrawInstruction(player.getName());
            String input = InputView.inputDrawingAnswer(enterable);
            return Answer.isDraw(input);
        } catch (IllegalArgumentException exception) {
            OutputView.printExceptionMessage(exception.getMessage());
            return isDrawing(player);
        }
    }
}
