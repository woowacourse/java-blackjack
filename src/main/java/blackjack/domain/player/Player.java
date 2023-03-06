package blackjack.domain.player;

import blackjack.domain.card.Card;

public abstract class Player {

    protected final HoldingCards holdingCards;

    protected Player() {
        this.holdingCards = new HoldingCards();
    }

    public void pickStartCards(final Card firstCard, final Card secondCard) {
        holdingCards.initialCard(firstCard, secondCard);
    }

    public HoldingCards getHoldingCards() {
        return holdingCards;
    }

    public void pick(final Card card) {
        holdingCards.add(card);
    }

    public int getTotalPoint() {
        return holdingCards.getSum();
    }

    public abstract Boolean canPick();

    public abstract Boolean isDealer();

    public abstract Boolean isChallenger();

    public abstract String getName();
}
