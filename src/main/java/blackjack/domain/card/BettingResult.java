package blackjack.domain.card;

public class BettingResult {

    public static int getMultiplyRatio(BlackjackScore playerScore, BlackjackScore dealerScore) {
        WinningResult winningResult = WinningResult.decide(playerScore, dealerScore);
        if (winningResult.equals(WinningResult.WIN)) {
            return 1;
        }
        if (winningResult.equals(WinningResult.LOSE)) {
            return -1;
        }
        return 0;
    }
}
