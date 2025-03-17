package blackjack;

import blackjack.game.BlackjackGame;
import blackjack.view.GameView;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackApplication {

    private final GameView gameView;

    private BlackjackApplication() {
        this.gameView = new GameView(new InputView(), new OutputView());
    }

    public static void main(String[] args) {
        BlackjackApplication application = new BlackjackApplication();
        application.start();
    }

    public void start() {
        BlackjackGame game = gameView.enterParticipants();
        gameView.distributeInitialCards(game);
        gameView.distributeAdditionalCards(game);
        gameView.showFinalCardsAndProfits(game);
    }
}
