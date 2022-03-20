package blackjack.domain.game;

import static blackjack.domain.game.PlayRecord.*;

import blackjack.domain.Name;
import blackjack.domain.state.Betting;

public final class Player extends Participant {

    private final Name name;
    private final Betting betting;

    Player(Name name, Betting betting) {
        this.name = name;
        this.betting = betting;
    }

    @Override
    public Name getName() {
        return name;
    }

    public long getRevenue(Dealer dealer) {
        return getState().revenue(getRecord(dealer), betting);
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
