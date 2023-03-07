package domain.card;

import domain.card.shuffler.CardsShuffler;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

public final class Cards {

    private final Stack<Card> cards;

    public Cards(CardsShuffler shuffler) {
        this.cards = shuffler.shuffleCards(initializeCards());
    }

    private static Stack<Card> initializeCards() {
        Stack<Card> cards = new Stack<>();
        for (Value value : Value.values()) {
            for (Shape shape : Shape.values()) {
                cards.push(new Card(value, shape));
            }
        }
        return cards;
    }

    public List<Card> giveInitialCards() {
        return new ArrayList<>(List.of(getCard(), getCard()));
    }

    public boolean contains(final Card card) {
        return cards.contains(card);
    }

    public Card getCard() {
        try {
            return cards.pop();
        } catch (EmptyStackException exception) {
            throw new EmptyStackException();
        }
    }
}
