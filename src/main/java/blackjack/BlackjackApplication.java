package blackjack;

import blackjack.domain.card.Deck;

public class BlackjackApplication {
    public static void main(String[] args) {
        BlackjackGame blackjackGame = new BlackjackGame(new Deck());
        blackjackGame.run();
    }
}
