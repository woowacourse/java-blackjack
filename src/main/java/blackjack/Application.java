package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.domain.cardpack.CardPack;
import blackjack.domain.cardpack.MasterShuffleStrategy;
import blackjack.domain.game.BlackjackGame;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Application {
    public static void main(String[] args) {
        BlackjackGame blackjackGame = new BlackjackGame(new CardPack(), new MasterShuffleStrategy());
        BlackjackController controller = new BlackjackController(new InputView(), new OutputView(), blackjackGame);
        controller.run();
    }
}
