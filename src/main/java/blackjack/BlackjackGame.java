package blackjack;

public class BlackjackGame {

    public void start() {
        Deck deck = Deck.create();
        Dealer dealer = new Dealer(deck.draw(), deck.draw());
    }
}
