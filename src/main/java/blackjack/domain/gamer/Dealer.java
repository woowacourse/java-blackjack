package blackjack.domain.gamer;

import blackjack.domain.MatchResult;
import blackjack.domain.Score;

public class Dealer extends Person {

    private static final int DEALER_MAX_SCORE = 16;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    public MatchResult matchGame(Player player) {
        return MatchResult.getPlayerMatchResult(Score.calculatorScore(player.cards), Score.calculatorScore(cards));
    }

    @Override
    public boolean canDraw() {
        return Score.calculatorScore(cards) <= DEALER_MAX_SCORE;
    }
}

