package model;

import model.controller.BlackjackGameController;
import model.view.InputView;
import model.view.OutputView;

public class Application {
    public static void main(String[] args) {
        BlackjackGameController blackjackGameController =
                new BlackjackGameController(new InputView(), new OutputView());
        blackjackGameController.run();
    }
}
