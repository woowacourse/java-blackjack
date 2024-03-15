package blackjack.domain.participant;

import blackjack.domain.card.Score;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.BiPredicate;

public enum ResultStatus {
    WIN(Score::isGreaterThan),
    DRAW(Score::equals),
    LOSE(Score::isLessThan);

    private final BiPredicate<Score, Score> condition;

    ResultStatus(final BiPredicate<Score, Score> condition) {
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

    private static ResultStatus compareScore(final Score playerScore, final Score dealerScore) {
        return Arrays.stream(values())
                .filter(resultStatus -> resultStatus.findResultStatus(playerScore, dealerScore))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("결과를 찾을 수 없습니다."));
    }

    private boolean findResultStatus(final Score playerScore, final Score dealerScore) {
        return condition.test(playerScore, dealerScore);
    }

    public boolean isWin() {
        return this == WIN;
    }

    public boolean isDraw() {
        return this == DRAW;
    }
}
