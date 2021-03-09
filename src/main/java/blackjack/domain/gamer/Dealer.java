package blackjack.domain.gamer;

import blackjack.domain.MatchResult;

public class Dealer extends Participants {

    private static final int DEALER_MAX_SCORE = 16;
    public static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    public MatchResult matchGame(Player player) {
        return MatchResult.getPlayerMatchResult(player.getTakenCards().calculateScore(), this.getTakenCards().calculateScore());
    }

    @Override
    public boolean canDraw() {
        return this.getTakenCards().calculateScore() <= DEALER_MAX_SCORE;
    }
}

