package blackjack.domain.participant;

import static blackjack.constant.CommonConstant.BLACKJACK_SYMBOL_NUMBER;

import blackjack.constant.MatchResult;

public class Player extends Participant {

    public Player(String name) {
        super(new Name(name));
    }

    public boolean canReceive() {
        return getScore() < BLACKJACK_SYMBOL_NUMBER;
    }

    public MatchResult match(Dealer other) {
        return hand.compareMatchResult(other.hand);
    }
}
