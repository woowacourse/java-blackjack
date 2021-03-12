package blackjack.utils;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denominations;
import blackjack.domain.card.Suits;

import java.util.*;

public class FixedCardDeck implements CardDeck {
    private final Queue<Card> cards;

    public FixedCardDeck() {
        cards = new LinkedList<>();
        Arrays.stream(Suits.values())
                .forEach(suit -> Arrays.stream(Denominations.values())
                        .forEach(denomination -> cards.offer(Card.from(denomination.getName() + suit.getName()))));
    }

    @Override
    public Card pop() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return cards.poll();
    }

    @Override
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    @Override
    public List<Card> initCards() {
        List<Card> cardsValue = new ArrayList<>();
        cardsValue.add(pop());
        cardsValue.add(pop());
        return cardsValue;
    }
}
