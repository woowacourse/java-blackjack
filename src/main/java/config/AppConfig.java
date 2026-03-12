package config;

import controller.BlackJackController;
import service.BlackJackInitService;
import service.BlackJackTurnService;

public class AppConfig {
    public BlackJackInitService blackJackInitService() {
        return new BlackJackInitService();
    }

    public BlackJackTurnService blackJackTurnService() {
        return new BlackJackTurnService();
    }


    public BlackJackController blackJackController() {
        return new BlackJackController(
                blackJackInitService(),
                blackJackTurnService());
    }
}
