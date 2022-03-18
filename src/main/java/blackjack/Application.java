package blackjack;

import blackjack.controller.BlackjackController2;
import blackjack.domain.betting2.PlayerBettings;
import blackjack.domain.BlackjackGame2;

public class Application {

    private static final BlackjackController2 controller = new BlackjackController2();

    public static void main(String[] args) {
        final BlackjackGame2 game = controller.initializeGame();
        final PlayerBettings bettings = controller.initializeBettings(game);

        controller.playGame(game);
        controller.showBettingResults(game, bettings);
    }
}
