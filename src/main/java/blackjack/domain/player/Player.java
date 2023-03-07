package blackjack.domain.player;

import blackjack.domain.card.Card;

public abstract class Player {
    protected static final int BLACKJACK_POINT = 21;

    protected final HoldingCards holdingCards;

    protected Player() {
        this.holdingCards = new HoldingCards();
    }

    public void pickStartCards(Card firstCard, Card secondCard) {
        holdingCards.initialize(firstCard, secondCard);
    }

    public void pickCard(Card card) {
        holdingCards.add(card);
    }

    public boolean isBust() {
        if (holdingCards.getSum() > BLACKJACK_POINT) {
            return true;
        }
        return false;
    }

    public int getTotalPoint() {
        return holdingCards.getSum();
    }

    public HoldingCards getHoldingCards() {
        return holdingCards;
    }

    public abstract Boolean canPick();

    public abstract String getName();

    public abstract boolean isDealer();
}
