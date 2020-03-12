package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;

import java.util.*;

public abstract class AbstractPlayer implements Player {
    private static final int MAX_SCORE_TO_MAXIMIZE = 12;
    private static final int ADDING_SCORE_TO_MAXIMIZE = 10;

    private final String name;
    private final List<Card> cards;

    protected AbstractPlayer(String name) {
        this.name = name;
        this.cards = new ArrayList<>();
    }

    @Override
    public void giveCards(Card... cards) {
        this.cards.addAll(Arrays.asList(cards));
    }

    @Override
    public boolean isBust() {
        return calculateScore().isOver(Player.MAX_SCORE);
    }

    @Override
    public boolean isNotBust() {
        return !isBust();
    }

    @Override
    public boolean isName(String name) {
        return this.name.equals(name);
    }

    @Override
    public int countCards() {
        return cards.size();
    }

    @Override
    public Score calculateScore() {
        Score score = sumScore();
        return maximize(score);
    }

    private Score sumScore() {
        Score score = Score.of(0);

        for (Card card : cards) {
            score = score.add(card.getScore());
        }
        return score;
    }

    private Score maximize(Score score) {
        if (score.isUnder(MAX_SCORE_TO_MAXIMIZE) && hasAce()) {
            return score.add(Score.of(ADDING_SCORE_TO_MAXIMIZE));
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractPlayer that = (AbstractPlayer) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(cards, that.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cards);
    }

    @Override
    public String toString() {
        return "AbstractPlayer{" +
                "name='" + name + '\'' +
                ", cards=" + cards +
                '}';
    }
}
