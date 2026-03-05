package domain;


import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final List<Card> cards =  new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public int calculateScore() {
        return 0;
    }

    public void add(Card card) {
        cards.add(card);
    }

    public String getName() {
        return name;
    }
}
