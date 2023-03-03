package domain.card;

import domain.card.shuffler.CardsShuffler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Cards {

    private final Stack<Card> cards;

    public Cards(CardsShuffler shuffler) {
        this.cards = shuffler.shuffleCards(initializeCards());
    }

    private static Stack<Card> initializeCards() {
        Stack<Card> cards = new Stack<>();
        Arrays.stream(Value.values())
                .map(Value::getValue)
                .forEach(value -> Arrays.stream(Shape.values())
                        .map(Shape::getShape)
                        .forEach(shape -> cards.push(new Card(value, shape))));
        return cards;
    }

    public List<Card> getInitialCards() {
        return new ArrayList<>(List.of(cards.pop(), cards.pop()));
    }

    public boolean contains(final Card card) {
        return cards.contains(card);
    }

    public Card getCard() {
        return cards.pop();
    }
}
