package blackjack.domain.game;

import blackjack.domain.Name;
import blackjack.domain.state.Bet;

public final class Player extends Participant {

    private final Bet bet;

    Player(Name name, Bet bet) {
        super(name);
        this.bet = bet;
    }

    @Override
    public boolean isDrawable() {
        return getState().isDrawable();
    }

    long getRevenue(Dealer dealer) {
        return getState().revenue(getRecord(dealer), bet);
    }

    private PlayRecord getRecord(Dealer dealer) {
        return PlayRecord.of(isPlayerLoss(dealer), isSameScore(dealer), isBlackjack());
    }

    private boolean isPlayerLoss(Dealer dealer) {
        return isBust() || (!dealer.isBust() && isLowerScoreThan(dealer))
            || (dealer.isBlackjack() && !isBlackjack());
    }

    private boolean isSameScore(Dealer dealer) {
        return getScore() == dealer.getScore();
    }
}
