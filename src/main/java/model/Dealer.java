package model;

import java.util.ArrayList;
import java.util.List;

public class Dealer {

    private final String nickname;
    private final List<Card> hands;

    public Dealer() {
        this.nickname = "딜러";
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
}
