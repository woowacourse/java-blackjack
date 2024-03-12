package blackjack.domain.result;

public enum WinStatus {
    LOSE("패"),
    DRAW("무"),
    WIN("승");

    private final String name;

    WinStatus(final String name) {
        this.name = name;
    }

    public static WinStatus of(final Score dealerScore, final Score playerScore) {
        if (BlackjackStatus.from(playerScore) == BlackjackStatus.DEAD) {
            return LOSE;
        }
        if (BlackjackStatus.from(dealerScore) == BlackjackStatus.DEAD || playerScore.isBiggerThan(dealerScore)) {
            return WIN;
        }
        if (dealerScore.isBiggerThan(playerScore)) {
            return LOSE;
        }
        return DRAW;
    }

    public WinStatus opposite() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    public String getName() {
        return name;
    }
}
