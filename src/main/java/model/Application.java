package model;

import model.controller.BlackjackGameController;
import model.view.InputView;

public class Application {
    public static void main(String[] args) {
        BlackjackGameController blackjackGameController = new BlackjackGameController(new InputView());
        blackjackGameController.run();
    }
}
