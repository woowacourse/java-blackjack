package domain;

public class Hit extends Running {

    public Hit(Hand hand, Score limitScore) {
        super(hand, limitScore);
    }

    @Override
    public State draw(TrumpCard card) {
        hand.addCard(card);
        Score score = Score.from(hand.getCards());

        if (score == Score.BUST) {
            return new Bust(hand);
        }

        if (score.isLowerThan(super.limitScore)) {
            return this;
        }

        return new Stay(hand);
    }
}
