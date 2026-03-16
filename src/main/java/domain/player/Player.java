package domain.player;

import domain.card.HandCard;
import domain.deck.Deck;

public abstract class Player {
    protected static final int BLACKJACK_MAX_LIMIT = 21;
    private static final int INITIAL_CARD_COUNT = 2;

    protected final HandCard handCard;

    protected Player() {
        this.handCard = new HandCard();
    }

    public void deal(Deck deck) {
        handCard.addCard(deck.deal());
    }

    public int score() {
        return handCard.cardCalculate();
    }

    public boolean isBust() {
        return score() > BLACKJACK_MAX_LIMIT;
    }

    public boolean isBlackJack() {
        return handCard.getCards().size() == INITIAL_CARD_COUNT && score() == BLACKJACK_MAX_LIMIT;
    }
}
