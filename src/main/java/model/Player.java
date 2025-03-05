package model;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final Nickname nickname;
    private final List<Card> hands;

    public Player(Nickname nickname) {
        this.nickname = nickname;
        this.hands = new ArrayList<>();
    }

    public Nickname getNickname() {
        return nickname;
    }

    public void addCards(final List<Card> findCards) {
        hands.addAll(findCards);
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

    public List<Card> getHands() {
        return hands;
    }
}
