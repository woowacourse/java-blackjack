package domain;

import java.util.ArrayList;
import java.util.List;

public class User {

    private final String name;
    private final List<Card> hand = new ArrayList<>();

    private User(String name) {
        this.name = name;
    }

    public static User from(String input) {
        return new User(input);
    }

    public String getName() {
        return name;
    }

    public void receiveInitCard(List<Card> cards) {
        hand.addAll(cards);
    }

    public List<Card> getHand() {
        return hand;
    }
}
