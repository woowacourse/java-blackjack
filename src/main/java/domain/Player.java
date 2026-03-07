package domain;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final List<Card> hand = new ArrayList<>();
    private boolean isBust = false;

    public Player(String name) {
        this.name = name;
    }

    public boolean isBust() {
        return isBust;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return List.copyOf(hand);
    }

    public List<String> getHandToString() {
        return hand.stream()
                .map(Card::toString)
                .toList();
    }

    public void setBust() {
        isBust = true;
    }

    @Override
    public String toString() {
        return "domain.Player{" +
                "name='" + name + '\'' +
                ", hand=" + hand +
                ", isBust=" + isBust +
                '}';
    }}
