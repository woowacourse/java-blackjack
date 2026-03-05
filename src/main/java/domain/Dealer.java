package domain;

import java.util.ArrayList;
import java.util.List;

public class Dealer extends Player {
    private final List<Card> cards = new ArrayList<>();

    public Dealer(String name) {
        super(name);
    }

    public void shareCard(Player player) {

    }

    public void add(Card card) {
        cards.add(card);
    }
}
