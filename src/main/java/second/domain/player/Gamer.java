package second.domain.player;

import first.domain.card.Rank;
import second.domain.ICardDeck;
import second.domain.IPlayer;
import second.domain.card.Card;
import second.domain.card.HandCards;
import second.domain.card.Score;
import second.domain.score.ScoreCalculator;

public abstract class Gamer implements IPlayer {
    private final String name;
    private final HandCards handCards;
    private Score score;

    public Gamer(String name, HandCards handCards) {
        this.name = name;
        this.handCards = handCards;
        this.score = ScoreCalculator.calculate(handCards);
    }

    public boolean isLargerScoreThan(final Score score) {
        return this.score.isLargerThan(score);
    }

    public boolean isLargerScoreThan(final Gamer gamer) {
        return isLargerScoreThan(gamer.score);
    }

    @Override
    public abstract boolean canDrawMore();

    @Override
    public void drawCard(ICardDeck cardDeck) {
        Card drawCard = cardDeck.pickCard();
        handCards.drawCard(drawCard);

        score = score.plus(drawCard.extractScore());
    }

    @Override
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
