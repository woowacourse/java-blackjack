package blackjack.domain.human;

import blackjack.domain.Card;
import blackjack.domain.Cards;
import blackjack.domain.Name;

public class Player extends Human {
    private final Name name;
    private final Cards cards;
    private boolean isCardNeeded = true;

    private Player(Name name) {
        this.name = name;
        this.cards = Cards.of();
    }

    public static Player of(Name name) {
        return new Player(name);
    }

    public void toNoCardNeeded() {
        isCardNeeded = false;
    }

    @Override
    public boolean isOneMoreCard() {
        return cards.getPoint() < 21 && isCardNeeded;
    }

    @Override
    public String getName() {
        return name.getName();
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
