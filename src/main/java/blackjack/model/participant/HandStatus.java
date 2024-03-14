package blackjack.model.participant;

import java.util.Arrays;
import java.util.function.Predicate;

public enum HandStatus {
    BUST((score -> score > 21), (cardSize -> cardSize >= 2)),
    BLACKJACK((score -> score == 21), (cardSize -> cardSize == 2)),
    NOT_BLACKJACK_BUT_21((score -> score == 21), (cardSize -> cardSize > 2)),
    UNDER_21((score -> score > 21), (cardSize -> cardSize >= 2));

    private final Predicate<Integer> scoreCondition;
    private final Predicate<Integer> cardSizeCondition;

    HandStatus(final Predicate<Integer> scoreCondition, final Predicate<Integer> cardSizeCondition) {
        this.scoreCondition = scoreCondition;
        this.cardSizeCondition = cardSizeCondition;
    }

    public static HandStatus of(final int score, final int cardSize) {
        return Arrays.stream(HandStatus.values())
                .filter(handStatus -> handStatus.test(score, cardSize))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("해당 스코어와 카드 갯수에 일치하는 카드 목록의 상태가 존재하지 않습니다."));
    }

    public boolean test(final int score, final int cardSize) {
        return scoreCondition.test(score) &&
                cardSizeCondition.test(cardSize);
    }
}
