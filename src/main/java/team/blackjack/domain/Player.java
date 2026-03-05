package team.blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final List<Hand> hands = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public List<Hand> getHands() {
        return List.copyOf(hands);
    }

    public void addHand(Hand hand) {
        hands.add(hand);
    }

    public String getName() {
        return this.name;
    }
}
