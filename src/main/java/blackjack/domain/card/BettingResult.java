package blackjack.domain.card;

public class BettingResult {

    public static int getMultiplyRatio(BlackjackScore blackjackScore) {
        if (blackjackScore.value() > 21) {
            return -1;
        }
        return 0;
    }
}
