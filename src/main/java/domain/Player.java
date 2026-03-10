package domain;

public class Player extends Member {

    public Player(String name) {
        super(name);
    }

    public MatchResult compareScoreWith(Member dealer) {
        int playerScore = currentValue();
        int dealerScore = dealer.currentValue();

        if (playerScore > BUST_CONDITION) {
            return MatchResult.LOSE;
        }
        if (dealerScore > BUST_CONDITION) {
            return MatchResult.WIN;
        }

        return calculateResultFromNormalCase(playerScore, dealerScore);
    }
}
