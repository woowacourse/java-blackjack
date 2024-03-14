package blackjack;

public class BlackJackApplication {

    private BlackJackApplication() {
    }

    public static void main(String[] args) {
        BlackJackGame blackJackGame = new BlackJackGame();
        blackJackGame.run();
    }
}
