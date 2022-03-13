package blackjack.domain.participant;

import blackjack.dto.PlayerResultDto;

public class Player extends Participant {

    public Player(String name) {
        super(name);
    }

    public boolean isAbleToHit() {
        return getScore() < GOAL_SCORE;
    }

    public PlayerResultDto computeResult(int comparisonScore) {
        return new PlayerResultDto(getName(), isWin(comparisonScore));
    }

    private boolean isWin(int comparisonScore) {
        if (getScore() > GOAL_SCORE) {
            return false;
        }
        if (comparisonScore > GOAL_SCORE) {
            return true;
        }
        return getScore() >= comparisonScore;
    }
}
