package blackjack.domain.human;

import blackjack.domain.Card;
import blackjack.domain.Cards;

public class Dealer extends Human {
    private final Cards cards;
    private final String name = "딜러";

    private Dealer() {
        this.cards = Cards.of();
    }

    public static Dealer of() {
        return new Dealer();
    }

    @Override
    public boolean isOneMoreCard() {
        return cards.getPoint() <= 16;
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
