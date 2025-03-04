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

    public List<Card> getHands() {
        return hands;
    }
}
