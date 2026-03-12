package factory;

import controller.BlackjackController;
import service.BlackjackService;
import view.InputView;
import view.OutputView;

public class BlackjackFactory {

    public BlackjackController blackjackController() {
        return new BlackjackController(inputView(), outputView(), blackjackService());
    }

    public BlackjackService blackjackService() {
        return new BlackjackService();
    }

    private InputView inputView() {
        return new InputView();
    }

    private OutputView outputView() {
        return new OutputView();
    }
}
