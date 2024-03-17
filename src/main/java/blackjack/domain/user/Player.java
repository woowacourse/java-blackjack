package blackjack.domain.user;

import blackjack.domain.rule.Score;

public class Player extends User {

    public Player(UserName userName) {
        super(userName);
    }

    @Override
    public boolean canHit() {
        Score score = getHands().calculateScore();
        return score.isHit();
    }
}
