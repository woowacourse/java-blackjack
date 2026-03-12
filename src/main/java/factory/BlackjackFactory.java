package factory;

import controller.BlackjackController;
import domain.game.BlackjackGameManager;
import view.InputView;
import view.OutputView;

public class BlackjackFactory {

    public BlackjackController blackjackController() {
        return new BlackjackController(inputView(), outputView(), blackjackService());
    }

    public BlackjackGameManager blackjackService() {
        return new BlackjackGameManager();
    }

    private InputView inputView() {
        return new InputView();
    }

    private OutputView outputView() {
        return new OutputView();
    }
}
