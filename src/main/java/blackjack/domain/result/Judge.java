package blackjack.domain.result;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;

public class Judge {

    private static final int MAX_SCORE = 21;

    public static boolean compete(final Dealer dealer, final Participant participant) {
        return isDealerWin(dealer.calculateFinalScore(), participant.calculateFinalScore());
    }

    private static boolean isDealerWin(final int dealerScore, final int participantScore) {
        return participantScore > MAX_SCORE || (dealerScore <= MAX_SCORE && dealerScore >= participantScore);
    }

}
