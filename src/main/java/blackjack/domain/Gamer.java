package blackjack.domain;

import java.util.List;

public abstract class Gamer {
    protected final Cards cards;
    private final String name;

    protected Gamer(String name) {
        this.name = name;
        this.cards = new Cards();
    }

    public void deal(CardPicker cardPicker) {
        cardPicker.pick(2)
                .forEach(cards::addCard);
    }

    public void hit(CardPicker cardPicker) {
        cardPicker.pick(1)
                .forEach(cards::addCard);
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards.getValues();
    }
}
