package blackjack.domain.participant;

import static blackjack.constant.CommonConstant.BLACKJACK_SYMBOL_NUMBER;

import blackjack.constant.MatchResult;
import blackjack.domain.BettingAmount;

public class Player extends Participant {

    private BettingAmount bettingAmount;

    public Player(String name) {
        super(new Name(name));
    }

    public void placeBet(int bettingAmount) {
        this.bettingAmount = new BettingAmount(bettingAmount);
    }

    public boolean canReceive() {
        return getScore() < BLACKJACK_SYMBOL_NUMBER;
    }

    public MatchResult match(Dealer other) {
        return hand.compareMatchResult(other.hand);
    }

    public int bettingAmount() {
        return bettingAmount.getValue();
    }
}
