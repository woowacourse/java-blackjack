package blackjack.domain;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Outcome {

    WIN, LOSE, PUSH;

    public static Money calculateProfit(final Dealer dealer, final Player player) {
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

    public static Money calculateBlackjackCaseProfit(final Dealer dealer, final Player player) {
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

    public static Outcome doesPlayerWin(final Dealer dealer, final Player player) {
        if (dealer.isBust() || player.isBust()) {
            return calculateBustCase(dealer, player);
        }
        if (dealer.isBlackjack() || player.isBlackjack()) {
            return calculateBlackjackCase(dealer, player);
        }
        return calculateNormalCase(dealer, player);
    }

    private static Outcome calculateBustCase(final Dealer dealer, final Player player) {
        if (dealer.isBust() && player.isBust()) {
            return Outcome.PUSH;
        }
        if (dealer.isBust()) {
            return Outcome.WIN;
        }
        return Outcome.LOSE;
    }

    private static Outcome calculateBlackjackCase(final Dealer dealer, final Player player) {
        if (dealer.isBlackjack() && player.isBlackjack()) {
            return Outcome.PUSH;
        }
        if (dealer.isBlackjack()) {
            return Outcome.LOSE;
        }
        return Outcome.WIN;
    }

    private static Outcome calculateNormalCase(final Dealer dealer, final Player player) {
        final int dealerScore = dealer.calculateScore();
        final int playerScore = player.calculateScore();

        if (dealerScore < playerScore) {
            return Outcome.WIN;
        }
        if (dealerScore > playerScore) {
            return Outcome.LOSE;
        }
        return Outcome.PUSH;
    }

    public static Outcome reverse(final Outcome outcome) {
        if (outcome == Outcome.WIN) {
            return Outcome.LOSE;
        }
        if (outcome == Outcome.LOSE) {
            return Outcome.WIN;
        }
        return Outcome.PUSH;
    }

    public static Map<Outcome, Long> countByKind(final List<Outcome> outcomes) {
        return Collections.unmodifiableMap(outcomes.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));
    }
}
