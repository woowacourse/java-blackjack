package blackjack.domain;

import blackjack.domain.card.Hand;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Outcome {

    WIN, LOSE, PUSH;

    private static final int BLACKJACK_CANDIDATE = 21;

    public static Outcome doesPlayerWin(final Hand dealerHand, final Hand playerHand) {
        final int dealerScore = dealerHand.calculateOptimalSum();
        final int playerScore = playerHand.calculateOptimalSum();
        if (isBust(dealerScore) || isBust(playerScore)) {
            return calculateBustCase(dealerHand, playerHand);
        }
        if (isBlackJack(dealerHand) || isBlackJack(playerHand)) {
            return calculateBlackJackCase(dealerHand, playerHand);
        }
        return calculateNormalCase(dealerScore, playerScore);
    }

    private static boolean isBust(int score) {
        return score > BLACKJACK_CANDIDATE;
    }

    private static Outcome calculateBustCase(final Hand dealerHand, final Hand playerHand) {
        if (isBust(dealerHand.calculateOptimalSum()) && isBust(playerHand.calculateOptimalSum())) {
            return Outcome.PUSH;
        }
        if (isBust(dealerHand.calculateOptimalSum())) {
            return Outcome.WIN;
        }
        return Outcome.LOSE;
    }

    private static boolean isBlackJack(Hand hand) {
        return hand.calculateOptimalSum() == BLACKJACK_CANDIDATE && hand.hasOnlyInitialCard();
    }

    private static Outcome calculateBlackJackCase(final Hand dealerHand, final Hand playerHand) {
        if (isBlackJack(dealerHand) && isBlackJack(playerHand)) {
            return Outcome.PUSH;
        }
        if (isBlackJack(dealerHand)) {
            return Outcome.LOSE;
        }
        return Outcome.WIN;
    }

    private static Outcome calculateNormalCase(final int dealerScore, final int playerScore) {
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
