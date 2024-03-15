package blackjack.domain.result;

public enum WinStatus {
    LOSE("패", -1),
    DRAW("무", 0),
    WIN("승", 1),
    WIN_BLACKJACK("블랙잭승", 1.5);

    private final String name;
    private final double betMultiplier;

    WinStatus(final String name, final double betMultiplier) {
        this.name = name;
        this.betMultiplier = betMultiplier;
    }

    public static WinStatus of(final ParticipantScoreStatus dealerScoreStatus,
                               final ParticipantScoreStatus playerScoreStatus) { // TODO: 라인수 정리
        if (BlackjackStatus.from(playerScoreStatus) == BlackjackStatus.DEAD) {
            return LOSE;
        }
        if (BlackjackStatus.from(playerScoreStatus) == BlackjackStatus.BLACKJACK
                && BlackjackStatus.from(dealerScoreStatus) != BlackjackStatus.BLACKJACK) {
            return WIN_BLACKJACK;
        }
        if (BlackjackStatus.from(dealerScoreStatus) == BlackjackStatus.BLACKJACK
                && BlackjackStatus.from(dealerScoreStatus) != BlackjackStatus.BLACKJACK) {
            return LOSE;
        }
        if (BlackjackStatus.from(dealerScoreStatus) == BlackjackStatus.DEAD || playerScoreStatus.isScoreBiggerThan(
                dealerScoreStatus)) {
            return WIN;
        }
        if (dealerScoreStatus.isScoreBiggerThan(playerScoreStatus)) {
            return LOSE;
        }
        return DRAW;
    }

    public String getName() {
        return name;
    }

    public double getBetMultiplier() {
        return betMultiplier;
    }
}
