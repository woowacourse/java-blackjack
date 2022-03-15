package blackjack.domain.participant;

import blackjack.domain.MatchResult;

public class Player extends Participant {

    private MatchResult matchResult;

    public Player(String name) {
        this.name = new Name(name);
    }

    public MatchResult decideMatchResult(Dealer dealer) {
        matchResult =  hand.compareMatchResult(dealer.getHand());
        return matchResult;
    }

    public MatchResult getResult() {
        return matchResult;
    }
}
