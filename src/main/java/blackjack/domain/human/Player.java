package blackjack.domain.human;

import blackjack.domain.Card;
import java.util.ArrayList;
import java.util.List;

public class Player extends Human {
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
    public String getName() {
        return name;
    }

    @Override
    public void addCard(Card card) {
        cards.add(card);
    }

    @Override
    public List<Card> getCards() {
        return cards;
    }
}
