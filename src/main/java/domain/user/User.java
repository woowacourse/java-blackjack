package domain.user;

import domain.CardDeck;
import domain.TrumpCard;
import java.util.List;

public abstract class User {
    protected final CardDeck cardDeck;

    public User() {
        this.cardDeck = new CardDeck();
    }

    public abstract boolean isImpossibleDraw();

    public abstract List<TrumpCard> openCard();

    public abstract List<TrumpCard> openAllCard();

    public abstract String getName();

    public abstract boolean isDealer();
  
    public void receiveCard(final TrumpCard trumpCard) {
        cardDeck.addTrumpCard(trumpCard);
    }

    public boolean hasName(String name) {
        return getName().equals(name);
    }

    public int userScore() {
        return this.cardDeck.calculateScore();

    }

    public CardDeck getCardDeck() {
        return this.cardDeck;
    }

    public boolean isBurst() {
        return this.cardDeck.checkOverScore();
    }
}
