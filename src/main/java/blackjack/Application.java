package blackjack;

import blackjack.controller.BlackJackGame;
import blackjack.service.BlackJackService;

public class Application {
    public static void main(String[] args) {
        BlackJackService blackJackService = new BlackJackService();
        BlackJackGame blackJackGame = new BlackJackGame(blackJackService);
        blackJackGame.start();
    }
}
