package blackjack.domain.state;

import blackjack.domain.card.Hand;
import blackjack.domain.player.BetAmount;

public class Bust extends Finished {
    private static final int EARNING_RATE = -1;

    public Bust(Hand cards) {
        super(cards);
    }

    @Override
    public double profit(State state, BetAmount amount) {
        return amount.getAmount() * EARNING_RATE;
    }
}
