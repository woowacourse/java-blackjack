package blackJack.domain.participant;

import blackJack.domain.result.MatchResult;

public class Player extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final String ERROR_MESSAGE_PROHIBIT_NAME = "플레이어의 이름은 '딜러'일 수 없습니다.";

    public Player(String name) {
        super(name);
        validateProhibitName(name);
    }

    private void validateProhibitName(String name) {
        if (name.equals(DEALER_NAME)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_PROHIBIT_NAME);
        }
    }

    @Override
    public boolean canHit() {
        return !MatchResult.isBurst(this.getScore());
    }

    public MatchResult getMatchResult(Dealer dealer) {
        return calculatePlayerMatchResult(dealer.getScore());
    }

    private MatchResult calculatePlayerMatchResult(int dealerScore) {
        if (MatchResult.isBurst(this.getScore())) {
            return MatchResult.LOSE;
        }
        if (MatchResult.isBurst(dealerScore)) {
            return MatchResult.WIN;
        }
        return getMatchResult(dealerScore);
    }

    private MatchResult getMatchResult(int dealerScore) {
        if (this.getScore() > dealerScore) {
            return MatchResult.WIN;
        }
        if (this.getScore() == dealerScore) {
            return MatchResult.DRAW;
        }
        return MatchResult.LOSE;
    }
}
