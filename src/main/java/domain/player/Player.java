package domain.player;

import domain.card.Card;

public class Player extends Participant {

    private static final int BLACKJACK_NUM = 21;

    private final BettingHand bettingHand;

    private Player(Name name, BettingHand bettingHand) {
        super(name);
        this.bettingHand = bettingHand;
    }

    public static Player of(Name name, BettingHand bettingHand) {
        return new Player(name, bettingHand);
    }

    @Override
    protected Hand getHand() {
        return bettingHand.getHand();
    }

    public boolean canHit() {
        return !isBust() && !isBlackjack() && totalScore() < BLACKJACK_NUM;
    }

    public void hit(Card card) {
        bettingHand.addCard(card);
    }

    public int calculateProfit(Dealer dealer) {
        return bettingHand.calculateProfit(dealer);
    }
}
