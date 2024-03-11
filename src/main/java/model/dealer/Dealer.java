package model.dealer;

import java.util.List;
import model.card.Card;
import model.card.Cards;

public class Dealer {

    private static final int HIT_CONDITION = 17;

    private final Cards cards;

    public Dealer() {
        this(new Cards(List.of()));
    }

    public Dealer(Cards cards) {
        this.cards = cards;
    }

    public boolean isPossibleHit() {
        int totalNumbers = cards.calculateTotalNumbers();
        return totalNumbers < HIT_CONDITION;
    }

    public Dealer hitCard(Card card) {
        Cards addedCards = cards.add(card);
        return new Dealer(addedCards);
    }

    public Dealer hitCards(List<Card> cards) {
        Cards addedCards = this.cards.addAll(cards);
        return new Dealer(addedCards);
    }

    public int cardsSize() {
        return cards.size();
    }

    public Cards getCards() {
        return cards;
    }
}
