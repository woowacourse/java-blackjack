package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

import java.util.Arrays;
import java.util.Optional;
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
        final Optional<ResultStatus> resultStatusOpt = checkByState(player, dealer);
        return resultStatusOpt.orElseGet(() ->
                compareScore(player.calculateScore(), dealer.calculateScore()));
    }

    private static Optional<ResultStatus> checkByState(final Player player, final Dealer dealer) {
        if (player.isBlackjack() && dealer.isBlackjack()) {
            return Optional.of(DRAW);
        }
        if (dealer.isBlackjack() || player.isBust()) {
            return Optional.of(LOSE);
        }
        if (dealer.isBust() || player.isBlackjack()) {
            return Optional.of(WIN);
        }
        return Optional.empty();
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
