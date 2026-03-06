package blackjack.config;

import blackjack.controller.BlackjackController;
import blackjack.domain.ShuffleStrategy;
import blackjack.service.BlackjackGame;
import blackjack.service.RandomShuffleStrategy;

public class AppConfig {
    public BlackjackController blackjackController() {
        return new BlackjackController(blackjackGame());
    }

    public BlackjackGame blackjackGame() {
        return new BlackjackGame();
    }

    public ShuffleStrategy shuffleStrategy() {
        return new RandomShuffleStrategy();
    }
}
