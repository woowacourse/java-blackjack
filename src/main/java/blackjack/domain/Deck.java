package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Deck {

    private final Stack<Card> cards;

    public Deck(Stack<Card> cards) {
        this.cards = cards;
    }

    public Stack<Card> getCards() {
        return cards;
    }

    public Card draw() {
        return cards.pop();
    }

    public void shuffleCards(CardsShuffler cardsShuffler) {
        List<Card> shuffledCard = new ArrayList<>();
        while (!cards.isEmpty()) {
            shuffledCard.add(cards.pop());
        }
        cardsShuffler.shuffle(shuffledCard);
        Collections.reverse(shuffledCard);
        cards.addAll(shuffledCard);
    }

    public Card peek() {
        return cards.peek();
    }
}
