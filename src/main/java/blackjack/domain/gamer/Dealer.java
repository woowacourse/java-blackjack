package blackjack.domain.gamer;

import blackjack.domain.MatchResult;
import blackjack.domain.Score;

public class Dealer extends Participants {

    private static final int DEALER_MAX_SCORE = 16;
    public static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    public MatchResult matchGame(Player player) {
        return MatchResult.getPlayerMatchResult(Score.calculatorScore(player.getTakenCards()), Score.calculatorScore(this.getTakenCards()));
    }

    @Override
    public boolean canDraw() {
        return Score.calculatorScore(this.getTakenCards()) <= DEALER_MAX_SCORE;
    }
}

