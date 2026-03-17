package domain;

public class BlackjackBettingCalculator {

    private BlackjackBettingCalculator() {
    }

    public static void calculate(Dealer dealer, PlayerBets playerBets) {
        compareResult(dealer, playerBets);
    }

    private static void compareResult(Dealer dealer, PlayerBets playerBets) {
        for (PlayerBet playerBet : playerBets.getPlayersBets()) {
            getJudgeResult(dealer, playerBet);
        }
    }

    private static void getJudgeResult(Dealer dealer, PlayerBet playerBet) {
        if (dealer.isBlackjack()) {
            playerBet.applyProfitIfDealerBlackjack();
            return;
        }
        if (playerBet.applyProfitIfBlackjackAndReturnApplied()) {
            return;
        }
        if (dealer.isBust()) {
            playerBet.IsBustLoseBettingAmount();
            return;
        }
        playerBet.applyProfitByDealerScore(dealer.calculateScore());
    }
}
