package blackjack;

import blackjack.controller.Controller;
import blackjack.controller.ModeStrategy;
import blackjack.controller.ModeStrategyFactory;
import blackjack.view.InputView;

public class Main {
    public static void main(String[] args) {
        String selectedGame = InputView.enterGame();
        ModeStrategy gameMode = ModeStrategyFactory.createByIdentifier(selectedGame);
        Controller controller = new Controller(gameMode);
        controller.play();
    }
}
