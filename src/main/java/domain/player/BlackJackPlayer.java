package domain.player;

import domain.CardPossessor;
import domain.CardProvider;

public abstract class BlackJackPlayer {
    protected final String name;
    protected final CardPossessor cardPossessor;

    public BlackJackPlayer(String name, CardPossessor cards) {
        this.name = name;
        this.cardPossessor = cards;
    }

    public boolean isWinTo(BlackJackPlayer counterParts) {
        return this.calculateScore() > counterParts.calculateScore();
    }

    public boolean isDrawTo(BlackJackPlayer counterParts) {
        return this.calculateScore() == counterParts.calculateScore();
    }

    public void drawCard(CardProvider cardProvider) {
        cardPossessor.drawCard(cardProvider);
    }

    public int calculateScore() {
        return cardPossessor.calculateScore();
    }

    public abstract boolean canDrawMore();

    public CardPossessor getCardsOnHand() {
        return cardPossessor;
    }
}
