package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class Application {
    private static final BlackjackController blackjackController = new BlackjackController();

    public static void main(String[] args) {
        BlackjackGame blackjackGame = blackjackController.initializeGame(InputView.requestPlayerNamesInput());
        OutputView.printInitialParticipantsCards(blackjackGame);

        List<Player> players = blackjackGame.getParticipants();
        for (Player player : players) {
            blackjackController.givePlayerCards(player, blackjackGame);
        }

        if (blackjackGame.giveCardToDealer()) {
            OutputView.printDealerExtraCardInfo();
        }

        OutputView.printAllCardsAndScore(blackjackGame);
        blackjackController.printDealerMatchDto(blackjackGame);
        blackjackController.printPlayersMatchDto(blackjackGame);
    }
}
