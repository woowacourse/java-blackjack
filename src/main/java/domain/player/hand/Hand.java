package domain.player.hand;

import domain.card.Card;
import domain.card.Cards;

import java.util.List;

public class Hand {

    private static final int INIT_SCORE = 0;

    private Score score;
    private final Cards cards;

    private Hand(final Score score, final Cards cards) {
        this.score = score;
        this.cards = cards;
    }

    public static Hand create() {
        return new Hand(Score.from(INIT_SCORE), Cards.create());
    }

    public boolean isBust() {
        return !score.isUnderMaxScore();
    }

    public void takeCard(final Card card) {
        cards.takeCard(card);
        score = sumScore(card);
    }

    private Score sumScore(final Card card) {
        final int cardScore = card.getScore();
        return score.add(cardScore);
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public Score getScore() {
        if (cards.hasAce() && score.canAddBonusScore()) {
            return new Score(score.getScoreWithBonusScore());
        }
        return score;
    }
}
