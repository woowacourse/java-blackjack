package blackjack.domain.human;

import blackjack.domain.Card;
import blackjack.domain.Cards;

public class Player extends Human {
    private final String name;
    private final Cards cards;

    private Player(String name) {
        this.name = name;
        this.cards = Cards.of();
    }

    public static Player of(String name) {
        return new Player(name);
    }

    public boolean isOneMoreCard(boolean input) {
        return input;
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
    public Cards getCards() {
        return cards;
    }
}
