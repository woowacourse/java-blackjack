package blackjack.domain.human;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Player extends Human {

    private final Name name;
    private final Cards cards;

    private Player(Name name) {
        this.name = name;
        this.cards = Cards.create();
    }

    public static Player of(Name name) {
        return new Player(name);
    }

    @Override
    public boolean isOneMoreCard() {
        return isOverThanMaxPoint();
    }

    @Override
    public String getName() {
        return name.getName();
    }

    @Override
    public Cards getCards() {
        return cards;
    }

    @Override
    public void addCard(Card card) {
        cards.add(card);
    }
}
