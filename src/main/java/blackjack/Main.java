package blackjack;

import blackjack.controller.BlackJackController;
import blackjack.controller.ControllerFactory;
import blackjack.view.InputView;

public class Main {
    public static void main(String[] args) {
        String selectedGame = InputView.enterGame();
        BlackJackController controller = ControllerFactory.createByIdentifier(selectedGame);
        controller.play();
    }
}
