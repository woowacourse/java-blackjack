package blackjack.model.participant;

import static blackjack.model.participant.HandStatus.Constants.BLACKJACK_CARD_SIZE;
import static blackjack.model.participant.HandStatus.Constants.BLACKJACK_SCORE;

import java.util.Arrays;
import java.util.function.Predicate;

public enum HandStatus {
    BUST((score -> score > BLACKJACK_SCORE), (cardSize -> cardSize >= BLACKJACK_CARD_SIZE)),
    BLACKJACK((score -> score == BLACKJACK_SCORE), (cardSize -> cardSize == BLACKJACK_CARD_SIZE)),
    NOT_BLACKJACK_BUT_21((score -> score == BLACKJACK_SCORE), (cardSize -> cardSize > BLACKJACK_CARD_SIZE)),
    UNDER_21((score -> score < BLACKJACK_SCORE), (cardSize -> cardSize >= BLACKJACK_CARD_SIZE));

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

    static class Constants {
        public static final int BLACKJACK_SCORE = 21;
        public static final int BLACKJACK_CARD_SIZE = 2;
    }
}
