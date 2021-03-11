package blackjack.domain.utils;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denominations;
import blackjack.domain.card.Suits;

import java.util.*;

public class FixedCardDeck implements CardDeck {
    private final Queue<Card> cards;

    public FixedCardDeck() {
        LinkedList<Card> cardsValue = new LinkedList<>();

        for (Suits suit : Suits.values()) {
            assembleWithDenominations(cardsValue, suit);
        }

        this.cards = cardsValue;
    }

    @Override
    public void assembleWithDenominations(LinkedList<Card> cardsValue, Suits suit) {
        for (Denominations denomination : Denominations.values()) {
            cardsValue.offer(Card.from(denomination, suit));
        }
    }

    @Override
    public Card pop() {
        if(isEmpty()){
            throw new IllegalArgumentException();
        }
        return cards.poll();
    }

    @Override
    public boolean isEmpty() {
        return cards.isEmpty();
    }


}
