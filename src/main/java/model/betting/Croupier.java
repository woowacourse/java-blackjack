package model.betting;

import model.participant.role.BetOwnable;
import model.participant.role.Bettable;

public class Croupier {
    private final Bets bets;

    public Croupier() {
        this.bets = new Bets();
    }

    public void receiveBet(final Bet bet) {
        this.bets.add(bet);
    }

    public void updateBetOwnerFrom(final BetOwnable beforeOwner, final BetOwnable afterOwner) {
        bets.updateOwner(beforeOwner, afterOwner);
    }

    public void updateBetAmountWhenBlackJackOf(final Bettable better) {
        bets.updateBetAmount(better);
    }

    public int calculateRevenueByAllBetters() {
        return bets.calculateRevenueByAllBetters();
    }

    private Bet findBetByBetter(final Bettable better) {
        return bets.findByBetter(better);
    }

    public int calculateRevenueByBetter(Bettable better) {
        Bet bet = findBetByBetter(better);
        return bet.calculateBetterRevenue();
    }
}
