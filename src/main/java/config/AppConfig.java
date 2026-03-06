package config;

import controller.BlackJackController;
import service.BlackJackInitService;
import service.BlackJackResultService;
import service.BlackJackTurnService;

public class AppConfig {
    public BlackJackInitService blackJackInitService() {
        return new BlackJackInitService();
    }

    public BlackJackTurnService blackJackTurnService() {
        return new BlackJackTurnService();
    }

    public BlackJackResultService blackJackResultService() {
        return new BlackJackResultService();
    }

    public BlackJackController blackJackController() {
        return new BlackJackController(
                blackJackInitService(),
                blackJackTurnService(),
                blackJackResultService());
    }
}
