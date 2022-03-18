package blackjack;

import blackjack.domain.game.BlackjackGame;
import blackjack.domain.participant.Participant;
import blackjack.view.input.DrawCommand;
import blackjack.view.input.InputView;
import blackjack.view.output.OutputView;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackGameRunner {

    public void run() {
        final List<String> playerNames = InputView.inputPlayerNames();
        final Map<String, Integer> bettingMoneysByName = inputBettingMoney(playerNames);
        final BlackjackGame blackJackGame = new BlackjackGame(bettingMoneysByName);
        OutputView.showGameInitInfo(blackJackGame.getParticipants());

        runPlayerTurn(blackJackGame);
        runDealerTurn(blackJackGame);

        OutputView.printResultPlayerInfos(blackJackGame.getParticipants());
        OutputView.printAllOutcomeResult(blackJackGame.getParticipantsProfit());
    }

    private Map<String, Integer> inputBettingMoney(final List<String> names) {
        return names.stream()
                .collect(Collectors.toMap(name -> name, InputView::inputBettingMoney));
    }

    private void runDealerTurn(final BlackjackGame blackjackGame) {
        while (!blackjackGame.isDealerTurnEnd()) {
            blackjackGame.drawDealer();
            OutputView.printDealerDraw();
        }
        blackjackGame.stayDealer();
    }

    private void runPlayerTurn(final BlackjackGame blackjackGame) {
        while (!blackjackGame.isAllPlayersEnd()) {
            final String command = InputView.inputDrawCommand(blackjackGame.getCurrentTurnPlayer());
            final DrawCommand drawCommand = DrawCommand.from(command);
            final Participant currentTurnParticipant = checkCurrentPlayerDrawNew(blackjackGame, drawCommand);
            OutputView.printPlayerCardInfo(currentTurnParticipant);
        }
    }

    private Participant checkCurrentPlayerDrawNew(
            final BlackjackGame blackjackGame, final DrawCommand command) {
        if (command.isNo()) {
            return blackjackGame.drawNextPlayer();
        }
        return blackjackGame.drawCurrentPlayer();
    }
}
