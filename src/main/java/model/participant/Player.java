package model.participant;

public class Player extends Participant {
    private boolean isTurnOver;

    public Player(Name name) {
        super(name);
        isTurnOver = false;
    }

    public boolean isInitBlackjack() {
        return cardDeck.isBlackJack() && cardDeck.cardSize() == 2;
    }

    @Override
    public boolean canHit() {
        return !cardDeck.isBust() && !isTurnOver;
    }

    public void turnOver() {
        isTurnOver = true;
    }
}
