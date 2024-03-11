package blackjack.domain;

public class Score {
    private static final int MINIMUM_VALUE = 0;
    private static final int BLACKJACK_MAX_SCORE = 21;
    private static final int DEALER_MINIMUM_SCORE = 17;
    public static final int CONVERTED_ACE_DIFFERENCE = 10;

    private final int value;
    private final boolean isBlackJack;

    public Score(int value, boolean isBlackJack) {
        validateRange(value);
        validateBlackJack(value, isBlackJack);

        this.value = value;
        this.isBlackJack = isBlackJack;
    }

    private void validateRange(int value) {
        if (value < MINIMUM_VALUE) {
            throw new IllegalArgumentException("음수는 점수로 사용될 수 없습니다.");
        }
    }

    private void validateBlackJack(int value, boolean isBlackJack) {
        if (isBlackJack && value != BLACKJACK_MAX_SCORE) {
            throw new IllegalArgumentException(BLACKJACK_MAX_SCORE + "점이 아닐 경우 블랙잭일 수 없습니다.");
        }
    }

    public static Score of(int value) {
        return new Score(value, false);
    }

    public static Score blackJackScore() {
        return new Score(BLACKJACK_MAX_SCORE, false);
    }

    public boolean isBusted() {
        return value > BLACKJACK_MAX_SCORE;
    }

    private boolean isBusted(int scoreValue) {
        return scoreValue > BLACKJACK_MAX_SCORE;
    }

    private boolean isGreaterThan(Score relativeScore) {
        return value > relativeScore.value;
    }

    private boolean isSame(Score relativeScore) {
        return value == relativeScore.value;
    }

    private boolean isLessThan(Score relativeScore) {
        return value < relativeScore.value;
    }

    public boolean isLessThanDealerMinimumScore() {
        return value < DEALER_MINIMUM_SCORE;
    }

    public Score convertToSmallAce(int currentBigAceAmount) {
        int currentValue = value;
        int convertedAceAmount = 0;
        while (isBusted(currentValue) && convertedAceAmount < currentBigAceAmount) {
            currentValue -= CONVERTED_ACE_DIFFERENCE;
            convertedAceAmount++;
        }

        return Score.of(currentValue);
    }

    public GameResult compareWith(Score other) {
        // TODO: 메서드 행 줄이기ㅣ
        if (this.isBusted() && other.isBusted()) {
            return GameResult.DRAW; // both busted
        }
        if (this.isBusted()) {
            return GameResult.LOSE;
        }
        if (other.isBusted()) {
            return GameResult.WIN;
        }

        if (this.isGreaterThan(other)) {
            return GameResult.WIN;
        }
        if (this.isLessThan(other)) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }

    public int getValue() {
        return value;
    }
}
