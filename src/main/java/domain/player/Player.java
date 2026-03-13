package domain.player;

import domain.card.HandCard;
import domain.deck.CardDeck;
import java.util.List;

public abstract class Player {

    protected final HandCard handCard;

    protected Player() {
        this.handCard = new HandCard();
    }

    public void deal(CardDeck cardDeck) {
        handCard.addCard(cardDeck.deal());
    }

    public int score() {
        return handCard.score();
    }

    public boolean isBust() {
        return handCard.isBust();
    }

    public List<String> cards() {
        return handCard.cards();
    }

    public boolean isBlackJack() {
        return handCard.isBlackJack();
    }

    protected abstract int getOpenCardCount();

    public List<String> getOpenCards() {
        return handCard.getOpenCards(getOpenCardCount());
    }

    public abstract String getName();

}

