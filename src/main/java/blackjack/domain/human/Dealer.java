package blackjack.domain.human;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Dealer extends Human {

    private static final int AVAILABLE_POINT_FOR_ADD_CARD = 16;
    private static final String NAME = "딜러";
    
    private final Cards cards;

    private Dealer() {
        this.cards = Cards.create();
    }

    public static Dealer of() {
        return new Dealer();
    }

    @Override
    public boolean isOneMoreCard() {
        return cards.getPoint() <= AVAILABLE_POINT_FOR_ADD_CARD;
    }

    @Override
    public String getName() {
        return NAME;
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
