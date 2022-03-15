package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.domain.game.BlackjackGame;

public class Application {

    public static void main(String[] args) {
        BlackjackController blackjackController = new BlackjackController();
        BlackjackGame blackjackGame = blackjackController.initializeGame();

        blackjackController.printInitialHand(blackjackGame);
        blackjackController.giveCardsToAllPlayer(blackjackGame);
        blackjackController.giveExtraCardToDealer(blackjackGame);
        blackjackController.printFinalHandAndScore(blackjackGame);
        blackjackController.printDealerMatchDto(blackjackGame);
        blackjackController.printPlayersMatchDto(blackjackGame);
    }
}
