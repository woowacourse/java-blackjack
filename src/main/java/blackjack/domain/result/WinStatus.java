package blackjack.domain.result;

import blackjack.domain.ParticipantScoreStatus;

public enum WinStatus {
    LOSE("패"),
    DRAW("무"),
    WIN("승"),
    WIN_BLACKJACK("블랙잭승");

    private final String name;

    WinStatus(final String name) {
        this.name = name;
    }

    public static WinStatus of(final ParticipantScoreStatus dealerScoreStatus, final ParticipantScoreStatus playerScoreStatus) {
        if (BlackjackStatus.from(playerScoreStatus) == BlackjackStatus.DEAD) {
            return LOSE;
        }
        if (BlackjackStatus.from(playerScoreStatus) == BlackjackStatus.BLACKJACK && BlackjackStatus.from(dealerScoreStatus) != BlackjackStatus.BLACKJACK) {
            return WIN_BLACKJACK;
        }
        if (BlackjackStatus.from(dealerScoreStatus) == BlackjackStatus.BLACKJACK && BlackjackStatus.from(dealerScoreStatus) != BlackjackStatus.BLACKJACK) {
            return LOSE;
        }
        if (BlackjackStatus.from(dealerScoreStatus) == BlackjackStatus.DEAD || playerScoreStatus.isScoreBiggerThan(dealerScoreStatus)) {
            return WIN;
        }
        if (dealerScoreStatus.isScoreBiggerThan(playerScoreStatus)) {
            return LOSE;
        }
        return DRAW;
    }

//    public static WinStatus of(final Score dealerScore, final Score playerScore, final BlackjackStatus blackjackStatus) {
//        if (BlackjackStatus.from(playerScore) == BlackjackStatus.DEAD) {
//            return LOSE;
//        }
//        if (BlackjackStatus.from(dealerScore) == BlackjackStatus.DEAD || playerScore.isBiggerThan(dealerScore)) {
//            return WIN;
//        }
//        if (dealerScore.isBiggerThan(playerScore)) {
//            return LOSE;
//        }
//        return DRAW;
//    }

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
