package blackjack.domain;

import java.util.function.BiPredicate;
import java.util.stream.Stream;

public enum Status {
    LOSE("패", (dealer, player) -> player.isBust()),
    DRAW("무", (dealer, player) -> player.compareTo(dealer) == 0),
    WIN("승", (dealer, player) -> (dealer.isBust() && player.isNotBust())
        || player.compareTo(dealer) > 0);

    private final String status;
    private final BiPredicate<Score, Score> match;

    Status(String status, BiPredicate<Score, Score> match) {
        this.status = status;
        this.match = match;
    }

    public static Status compare(Score dealerScore, Score playerScore) {
        return Stream.of(values())
            .filter(status -> status.isMatch(dealerScore, playerScore))
            .findFirst()
            .orElse(LOSE);
    }

    private boolean isMatch(Score dealerScore, Score playerScore) {
        return match.test(dealerScore, playerScore);
    }

    public String getStatus() {
        return status;
    }
}
