package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum ResultStatus {
    WIN((playerScore, dealerScore) -> playerScore > dealerScore),
    DRAW((playerScore, dealerScore) -> playerScore == dealerScore),
    LOSE((playerScore, dealerScore) -> playerScore < dealerScore);

    private final BiPredicate<Integer, Integer> condition;

    ResultStatus(final BiPredicate<Integer, Integer> condition) {
        this.condition = condition;
    }

    public static ResultStatus of(final Player player, final Dealer dealer) {
        if (player.isBust() || dealer.isBlackjack()) {
            return LOSE;
        }
        return compareScore(player.calculateScore(), dealer.calculateScore());
    }

    private static ResultStatus compareScore(final int playerScore, final int dealerScore) {
        return Arrays.stream(values())
                .filter(resultStatus -> resultStatus.findResultStatus(playerScore, dealerScore))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("결과를 찾을 수 없습니다."));
    }

    private boolean findResultStatus(final int playerScore, final int dealerScore) {
        return condition.test(playerScore, dealerScore);
    }
}
