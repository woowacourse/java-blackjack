package domain.blackJack;

import static domain.blackJack.MatchResult.DRAW;
import static domain.blackJack.MatchResult.LOSE;
import static domain.blackJack.MatchResult.WIN;

public class Result {
    private static final int BLACKJACK_NUMBER = 21;

    public MatchResult calculateResultOfPlayer(final int dealerSum, final int playerSum) {
        if ((!isBust(dealerSum) && dealerSum > playerSum) || isBust(playerSum)) {
            return LOSE;
        }
        if (dealerSum < playerSum || isBust(dealerSum)) {
            return WIN;
        }
        return DRAW;
    }

    public boolean isBust(int sum) {
        return sum > BLACKJACK_NUMBER;
    }
}
