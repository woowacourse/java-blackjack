package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Participant {

    private static final int ACE_OFFSET = 10;

    private final List<Card> hands;
    private int aceCount;
    protected Score score;

    protected Participant() {
        this.hands = new ArrayList<>();
        this.score = new Score(0);
        this.aceCount = 0;
    }

    public void addCards(final List<Card> findCards) {
        hands.addAll(findCards);
        this.score = score.plus(findCards.stream()
                .mapToInt(card -> findScore(card.getRank().getScore()))
                .sum());
        aceCount += (int) findCards.stream()
                .filter(o -> o.getRank().equals(RankType.ACE))
                .count();
    }

    public boolean isBust() {
        return score.isBust();
    }

    public boolean isNotEnoughScoreCondition() {

        while (aceCount > 0 && isBust()) {
            this.score = score.minus(ACE_OFFSET);
            aceCount--;
        }

        return score.compareScoreCondition() < 0;
    }

    private int findScore(List<Integer> score) {
        return score.getFirst();
    }

    abstract String getNickname();

    public List<Card> getHands() {
        return hands;
    }

    public int getSum() {
        return score.getValue();
    }
}
