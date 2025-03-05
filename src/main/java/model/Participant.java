package model;

import java.util.ArrayList;
import java.util.List;

public class Participant {

    private final String nickname;
    private final List<Card> hands;

    protected Participant(String nickname) {
        this.nickname = nickname;
        this.hands = new ArrayList<>();
    }

    public void addCards(final List<Card> findCards) {
        hands.addAll(findCards);
    }

    public String getNickname() {
        return nickname;
    }

    public List<Card> getHands() {
        return hands;
    }

    public int sum() {
        return hands.stream()
                .mapToInt(card -> findScore(card.getRank().getScore()))
                .sum();
    }

    private int findScore(List<Integer> score) {
        if (score.size() == 1) {
            return score.getFirst();
        }
        return score.getFirst();
    }
}
