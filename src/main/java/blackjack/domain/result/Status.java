package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.player.TotalScore;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;

public enum Status {
    BLACKJACK((count, sum) -> count == 2 && sum == 21),
    BUST((count, sum) -> sum > 21),
    NONE((count, sum) -> sum <= 21);

    private final BiPredicate<Integer, Integer> status;

    Status(BiPredicate<Integer, Integer> status) {
        this.status = status;
    }

    public static Status of(List<Card> cards) {
        int count = cards.size();
        int sum = TotalScore.calculateTotalScore(cards).getTotalScore();

        return Arrays.stream(Status.values())
                .filter(status -> status.status.test(count, sum))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 값 입니다."));
    }
}
