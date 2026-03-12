package blackjack;

public class BlackjackApplication {

    public static void main(final String[] args) {
        BlackjackGame blackjackGame = BlackjackGameFactory.create();
        blackjackGame.start();
    }
}
