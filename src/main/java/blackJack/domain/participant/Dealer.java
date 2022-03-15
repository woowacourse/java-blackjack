package blackJack.domain.participant;

import blackJack.domain.result.MatchResult;

public class Dealer extends Participant {

    private static final int DEALER_MAXIMUM_RECEIVE_CARD_SCORE = 16;

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean canHit() {
        return this.getScore() <= DEALER_MAXIMUM_RECEIVE_CARD_SCORE;
    }

    @Override
    public MatchResult getMatchResult(Participant player) {
        if (player.isBurst()) {
            return MatchResult.WIN;
        }
        if (this.getScore() == player.getScore()) {
            return getResultAtSameScore(player);
        }
        return getResultAtDifferentScore(player);
    }

    private MatchResult getResultAtSameScore(Participant player) {
        if (this.isBlackJack() && !player.isBlackJack()) {
            return MatchResult.WIN;
        }
        if (!this.isBlackJack() && player.isBlackJack()) {
            return MatchResult.LOSE;
        }
        return MatchResult.DRAW;
    }

    private MatchResult getResultAtDifferentScore(Participant player) {
        if (this.isBurst()) {
            return MatchResult.LOSE;
        }
        if (this.getScore() > player.getScore()) {
            return MatchResult.WIN;
        }
        return MatchResult.LOSE;
    }
}
