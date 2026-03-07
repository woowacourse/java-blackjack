package team.blackjack.config;

import team.blackjack.controller.BlackJackController;
import team.blackjack.service.BlackJackService;

public class AppConfig {
    private static volatile AppConfig instance;

    private AppConfig() {
    }

    public static AppConfig getInstance() {
        if (instance == null) {
            synchronized (AppConfig.class) {
                if (instance == null) {
                    instance = new AppConfig();
                }
            }
        }

        return instance;
    }

    public BlackJackService blackJackService() {
        return new BlackJackService();
    }

    public BlackJackController blackJackController() {
        return new BlackJackController(blackJackService());
    }
}
