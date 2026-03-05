package domain.player;

import domain.card.HandCard;
import domain.deck.CardDeck;

public abstract class Player {
    protected final HandCard handCard;

    protected Player() {
        this.handCard = new HandCard();
    }

    public void deal(CardDeck cardDeck) {
        handCard.addCard(cardDeck.deal());
    }

    public int score() {
        return handCard.cardCalculator();
    }

    public int adjustBustScore(int score) {
        if (score > 21) {
            return 0;
        }
        return score;
    }
}

