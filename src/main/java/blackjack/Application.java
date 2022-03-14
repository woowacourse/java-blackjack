package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.domain.game.BlackjackGame;
import blackjack.view.InputView;

public class Application {

    public static void main(String[] args) {
        BlackjackController blackjackController = new BlackjackController();
        BlackjackGame blackjackGame = blackjackController.initializeGame(InputView.requestPlayerNamesInput());

        blackjackController.printInitialHand(blackjackGame);
        blackjackController.giveCardsToAllPlayer(blackjackGame);
        blackjackController.giveExtraCardToDealerIfPossible(blackjackGame);
        blackjackController.printFinalHandAndScore(blackjackGame);
        blackjackController.printDealerMatchDto(blackjackGame);
        blackjackController.printPlayersMatchDto(blackjackGame);
    }
}
