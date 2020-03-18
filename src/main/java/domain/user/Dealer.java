package domain.user;

import domain.card.Card;
import domain.card.Deck;
import domain.card.PlayingCards;

public class Dealer extends User {
    private static final int ADD_THRESHOLD = 17;
    private static final String NAME = "딜러";
    private Deck deck;

    public Dealer(PlayingCards playingCards, Deck deck) {
        super(playingCards, NAME);
        this.deck = deck;
    }

    public boolean canGetExtraCard() {
        return playingCards.calculateScore() < ADD_THRESHOLD;
    }
}
