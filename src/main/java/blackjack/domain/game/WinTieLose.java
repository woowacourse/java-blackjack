package blackjack.domain.game;

public enum WinTieLose {

    WIN("승"),
    TIE("무"),
    LOSE("패");

    private final String value;

    WinTieLose(final String value) {
        this.value = value;
    }

    public static WinTieLose resultPlayer(final Score playerScore, final Score dealerScore) {
        if (bothBust(playerScore, dealerScore) || playerScore.isEqualTo(dealerScore)) {
            return TIE;
        }
        if (playerScore.canHit() && (dealerScore.isBust() || playerScore.isGreaterThan(dealerScore))) {
            return WIN;
        }
        return LOSE;
    }

    private static boolean bothBust(final Score scoreFirst, final Score scoreSecond) {
        return scoreFirst.isBust() && scoreSecond.isBust();
    }

    public WinTieLose reverse() {
        if (equals(WIN)) {
            return LOSE;
        }
        if (equals(TIE)) {
            return TIE;
        }
        return WIN;
    }

    public String getValue() {
        return value;
    }
}
