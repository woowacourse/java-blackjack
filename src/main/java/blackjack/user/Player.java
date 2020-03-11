package blackjack.user;

import blackjack.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player implements User {
    private final String name;
    private final List<Card> cards;

    private Player(String name) {
        this.name = name;
        this.cards = new ArrayList<>();
    }

    public static Player of(String name) {
        return new Player(name);
    }

    @Override
    public void append(Card card) {
        cards.add(card);
    }

    @Override
    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    @Override
    public String getName() {
        return name;
    }
}
