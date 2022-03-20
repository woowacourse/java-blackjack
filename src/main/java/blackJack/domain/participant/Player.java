package blackJack.domain.participant;

import blackJack.domain.result.MatchResult;

public class Player extends Participant {

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
        return !this.getScore().isBurst();
    }

    public MatchResult getMatchResult(Participant dealer) {
        if (this.isBurst()) {
            return MatchResult.LOSE;
        }
        if (this.isBlackJack() && !dealer.isBlackJack()) {
            return MatchResult.BLACK_JACK_WIN;
        }
        if (this.getScore().compareTo(dealer.getScore()) == 0) {
            return getResultAtSameScore(dealer);
        }
        return getResultAtDifferentScore(dealer);
    }

    private MatchResult getResultAtSameScore(Participant dealer) {
        if (!this.isBlackJack() && dealer.isBlackJack()) {
            return MatchResult.LOSE;
        }
        return MatchResult.DRAW;
    }

    private MatchResult getResultAtDifferentScore(Participant dealer) {
        if (dealer.isBurst()) {
            return MatchResult.WIN;
        }
        if (this.getScore().compareTo(dealer.getScore()) > 0) {
            return MatchResult.WIN;
        }
        return MatchResult.LOSE;
    }
}
