package team.blackjack.config;

import team.blackjack.control.BlackJackController;
import team.blackjack.service.BlackjackService;

public class AppConfig {
    private static final AppConfig instance = new AppConfig();
    private final BlackjackService blackJackService = new BlackjackService();
    private final BlackJackController blackJackController = new BlackJackController(blackJackService);

    private AppConfig() {}

    public static AppConfig getInstance() {
        return instance;
    }

    public BlackJackController blackJackController() {
        return blackJackController;
    }
}
