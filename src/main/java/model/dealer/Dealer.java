package model.dealer;

import java.util.List;
import model.card.Card;
import model.card.Hand;

public class Dealer {

    private static final int HIT_CONDITION = 17;

    private final Hand cards;

    public Dealer() {
        this(new Hand(List.of()));
    }

    public Dealer(Hand cards) {
        this.cards = cards;
    }

    public boolean isPossibleHit() {
        int totalNumbers = cards.calculateTotalNumbers();
        return totalNumbers < HIT_CONDITION;
    }

    public Dealer hitCard(Card card) {
        Hand addedCards = cards.add(card);
        return new Dealer(addedCards);
    }

    public Dealer hitCards(List<Card> cards) {
        Hand addedCards = this.cards.addAll(cards);
        return new Dealer(addedCards);
    }

    public int cardsSize() {
        return cards.size();
    }

    public Hand getCards() {
        return cards;
    }
}
