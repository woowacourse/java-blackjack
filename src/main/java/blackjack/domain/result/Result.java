package blackjack.domain.result;

import blackjack.domain.player.Player;
import blackjack.domain.player.Players;

public enum Result {

    WIN("승"),
    LOSE("패"),
    DRAW("무"),
    ;

    public static final int MAX_SCORE = 21;
    private final String value;

    Result(final String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static Result competeResult(Player dealer, Player participant) {
        if (checkDraw(dealer, participant)) {
            return Result.DRAW;
        }
        if (compete(dealer, participant)) {
            return Result.WIN;
        }
        return Result.LOSE;
    }

    private static boolean checkDraw(Player dealer, Player participant) {
        if (dealer.isBlackjack() && participant.isBlackjack()) {
            return true;
        }

        return !dealer.isBlackjack() && !participant.isBlackjack() && dealer.calculateFinalScore() == participant.calculateFinalScore();
    }

    private static boolean compete(final Player dealer, final Player participant) {
        return isParticipantWin(dealer.calculateFinalScore(), participant.calculateFinalScore());
    }

    private static boolean isParticipantWin(int dealerScore, int participantScore) {
        return dealerScore > MAX_SCORE || (participantScore <= MAX_SCORE && participantScore >= dealerScore);
    }

    public boolean isWin() {
        return this == WIN;
    }

    public boolean isLose() {
        return this == LOSE;
    }

    public boolean isDraw() {
        return this == DRAW;
    }
}
