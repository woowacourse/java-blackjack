package team.blackjack.config;

import team.blackjack.controler.BlackJackController;
import team.blackjack.service.BlackJackService;

public class AppConfig {
    private static final AppConfig instance = new AppConfig();
    private final BlackJackService blackJackService = new BlackJackService();
    private final BlackJackController blackJackController = new BlackJackController(blackJackService);

    private AppConfig() {}

    public static AppConfig getInstance() {
        return instance;
    }

    public BlackJackController blackJackController() {
        return blackJackController;
    }
}
