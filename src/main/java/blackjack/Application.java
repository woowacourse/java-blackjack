package blackjack;

import blackjack.domain.game.BlackJackGame;
import blackjack.dto.PlayerDto;
import blackjack.view.input.DrawCommand;
import blackjack.view.input.InputView;
import blackjack.view.output.OutputView;
import java.util.List;

public class Application {

    public static void main(final String[] args) {
        final List<String> playerNames = InputView.inputPlayerNames();
        final BlackJackGame blackJackGame = BlackJackGame.init(playerNames);
        OutputView.showGameInitInfo(blackJackGame.getInitDealerInfo(), blackJackGame.getInitPlayerInfo());

        runPlayerTurn(blackJackGame);
        runDealerTurn(blackJackGame);

        OutputView.printResultPlayerInfos(blackJackGame.getPlayerResultInfos());
        OutputView.printAllOutcomeResult(blackJackGame.getWinningResult());
    }

    private static void runDealerTurn(final BlackJackGame blackJackGame) {
        while (!blackJackGame.isDealerTurnEnd()) {
            blackJackGame.drawDealer();
            OutputView.printDealerDraw();
        }
        blackJackGame.stayDealer();
    }

    private static void runPlayerTurn(final BlackJackGame blackJackGame) {
        while (!blackJackGame.isAllPlayersEnd()) {
            final String command = InputView.inputDrawCommand(blackJackGame.getCurrentTurnPlayerInfo());
            final DrawCommand drawCommand = DrawCommand.from(command);
            final PlayerDto currentPlayerDto = checkCurrentPlayerDrawNew(blackJackGame, drawCommand);
            OutputView.printPlayerCardInfo(currentPlayerDto);
        }
    }

    private static PlayerDto checkCurrentPlayerDrawNew(final BlackJackGame blackJackGame, final DrawCommand command) {
        if (command.isNo()) {
            return blackJackGame.drawNextPlayer();
        }
        return blackJackGame.drawCurrentPlayer();
    }
}
