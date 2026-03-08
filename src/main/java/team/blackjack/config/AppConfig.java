package team.blackjack.config;

import team.blackjack.controller.BlackJackController;
import team.blackjack.domain.rule.BlackjackRule;
import team.blackjack.domain.rule.DefaultBlackjackRule;
import team.blackjack.service.BlackJackService;

public class AppConfig {
    private static final AppConfig INSTANCE = new AppConfig();

    private AppConfig() {
    }

    public static AppConfig getInstance() {
        return INSTANCE;
    }

    public BlackJackService blackJackService() {
        return new BlackJackService();
    }

    public BlackJackController blackJackController() {
        return new BlackJackController(blackJackService());
    }

    public BlackjackRule blackjackRule() {
        return new DefaultBlackjackRule();
    }
}
