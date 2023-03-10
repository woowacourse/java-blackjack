package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;

public abstract class Player {
    protected static final int BLACKJACK_POINT = 21;

    protected final Hand hand;
    protected Status status;

    protected Player() {
        this.hand = new Hand();
        this.status = Status.NORMAL;
    }

    public void pickStartCards(Card firstCard, Card secondCard) {
        hand.initialize(firstCard, secondCard);
        updateStatus();
    }

    public void pickCard(Card card) {
        hand.add(card);
        updateStatus();
    }

    private void updateStatus() {
        if (hand.getSum() == BLACKJACK_POINT) {
            status = Status.BLACKJACK;
            return;
        }
        if (hand.getSum() > BLACKJACK_POINT) {
            status = Status.BUST;
            return;
        }
        this.status = Status.NORMAL;
    }

    public int getTotalPoint() {
        return hand.getSum();
    }

    public boolean isBust() {
        if (status == Status.BUST) {
            return true;
        }
        return false;
    }

    public boolean isBlackJack() {
        if (status == Status.BLACKJACK) {
            return true;
        }
        return false;
    }

    public Hand getHoldingCards() {
        return hand;
    }

    public abstract Boolean canPick();

    public abstract String getName();

    public abstract boolean isDealer();
}
