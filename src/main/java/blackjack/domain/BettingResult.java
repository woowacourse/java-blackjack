package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum BettingResult {

    BLACKJACK_WIN(1.5),
    WIN(1),
    PUSH(0),
    LOSE(-1);

    private final double profitRate;

    BettingResult(double profitRate) {
        this.profitRate = profitRate;
    }

    public int getProfitRate(int betMoney) {
        return (int) (betMoney * profitRate);
    }

    public static BettingResult judge(Dealer dealer, Player player) {
        GameScore playerScore = player.getScore();
        GameScore dealerScore = dealer.getScore();

        if (player.isBlackjack() && !dealer.isBlackjack()) {
            return BettingResult.BLACKJACK_WIN;
        }
        if (dealer.isBlackjack() && !player.isBlackjack()) {
            return BettingResult.LOSE;
        }
        if (playerScore.isBust()) {
            return BettingResult.LOSE;
        }
        if (dealerScore.isBust()) {
            return BettingResult.WIN;
        }
        if (playerScore.isBiggerThan(dealerScore)) {
            return BettingResult.WIN;
        }
        if (dealerScore.isBiggerThan(playerScore)) {
            return BettingResult.LOSE;
        }
        return BettingResult.PUSH;
    }
}
