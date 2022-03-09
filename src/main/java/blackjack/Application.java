package blackjack;

import blackjack.domain.BlackJackGame;
import blackjack.dto.PlayerInfo;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class Application {

    public static void main(final String[] args) {
        final List<String> playerNames = InputView.inputPlayerNames();

        final BlackJackGame blackJackGame = BlackJackGame.init(playerNames);
        OutputView.showGameInitInfo(blackJackGame.getInitDealerInfo(), blackJackGame.getInitPlayerInfo());

        while (!blackJackGame.isPlayersTurnEnd()) {
            final String command = InputView.inputDrawCommand(blackJackGame.getCurrentTurnPlayerInfo());
            final PlayerInfo currentPlayerInfo = blackJackGame.drawCurrentPlayer(command);
            OutputView.printPlayerCardInfo(currentPlayerInfo);
        }

        while (!blackJackGame.isDealerTurnEnd()) {
            blackJackGame.drawDealer();
            OutputView.printDealerDraw();
        }

        OutputView.printResultPlayerInfos(blackJackGame.getPlayerResultInfos());

    }
}
