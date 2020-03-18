package second.domain.gamer;

import second.domain.card.Card;
import second.domain.card.HandCards;
import second.domain.score.Score;
import second.domain.score.ScoreCalculator;

public abstract class Gamer {
    private final String name;
    private final HandCards handCards;
    private Score score;

    public Gamer(final String name, final HandCards handCards) {
        this.name = name;
        this.handCards = handCards;
        this.score = ScoreCalculator.calculate(handCards);
    }

    public abstract boolean canDrawMore();

    boolean isLargerScoreThan(final Score score) {
        return this.score.isLargerThan(score);
    }

    public boolean isLargerScoreThan(final Gamer gamer) {
        return isLargerScoreThan(gamer.score);
    }

    public void draw(final Card card) {
        final Card drawCard = card;
        handCards.drawCard(drawCard);

        score = score.plus(drawCard.extractScore());
    }

    public boolean isBust() {
        return score.isBust();
    }

    public String getName() {
        return name;
    }

    public HandCards getHandCards() {
        return handCards;
    }

    public Score getScore() {
        return score;
    }
}
