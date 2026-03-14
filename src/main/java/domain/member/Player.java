package domain.member;

import domain.MatchResult;

public class Player extends Member {

    public Player(String name) {
        super(name);
    }

    @Override
    public MatchResult compareScoreWith(Member dealer) {
        int playerScore = currentScore();
        int dealerScore = dealer.currentScore();

        if (playerScore > BUST_CONDITION) {
            return MatchResult.LOSE;
        }
        if (dealerScore > BUST_CONDITION) {
            return MatchResult.WIN;
        }

        return calculateResultFromNormalCase(playerScore, dealerScore);
    }
}
