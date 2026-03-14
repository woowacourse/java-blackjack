package domain.member;

import domain.MatchResult;

public class Dealer extends Member {
    private static final int DEALER_DRAW_CONDITION = 16;

    public Dealer() {
        super(DEALER_NAME);
    }

    public boolean isMeetTheDrawCondition() {
        return currentScore() <= DEALER_DRAW_CONDITION;
    }

    @Override
    public MatchResult compareScoreWith(Member player) {
        int dealerScore = currentScore();
        int playerScore = player.currentScore();
        if (playerScore > BUST_CONDITION) {
            return MatchResult.WIN;
        }
        if (dealerScore > BUST_CONDITION) {
            return MatchResult.LOSE;
        }
        return calculateResultFromNormalCase(dealerScore, playerScore);
    }
}
