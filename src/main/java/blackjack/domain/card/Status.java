package blackjack.domain.card;

import java.util.Arrays;
import java.util.function.Predicate;

public enum Status {
    BLACKJACK((cards) -> cards.getCount() == Cards.BLACKJACK_COUNT && cards.sum() == Cards.BLACKJACK_VALUE),
    BUST((cards) -> cards.sum() > Cards.BLACKJACK_VALUE),
    NONE((cards) -> cards.sum() < Cards.BLACKJACK_VALUE
            || (cards.sum() == Cards.BLACKJACK_VALUE && cards.getCount() != Cards.BLACKJACK_COUNT));

    private final Predicate<Cards> condition;

    Status(Predicate<Cards> condition) {
        this.condition = condition;
    }

    public static Status findStatus(Cards cards) {
        return Arrays.stream(Status.values())
                .filter(status -> status.condition.test(cards))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 cards 입니다."));
    }
}
