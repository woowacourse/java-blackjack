package blackjack;

import java.util.List;

public class BlackjackProcessManager {
    private final Deck deck;

    public BlackjackProcessManager(Deck deck) {
        this.deck = deck;
    }

    public void giveCardTo(Hand hand) {
        List<Card> cards = deck.takeCards(2);
        cards.forEach(hand::takeCard);
    }
}
