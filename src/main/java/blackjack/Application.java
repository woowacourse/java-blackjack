package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.domain.GameMachine;

public class Application {

    public static void main(String[] args) {
        BlackjackController blackjackController = new BlackjackController();
        GameMachine gameMachine = blackjackController.createGameMachine();
        blackjackController.play(gameMachine);
    }
}
