package second.domain.player;

import second.domain.card.CardProviable;
import second.domain.card.Card;
import second.domain.card.HandCards;
import second.domain.card.Score;
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

    boolean isLargerScoreThan(final Score score) {
        return this.score.isLargerThan(score);
    }

    public boolean isLargerScoreThan(final Gamer gamer) {
        return isLargerScoreThan(gamer.score);
    }

    public abstract boolean canDrawMore();

    public void drawCard(final CardProviable cardDeck) {
        final Card drawCard = cardDeck.pickCard();
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
