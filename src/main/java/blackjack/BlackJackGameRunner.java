package blackjack;

import blackjack.dto.CurrentTurnParticipant;
import blackjack.view.input.DrawCommand;
import blackjack.view.input.InputView;
import blackjack.view.output.OutputView;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackGameRunner {

    public void run() {
        final List<String> playerNames = InputView.inputPlayerNames();
        final Map<String, String> battingMoneysByName = inputBattingMoney(playerNames);
        final GameCommunicator gameCommunicator = new GameCommunicator(battingMoneysByName);
        OutputView.showGameInitInfo(gameCommunicator.getInitDealerInfo(), gameCommunicator.getInitPlayerInfo());

        runPlayerTurn(gameCommunicator);
        runDealerTurn(gameCommunicator);

        OutputView.printResultPlayerInfos(gameCommunicator.getPlayerResultInfos());
        OutputView.printAllOutcomeResult(gameCommunicator.getParticipantsProfit());
    }

    private Map<String, String> inputBattingMoney(final List<String> names) {
        return names.stream()
                .collect(Collectors.toMap(name -> name, InputView::inputBattingMoney));
    }

    private void runDealerTurn(final GameCommunicator gameCommunicator) {
        while (!gameCommunicator.isDealerTurnEnd()) {
            gameCommunicator.drawDealer();
            OutputView.printDealerDraw();
        }
        gameCommunicator.stayDealer();
    }

    private void runPlayerTurn(final GameCommunicator gameCommunicator) {
        while (!gameCommunicator.isAllPlayersEnd()) {
            final String command = InputView.inputDrawCommand(gameCommunicator.getCurrentTurnPlayerInfo());
            final DrawCommand drawCommand = DrawCommand.from(command);
            final CurrentTurnParticipant currentCurrentTurnParticipant =
                    checkCurrentPlayerDrawNew(gameCommunicator, drawCommand);
            OutputView.printPlayerCardInfo(currentCurrentTurnParticipant);
        }
    }

    private CurrentTurnParticipant checkCurrentPlayerDrawNew(
            final GameCommunicator gameCommunicator, final DrawCommand command) {
        if (command.isNo()) {
            return gameCommunicator.drawNextPlayer();
        }
        return gameCommunicator.drawCurrentPlayer();
    }
}
