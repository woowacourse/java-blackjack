package blackjack.domain.game;

import static blackjack.domain.game.PlayRecord.*;

import blackjack.domain.Name;
import blackjack.domain.state.Bet;

public final class Player extends Participant {

    private final Bet bet;

    Player(Name name, Bet bet) {
        super(name);
        this.bet = bet;
    }

    public long getRevenue(Dealer dealer) {
        return getState().revenue(getRecord(dealer), bet);
    }

    @Override
    public boolean isDrawable() {
        return getState().isDrawable();
    }

    public PlayRecord getRecord(Dealer dealer) {
        if (isPlayerLoss(dealer)) {
            return LOSS;
        }

        if (getScore() == dealer.getScore()) {
            return PUSH;
        }

        if (isBlackjack()) {
            return BLACKJACK;
        }

        return WIN;
    }

    private boolean isPlayerLoss(Dealer dealer) {
        return isBust() || (!dealer.isBust() && isLowerScoreThan(dealer))
            || (dealer.isBlackjack() && !isBlackjack());
    }
}
