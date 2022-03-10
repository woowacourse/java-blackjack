package blackjack;

import blackjack.domain.game.BlackJackGame;
import blackjack.dto.PlayerInfo;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class Application {

    public static void main(final String[] args) {
        final List<String> playerNames = InputView.inputPlayerNames();
        final BlackJackGame blackJackGame = BlackJackGame.init(playerNames);
        OutputView.showGameInitInfo(blackJackGame.getInitDealerInfo(), blackJackGame.getInitPlayerInfo());

        runPlayerTurn(blackJackGame);
        runDealerTurn(blackJackGame);

        OutputView.printResultPlayerInfos(blackJackGame.getPlayerResultInfos());
        OutputView.printAllOutcomeResult(blackJackGame.calculateAllResults());
    }

    private static void runDealerTurn(final BlackJackGame blackJackGame) {
        while (!blackJackGame.isDealerTurnEnd()) {
            blackJackGame.drawDealer();
            OutputView.printDealerDraw();
        }
        blackJackGame.stayDealer();
    }

    private static void runPlayerTurn(final BlackJackGame blackJackGame) {
        while (!blackJackGame.isPlayersTurnEnd()) {
            final String command = InputView.inputDrawCommand(blackJackGame.getCurrentTurnPlayerInfo());
            final PlayerInfo currentPlayerInfo = blackJackGame.drawCurrentPlayer(command);
            OutputView.printPlayerCardInfo(currentPlayerInfo);
        }
    }
}
