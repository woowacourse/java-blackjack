package domain.player;

import domain.CardPossessor;
import domain.CardProvider;

public abstract class BlackJackPlayer {
    protected final String name;
    protected final CardPossessor cardsOnHand;

    public BlackJackPlayer(String name, CardPossessor cards) {
        this.name = name;
        this.cardsOnHand = cards;
    }

    public boolean isWinTo(BlackJackPlayer counterParts) {
        return this.calculateScore() > counterParts.calculateScore();
    }

    public boolean isDrawTo(BlackJackPlayer counterParts) {
        return this.calculateScore() == counterParts.calculateScore();
    }

    public void drawCard(CardProvider cardProvider) {
        cardsOnHand.drawCard(cardProvider);
    }

    public int calculateScore() {
        return cardsOnHand.calculateScore();
    }

    public abstract boolean canDrawMore();

    public CardPossessor getCardsOnHand() {
        return cardsOnHand;
    }
}
