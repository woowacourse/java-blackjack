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

    public MatchResult getMatchResult(Player player) {
        return calculateDealerMatchResult(player.getScore());
    }

    private MatchResult calculateDealerMatchResult(int playerScore) {
        if (MatchResult.isBurst(playerScore)) {
            return MatchResult.WIN;
        }
        if (MatchResult.isBurst(this.getScore())) {
            return MatchResult.LOSE;
        }
        return getMatchResult(playerScore);
    }
}
