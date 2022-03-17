package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.domain.betting.PlayerBettings;
import blackjack.domain.game.BlackjackGame;

public class Application {

    private static final BlackjackController controller = new BlackjackController();

    public static void main(String[] args) {
        final BlackjackGame game = controller.initializeGame();
        final PlayerBettings bettings = controller.initializeBettings(game);

        controller.playGame(game);
        controller.showGameResult(game);
    }
}
