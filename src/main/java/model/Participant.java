package model;

import java.util.ArrayList;
import java.util.List;

public class Participant {

    private final String nickname;
    private final List<Card> hands;
    private int sum;
    private int aceCount;

    protected Participant(String nickname) {
        this.nickname = nickname;
        this.hands = new ArrayList<>();
        this.sum = 0;
        this.aceCount = 0;
    }

    public void addCards(final List<Card> findCards) {
        hands.addAll(findCards);
        sum += findCards.stream()
                .mapToInt(card -> findScore(card.getRank().getScore()))
                .sum();
        aceCount += (int) findCards.stream()
                .filter(o -> o.getRank().equals(RankType.ACE))
                .count();
    }

    public boolean isNotBust() {

        while (aceCount > 0 && sum > 21) {
            sum -= 10;
            aceCount--;
        }

        return sum <= 21;
    }

    private int findScore(List<Integer> score) {
        return score.getFirst();
    }

    public String getNickname() {
        return nickname;
    }

    public List<Card> getHands() {
        return hands;
    }

    public int getSum() {
        return sum;
    }

}
