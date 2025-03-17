package domain;

public abstract class Started implements State {

    public static final int INITIAL_CARD_COUNT = 2;

    protected final Hand hand;
    protected final Score limitScore;

    protected Started(Hand hand, Score limitScore) {
        validate(hand, limitScore);
        this.hand = hand;
        this.limitScore = limitScore;
    }

    private void validate(Hand hand, Score limitScore) {
        validateNotNull(hand, limitScore);
        validateSize(hand);
    }

    private void validateNotNull(Hand hand, Score limitScore) {
        if (hand == null || limitScore == null) {
            throw new IllegalArgumentException("시작할 때 손패와 제한 점수를 가져야합니다.");
        }
    }

    private void validateSize(Hand hand) {
        if (hand.getCards().size() != INITIAL_CARD_COUNT) {
            throw new IllegalArgumentException("시작할 때 손패는 2장이어야 합니다.");
        }
    }

    public static State of(Hand hand, Score limitScore) {
        Score score = Score.from(hand.getCards());

        if (score == Score.BLACKJACK) {
            return new BlackJack(hand);
        }

        if (score.isLowerThan(limitScore)) {
            return new Hit(hand, limitScore);
        }

        return new Stay(hand);
    }
}
