package blackjack.domain.participant;

import blackjack.dto.PlayerResultDto;

public class Player extends Participant {

    public Player(String name) {
        super(name);
    }

    public boolean isHittable() {
        return score < GOAL_SCORE;
    }

    public PlayerResultDto computeResult(int comparisonScore) {
        return new PlayerResultDto(name, isWin(comparisonScore));
    }

    private boolean isWin(int comparisonScore) {
        if (score > GOAL_SCORE) {
            return false;
        }
        if (comparisonScore > GOAL_SCORE) {
            return true;
        }
        return score >= comparisonScore;
    }
}
