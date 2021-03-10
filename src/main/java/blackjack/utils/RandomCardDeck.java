package blackjack.utils;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denominations;
import blackjack.domain.card.Suits;

import java.util.*;

public class RandomCardDeck implements CardDeck {
    private final Queue<Card> cards;

    public RandomCardDeck() {
        LinkedList<Card> cardsValue = new LinkedList<>();
        for (Suits suit : Suits.values()) {
            assembleWithDenominations(cardsValue, suit);
        }
        Collections.shuffle(cardsValue);
        this.cards = cardsValue;
    }

    @Override
    public void assembleWithDenominations(LinkedList<Card> cardsValue, Suits suit) {
        for (Denominations denomination : Denominations.values()) {
            cardsValue.add(Card.from(denomination, suit));
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
