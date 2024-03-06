package model.dealer;

import java.util.List;
import model.card.Card;
import model.card.Cards;

public class Dealer {

    private static final int ADD_CARD_CONDITION = 17;

    private final Cards cards;

    public Dealer() {
        this(new Cards(List.of()));
    }

    public Dealer(Cards cards) {
        this.cards = cards;
    }

    public boolean isPossibleAddCard() {
        int totalNumbers = cards.calculateTotalNumbers();
        return totalNumbers < ADD_CARD_CONDITION;
    }

    public Dealer addCard(Card card) {
        Cards addedCards = cards.add(card);
        return new Dealer(addedCards);
    }

    public int cardsSize() {
        return cards.size();
    }
}
