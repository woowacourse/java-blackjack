package factory;

import controller.BlackjackController;
import domain.game.BlackjackGameManager;
import view.InputView;
import view.OutputView;

public class BlackjackControllerFactory {

    public BlackjackController blackjackController() {
        return new BlackjackController(inputView(), outputView(), blackjackGameManager());
    }

    public BlackjackGameManager blackjackGameManager() {
        return new BlackjackGameManager();
    }

    private InputView inputView() {
        return new InputView();
    }

    private OutputView outputView() {
        return new OutputView();
    }
}
