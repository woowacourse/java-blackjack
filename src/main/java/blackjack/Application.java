package blackjack;

import blackjack.controller.BlackJackController;
import blackjack.dto.CurrentTurnParticipant;
import blackjack.view.input.DrawCommand;
import blackjack.view.input.InputView;
import blackjack.view.output.OutputView;
import java.util.List;

public class Application {

    public static void main(final String[] args) {
        final List<String> playerNames = InputView.inputPlayerNames();
        final BlackJackController blackJackController = new BlackJackController(playerNames);
        OutputView.showGameInitInfo(blackJackController.getInitDealerInfo(), blackJackController.getInitPlayerInfo());

        runPlayerTurn(blackJackController);
        runDealerTurn(blackJackController);

        OutputView.printResultPlayerInfos(blackJackController.getPlayerResultInfos());
        OutputView.printAllOutcomeResult(blackJackController.getWinningResult());
    }

    private static void runDealerTurn(final BlackJackController blackJackController) {
        while (!blackJackController.isDealerTurnEnd()) {
            blackJackController.drawDealer();
            OutputView.printDealerDraw();
        }
        blackJackController.stayDealer();
    }

    private static void runPlayerTurn(final BlackJackController blackJackController) {
        while (!blackJackController.isAllPlayersEnd()) {
            final String command = InputView.inputDrawCommand(blackJackController.getCurrentTurnPlayerInfo());
            final DrawCommand drawCommand = DrawCommand.from(command);
            final CurrentTurnParticipant currentCurrentTurnParticipant = checkCurrentPlayerDrawNew(blackJackController, drawCommand);
            OutputView.printPlayerCardInfo(currentCurrentTurnParticipant);
        }
    }

    private static CurrentTurnParticipant checkCurrentPlayerDrawNew(final BlackJackController blackJackController, final DrawCommand command) {
        if (command.isNo()) {
            return blackJackController.drawNextPlayer();
        }
        return blackJackController.drawCurrentPlayer();
    }
}
