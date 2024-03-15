package blackjack.domain;

import java.util.List;

//TODO: Dealer, Player를 상태로 갖는 객체를 만드는건 어떨까?
public class Outcome {

    public static Money calculatePlayerProfit(final Dealer dealer, final Player player) {
        if (player.isBust() || dealer.isBust()) {
            return calculateBustCaseProfit(player);
        }
        if (player.isBlackjack() || dealer.isBlackjack()) {
            return calculateBlackjackCaseProfit(dealer, player);
        }
        return calculateNormalCaseProfit(dealer, player);
    }

    private static Money calculateBustCaseProfit(final Player player) {
        if (player.isBust()) {
            return player.calculateLoseProfit();
        }
        return player.calculateWinProfit();
    }

    private static Money calculateBlackjackCaseProfit(final Dealer dealer, final Player player) {
        if (player.isBlackjack() && dealer.isBlackjack()) {
            return player.calculatePushProfit();
        }
        if (player.isBlackjack()) {
            return player.calculateBlackjackWinProfit();
        }
        return player.calculateLoseProfit();
    }

    private static Money calculateNormalCaseProfit(final Dealer dealer, final Player player) {
        final int dealerScore = dealer.calculateScore();
        final int playerScore = player.calculateScore();

        if (dealerScore < playerScore) {
            return player.calculateWinProfit();
        }
        if (dealerScore > playerScore) {
            return player.calculateLoseProfit();
        }
        return player.calculatePushProfit();
    }

    public static Money calculateDealerProfit(final List<Money> playerProfits) {
        final double playerProfitSum = playerProfits.stream()
                .mapToDouble(Money::value)
                .sum();
        return new Money(playerProfitSum).toNegative();
    }
}
