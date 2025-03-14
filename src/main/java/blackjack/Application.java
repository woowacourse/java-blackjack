package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.view.GamePlayView;
import blackjack.view.GameResultView;
import blackjack.view.GameSetupView;

public class Application {

    public static void main(String[] args) {
        createController().run();
    }

    private static BlackjackController createController() {
        return new BlackjackController(new GameSetupView(), new GamePlayView(), new GameResultView());
    }
}
