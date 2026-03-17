package team.blackjack.config;

import team.blackjack.control.BlackJackController;
import team.blackjack.service.BlackjackJudge;
import team.blackjack.service.BlackjackService;
import team.blackjack.service.RevenueService;

public class AppConfig {
    private static final AppConfig instance = new AppConfig();
    private final BlackjackJudge blackJackJudge = new BlackjackJudge();
    private final BlackjackService blackJackService = new BlackjackService(blackJackJudge);
    private final RevenueService revenueService = new RevenueService();
    private final BlackJackController blackJackController = new BlackJackController(blackJackService, revenueService);

    private AppConfig() {}

    public static AppConfig getInstance() {
        return instance;
    }

    public BlackJackController blackJackController() {
        return blackJackController;
    }
}
