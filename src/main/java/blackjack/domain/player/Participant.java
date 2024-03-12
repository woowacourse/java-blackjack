package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.common.Name;
import blackjack.domain.card.Cards;

import java.util.List;

public abstract class Participant implements CardReceivable {
    private static final int BUST_SIZE = 21;
    private static final Score CHANGE_A_VALUE = Score.from(10);

    protected final Name name;
    protected final Cards cards;

    protected Participant(final Name name, final Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public Score calculateScore() {
        final Score score = Score.from(cards.sum());
        final Score maxScore = score.add(CHANGE_A_VALUE);

        if (cards.containAce() && maxScore.toInt() <= BUST_SIZE) {
            return maxScore;
        }
        return score;
    }

    public void drawCard(final Card card) {
        cards.add(card);
    }

    public boolean isBust() {
        return cards.sum() > BUST_SIZE;
    }

    public String getNameAsString() {
        return name.asString();
    }

    public Name getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards.toList();
    }

}
