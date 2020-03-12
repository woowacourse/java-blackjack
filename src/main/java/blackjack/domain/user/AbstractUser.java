package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractUser implements User {
    private static final int MAX_SCORE_TO_MAXIMIZE = 12;
    private static final int ADDING_SCORE_TO_MAXIMIZE = 10;

    private final String name;
    private final List<Card> cards;

    protected AbstractUser(String name) {
        this.name = name;
        this.cards = new ArrayList<>();
    }

    @Override
    public void append(Card card) {
        cards.add(card);
    }

    @Override
    public boolean isBust() {
        return calculateScore().isOver(21);
    }

    @Override
    public boolean is(String name) {
        return this.name.equals(name);
    }

    @Override
    public Score calculateScore() {
        Score score = sumScore();
        return maximize(score);
    }

    private Score sumScore() {
        Score score = new Score(0);

        for (Card card : cards) {
            score = score.add(card.getScore());
        }
        return score;
    }

    private Score maximize(Score score) {
        if (score.isUnder(MAX_SCORE_TO_MAXIMIZE) && hasAce()) {
            return score.add(new Score(ADDING_SCORE_TO_MAXIMIZE));
        }
        return score;
    }

    private boolean hasAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    @Override
    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    @Override
    public String getName() {
        return name;
    }
}
