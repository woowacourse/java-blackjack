package domain.player;

import domain.card.HandCard;
import domain.deck.CardDeck;

public abstract class Player {
    protected static final int BLACKJACK_MAX_LIMIT = 21;
    protected final String name;
    protected final HandCard handCard;

    protected Player(String name) {
        this.name = name;
        this.handCard = new HandCard();
    }

    public String getName() {
        return name;
    }

    public void deal(CardDeck cardDeck) {
        handCard.addCard(cardDeck.deal());
    }

    public int score() {
        return handCard.cardCalculator();
    }

    public boolean isBust() {
        return score() > BLACKJACK_MAX_LIMIT;
    }
}

