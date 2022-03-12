package blackjack.domain.participant;

import blackjack.dto.PlayerResultDTO;

public class Player extends Participant {

    public Player(String name) {
        super(name);
    }

    public boolean isAbleToHit() {
        return getScore() < GOAL_SCORE;
    }

    public PlayerResultDTO computeResult(int comparisonScore) {
        return new PlayerResultDTO(getName(), isWin(comparisonScore));
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
