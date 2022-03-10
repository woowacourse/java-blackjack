package blackjack.domain.human;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Dealer extends Human {

    private final Cards cards;

    private Dealer() {
        this.cards = Cards.create();
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
        return "딜러";
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
