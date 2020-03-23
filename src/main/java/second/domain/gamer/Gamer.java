package second.domain.gamer;

import second.domain.BlackJackRule;
import second.domain.card.Card;
import second.domain.card.HandCards;
import second.domain.score.Score;
import second.domain.score.ScoreCalculator;

public abstract class Gamer {
    private final String name;
    private final HandCards handCards;
    private final Money money;
    private Score score;

    public Gamer(final String name, final HandCards handCards, final Money money) {
        this.name = name;
        this.handCards = handCards;
        this.score = ScoreCalculator.calculate(handCards);
        this.money = money;
    }

    public abstract boolean canDrawMore();

    boolean isLargerScoreThan(final Score score) {
        return this.score.isLargerThan(score);
    }

    public boolean isLargerScoreThan(final Gamer gamer) {
        return isLargerScoreThan(gamer.score);
    }

    public boolean isBlackJack() {
        return BlackJackRule.isBlackJack(handCards, score);
    }

    public void draw(final Card card) {
        handCards.drawCard(card);
        score = ScoreCalculator.calculate(handCards);
    }

    public boolean isBust() {
        return score.isBurst();
    }

    public boolean isSameScoreAs(Gamer counterGamer) {
        return score.isSameAs(counterGamer.score);
    }

    public Money calculateProfit(double value) {
        return this.money.times(value);
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
