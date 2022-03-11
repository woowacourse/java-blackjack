package blackjack.domain.participant;

import blackjack.domain.MatchResult;

public class Player extends Participant {

    private MatchResult matchResult;

    public Player(String name) {
        this.name = new Name(name);
    }

    public void decideMatchResult(Dealer dealer) {
        matchResult =  cardHand.compareMatchResult(dealer.getCardHand());
    }

    public MatchResult getResult() {
        return matchResult;
    }
}
