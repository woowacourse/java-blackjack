package domain.gamer;

import domain.card.Deck;
import domain.card.PlayingCards;

public class Dealer extends Player {
    private static final int ADD_THRESHOLD = 17;
    private static final String DEALER_NAME = "딜러";
    private static final int INIT_DEALER_MONEY = 0;

    public Dealer(PlayingCards playingCards) {
        super(DEALER_NAME, INIT_DEALER_MONEY, playingCards);
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
