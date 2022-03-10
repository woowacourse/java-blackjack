package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.domain.GameMachine;
import blackjack.view.InputView;

public class Application {

    public static void main(String[] args) {
        BlackjackController blackjackController = new BlackjackController();
        GameMachine gameMachine = new GameMachine(InputView.inputNames());
        blackjackController.play(gameMachine);
    }
}
