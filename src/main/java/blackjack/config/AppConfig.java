package blackjack.config;

import blackjack.controller.BlackjackController;
import blackjack.service.GameService;
import blackjack.view.InputView;

public class AppConfig {

    private BlackjackController controller;
    private GameService service;
    private InputView inputView;

    public AppConfig() {
    }

    public BlackjackController controller() {
        if (controller == null) {
            this.controller = new BlackjackController(service(), inputView());
        }
        return controller;
    }

    private GameService service() {
        if (service == null) {
            this.service = new GameService();
        }
        return service;
    }

    private InputView inputView() {
        if (inputView == null) {
            this.inputView = new InputView();
        }
        return inputView;
    }

}
