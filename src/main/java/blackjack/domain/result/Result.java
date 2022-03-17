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

        return bothNotBust(dealer, participant) && bothNotBlackjack(dealer, participant) && isScoreSame(dealer, participant);
    }

    private static boolean bothNotBust(Player dealer, Player participant) {
        return !dealer.isBust() && !participant.isBust();
    }

    private static boolean bothNotBlackjack(Player dealer, Player participant) {
        return !dealer.isBlackjack() && !participant.isBlackjack();
    }

    private static boolean isScoreSame(Player dealer, Player participant) {
        return dealer.calculateFinalScore() == participant.calculateFinalScore();
    }

    private static boolean compete(final Player dealer, final Player participant) {
        if (participant.isBlackjack()) {
            return true;
        }

        return isParticipantWin(dealer.calculateFinalScore(), participant.calculateFinalScore());
    }

    private static boolean isParticipantWin(int dealerScore, int participantScore) {
        return participantScore <= MAX_SCORE && participantScore > dealerScore;
    }

    public boolean isWin() {
        return this == WIN;
    }

    public boolean isLose() {
        return this == LOSE;
    }
}
