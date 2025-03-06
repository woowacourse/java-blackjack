package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Participant {

    private final List<Card> hands;
    private final Map<MatchType, Integer> matchResult;
    private int aceCount;
    protected Score score;

    protected Participant() {
        this.hands = new ArrayList<>();
        this.matchResult = new HashMap<>();
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

    public boolean isNotEnoughScoreCondition() {

        while (aceCount > 0 && score.getValue() > 21) {
            this.score = score.minus(10);
            aceCount--;
        }

        return score.getValue() < 21;
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

    public void updateResult(MatchType type) {
        matchResult.computeIfAbsent(type, k -> 0);
        matchResult.put(type, matchResult.get(type) + 1);
    }

    public Map<MatchType, Integer> getMatchResult() {
        return matchResult;
    }
}
