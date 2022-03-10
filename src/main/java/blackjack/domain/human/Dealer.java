package blackjack.domain.human;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Dealer extends Human {
    public static final String DEALER_NAME = "딜러";
    private final Cards cards;

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
        return DEALER_NAME;
    }

    @Override
    public void addCard(final Card card) {
        cards.add(card);
    }

    @Override
    public Cards getCards() {
        return cards;
    }
}
