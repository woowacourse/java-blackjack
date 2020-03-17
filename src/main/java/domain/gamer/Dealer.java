package domain.gamer;

import domain.card.Deck;
import domain.card.PlayingCards;

public class Dealer extends Player {
    private static final int ADD_THRESHOLD = 17;
    private static final String DEALER_NAME = "딜러";

    public Dealer(PlayingCards playingCards) {
        super(playingCards, DEALER_NAME);

    }

    public void receiveDealerCard(Deck deck) {
        if (canGetExtraCard()) {
            addCard(deck);
        }
    }

    public boolean canGetExtraCard() {
        return playingCards.calculateScore() < ADD_THRESHOLD;
    }
}
