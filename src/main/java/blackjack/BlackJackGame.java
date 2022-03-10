package blackjack;

import blackjack.domain.BlackJackBoard;
import blackjack.domain.DrawCommand;
import blackjack.dto.PlayerCards;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackGame {

    private BlackJackGame() {
        throw new AssertionError();
    }

    public static void runPlayerTurn(final BlackJackBoard blackJackBoard) {
        if (blackJackBoard.isPlayersTurnEnd()) {
            return;
        }
        final DrawCommand drawCommand = inputDrawCommand(blackJackBoard);
        final PlayerCards currentPlayerCards = blackJackBoard.drawCurrentPlayer(drawCommand);
        OutputView.printPlayerCards(currentPlayerCards);
        runPlayerTurn(blackJackBoard);
    }

    private static DrawCommand inputDrawCommand(final BlackJackBoard blackJackBoard) {
        return DrawCommand.from(InputView.inputDrawCommand(blackJackBoard.getCurrentTurnPlayerName()));
    }

    public static void runDealerTurn(final BlackJackBoard blackJackBoard) {
        if (blackJackBoard.isDealerTurnEnd()) {
            return;
        }
        blackJackBoard.drawDealer();
        OutputView.printDealerDraw();
        runDealerTurn(blackJackBoard);
    }
}
