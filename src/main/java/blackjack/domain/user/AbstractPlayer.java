package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;

import java.util.*;

public abstract class AbstractPlayer implements Player {
    private static final int MAX_SCORE = 21;
    private static final int MAX_SCORE_TO_MAXIMIZE = 12;
    private static final int ADDING_SCORE_TO_MAXIMIZE = 10;

    private final String name;
    private final List<Card> hand;

    protected AbstractPlayer(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    protected int getMaxScore() {
        return MAX_SCORE;
    }

    @Override
    public void giveCards(Card... cards) {
        this.hand.addAll(Arrays.asList(cards));
    }

    @Override
    public boolean isBust() {
        return getScore().isOver(getMaxScore());
    }

    @Override
    public boolean isNotBust() {
        return !isBust();
    }

    @Override
    public int countCards() {
        return hand.size();
    }

    @Override
    public Score getScore() {
        Score score = sumScore();
        return maximize(score);
    }

    private Score sumScore() {
        Score score = Score.of(0);

        for (Card card : hand) {
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
        return hand.stream().anyMatch(Card::isAce);
    }

    @Override
    public List<Card> getHand() {
        return Collections.unmodifiableList(hand);
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
                Objects.equals(hand, that.hand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, hand);
    }

    @Override
    public String toString() {
        return "AbstractPlayer{" +
                "name='" + name + '\'' +
                ", cards=" + hand +
                '}';
    }
}
