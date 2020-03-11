package domain;

public abstract class BlackJackPlayer {
    protected final CardPossessor cardsOnHand;

    public BlackJackPlayer(CardPossessor cards) {
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
}
