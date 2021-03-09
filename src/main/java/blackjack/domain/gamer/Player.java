package blackjack.domain.gamer;

import blackjack.domain.MatchResult;

public class Player extends Participants {

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean canDraw() {
        return this.getTakenCards().calculateScore() < MatchResult.MAX_SCORE;
    }
}
