package domain.participant;

import domain.blackjack.BetAmount;

public class Player extends Participant {

    private static final int BLACK_JACK_COUNT = 21;
    private static final double BLACKJACK_PROFIT_RATE = 1.5;

    private final BetAmount betAmount;

    public Player(Name name) {
        super(name);
        this.betAmount = new BetAmount(1000);
    }

    public Player(Name name, BetAmount betAmount) {
        super(name);
        this.betAmount = betAmount;
    }

    public double getPayout(Dealer dealer) {
        double payout = betAmount.calculateProfit(dealer.calculatePlayerWinStatus(this));
        if (isBlackJack() && dealer.isNotBlackJack()) {
            return payout * BLACKJACK_PROFIT_RATE;
        }
        return payout;
    }

    @Override
    boolean canHit() {
        return hands.calculateScore() <= BLACK_JACK_COUNT;
    }
}
