package blackjack;

import blackjack.domain.BlackJackGame;
import blackjack.domain.DrawCommand;
import blackjack.dto.PlayerCards;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class Application {

    public static void main(final String[] args) {
        final List<String> playerNames = InputView.inputPlayerNames();
        final BlackJackGame blackJackGame = BlackJackGame.init(playerNames);
        OutputView.showPlayersFirstCards(blackJackGame.getDealerFirstCard(), blackJackGame.getPlayersFirstCards());

        runPlayerTurn(blackJackGame);
        runDealerTurn(blackJackGame);

        OutputView.printPlayerScoreResults(blackJackGame.getPlayerScoreResults());
        OutputView.printAllOutcomeResult(blackJackGame.calculateAllResults());
    }

    private static void runPlayerTurn(final BlackJackGame blackJackGame) {
        while (!blackJackGame.isPlayersTurnEnd()) {
            final DrawCommand drawCommand = DrawCommand
                    .from(InputView.inputDrawCommand(blackJackGame.getCurrentTurnPlayerName()));
            final PlayerCards currentPlayerCards = blackJackGame.drawCurrentPlayer(drawCommand);
            OutputView.printPlayerCards(currentPlayerCards);
        }
    }

    private static void runDealerTurn(final BlackJackGame blackJackGame) {
        while (!blackJackGame.isDealerTurnEnd()) {
            blackJackGame.drawDealer();
            OutputView.printDealerDraw();
        }
    }
}
