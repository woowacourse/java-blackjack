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

    public boolean isBust() {
        return getTotalPoint().isBust();
    }

    public boolean isBlackjack() {
        return getTotalPoint().isBlackjack() && holdingCards.isInitialSize();
    }

    public boolean moreScoreThan(final Player targetPlayer) {
        Score targetScore = targetPlayer.getTotalPoint();
        return getTotalPoint().isBiggerThan(targetScore);
    }

    public boolean isSameScore(final Player targetPlayer) {
        Score targetScore = targetPlayer.getTotalPoint();
        return getTotalPoint().isSameScore(targetScore);
    }

    public Score getTotalPoint() {
        return holdingCards.getSum();
    }

    public abstract Boolean canPick();

    public abstract Boolean isDealer();

    public abstract Boolean isChallenger();

    public abstract String getName();
}
