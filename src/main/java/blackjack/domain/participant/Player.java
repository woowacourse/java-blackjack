package blackjack.domain.participant;

import blackjack.domain.card.Deck;

public class Player extends Participant {

    private BettingAmount bettingAmount;

    private Player(final String name, final Deck deck) {
        super(name, deck);
    }

    public static Player readyToPlay(final String name, final Deck deck) {
        return new Player(name, deck);
    }

    public void betAmount(final int amount) {
        this.bettingAmount = new BettingAmount(amount);
    }

    @Override
    public boolean isPossibleToDrawCard() {
        return isNotBlackjack() && isNotBust();
    }

    private boolean isNotBlackjack() {
        return !isBlackjack();
    }

    private boolean isNotBust() {
        return !isBust();
    }

    public int getBettingAmount() {
        return bettingAmount.getAmount();
    }

}
