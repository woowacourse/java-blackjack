package blackjack.domain.participant;

import blackjack.domain.MatchResult;

public class Player extends Participant {

    private MatchResult matchResult;

    public Player(String name) {
        this.name = new Name(name);
    }

    public MatchResult getResult() {
        return matchResult;
    }
}
