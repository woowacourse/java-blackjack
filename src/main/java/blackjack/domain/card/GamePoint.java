package blackjack.domain.card;

import java.util.Arrays;
import java.util.List;

public class GamePoint implements Comparable<GamePoint> {

    private static final int BUST = 0;
    private static final int MAX_GAME_POINT_VALUE = 21;
    private static final int ACE_BONUS_VALUE = 10;
    private final int gamePoint;

    public GamePoint(final List<Card> cards) {
        this.gamePoint = getOptimizedValue(cards);
    }

    private int getOptimizedValue(List<Card> cards) {
        int originValue = getValueOf(cards);
        if (originValue + ACE_BONUS_VALUE <= MAX_GAME_POINT_VALUE && containAce(cards)) {
            originValue += ACE_BONUS_VALUE;
        }
        return checkWithBust(originValue);
    }

    private int getValueOf(final List<Card> cards) {
        int value = 0;
        for (Card card : cards) {
            value += PointValue.getDefaultValueOf(card.getCardNumber());
        }
        return value;
    }

    private boolean containAce(final List<Card> cards) {
        return cards.stream().anyMatch(card -> card.isAce());
    }

    private int checkWithBust(final int value) {
        if (value > MAX_GAME_POINT_VALUE) {
            return BUST;
        }
        return value;
    }

    public boolean isBusted() {
        return gamePoint == BUST;
    }

    public boolean isLowerThan(final int value) {
        return gamePoint <= value;
    }

    public int getValue() {
        return gamePoint;
    }

    @Override
    public int compareTo(final GamePoint other) {
        return Integer.compare(this.gamePoint, other.gamePoint);
    }

    private enum PointValue {
        ACE(CardNumber.ACE, 1),
        Two(CardNumber.TWO, 2),
        THREE(CardNumber.THREE, 3),
        FOUR(CardNumber.FOUR, 4),
        FIVE(CardNumber.FIVE, 5),
        SIX(CardNumber.SIX, 6),
        SEVEN(CardNumber.SEVEN, 7),
        EIGHT(CardNumber.EIGHT, 8),
        NINE(CardNumber.NINE, 9),
        TEN(CardNumber.TEN, 10),
        JACK(CardNumber.JACK, 10),
        QUEEN(CardNumber.JACK, 10),
        KING(CardNumber.KING, 10);

        private CardNumber cardNumber;
        private int defaultValue;

        PointValue(CardNumber cardNumber, int value) {
            this.cardNumber = cardNumber;
            this.defaultValue = value;
        }

        public static int getDefaultValueOf(CardNumber cardNumber) {
            final PointValue pointValue = Arrays.stream(values()).filter((num) -> num.cardNumber.equals(cardNumber))
                    .findAny()
                    .orElseThrow(() -> {
                        throw new IllegalArgumentException("해당 Number는 처리되지 않습니다.");
                    });
            return pointValue.defaultValue;
        }
    }
}
