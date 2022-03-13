package blackjack;

import blackjack.controller.GameController;

public class Application {
    public static void main(String[] args) {
        final GameController gameController = new GameController();
        gameController.prepare();
        gameController.progressPlayerTurns();
        gameController.progressDealerTurn();
        gameController.endGame();
    }
}
