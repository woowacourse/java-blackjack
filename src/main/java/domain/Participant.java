package domain;

import java.util.ArrayList;
import java.util.List;

public class Participant {

    private final String name;
    private final List<Card> cards = new ArrayList<>();

    public Participant(String name) {
        this.name = name;
    }

    public void addCard(Card card) {
        cards.add(card);
    }
}

