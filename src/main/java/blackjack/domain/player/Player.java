package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.HoldingCards;

public abstract class Player {
    protected static final int BLACKJACK_POINT = 21;

    protected final HoldingCards holdingCards;
    protected Status status;

    protected Player() {
        this.holdingCards = new HoldingCards();
        this.status = Status.NORMAL;
    }

    public void pickStartCards(Card firstCard, Card secondCard) {
        holdingCards.initialize(firstCard, secondCard);
        updateStatus();
    }

    public void pickCard(Card card) {
        holdingCards.add(card);
        updateStatus();
    }

    private void updateStatus() {
        if (holdingCards.getSum() == BLACKJACK_POINT) {
            status = Status.BLACKJACK;
            return;
        }
        if (holdingCards.getSum() > BLACKJACK_POINT) {
            status = Status.BUST;
            return;
        }
        this.status = Status.NORMAL;
    }

    public int getTotalPoint() {
        return holdingCards.getSum();
    }

    public boolean isBust() {
        return status == Status.BUST;
    }

    public boolean isBlackJack() {
        return status == Status.BLACKJACK;
    }

    public HoldingCards getHoldingCards() {
        return holdingCards;
    }

    public abstract Boolean canPick();

    public abstract String getName();

    public abstract boolean isDealer();
}
