package domain;

import java.util.Optional;

public class Dealer {
    private static final int MINIMUM_TOTAL_SCORE = 16;
    
    private final Deck deck;
    private final String name = "딜러";

    public Dealer(Deck deck) {
        this.deck = deck;
    }

    public Optional<Card> addCardWhenSumBelowMinimum(Deck totalDeck) {
        if (deck.calculateCardScoreSum() <= MINIMUM_TOTAL_SCORE) {
            Card newCard = totalDeck.drawCard();
            deck.addCard(newCard);
            return Optional.of(newCard);
        }
        return Optional.empty();
    }
}
