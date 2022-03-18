package blackjack;

import blackjack.domain.game.BlackJackGame;
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
        final BlackJackGame blackJackGame = new BlackJackGame(bettingMoneysByName);
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

    private void runDealerTurn(final BlackJackGame BlackJackGame) {
        while (!BlackJackGame.isDealerTurnEnd()) {
            BlackJackGame.drawDealer();
            OutputView.printDealerDraw();
        }
        BlackJackGame.stayDealer();
    }

    private void runPlayerTurn(final BlackJackGame BlackJackGame) {
        while (!BlackJackGame.isAllPlayersEnd()) {
            final String command = InputView.inputDrawCommand(BlackJackGame.getCurrentTurnPlayer());
            final DrawCommand drawCommand = DrawCommand.from(command);
            final Participant currentTurnParticipant = checkCurrentPlayerDrawNew(BlackJackGame, drawCommand);
            OutputView.printPlayerCardInfo(currentTurnParticipant);
        }
    }

    private Participant checkCurrentPlayerDrawNew(
            final BlackJackGame BlackJackGame, final DrawCommand command) {
        if (command.isNo()) {
            return BlackJackGame.drawNextPlayer();
        }
        return BlackJackGame.drawCurrentPlayer();
    }
}
