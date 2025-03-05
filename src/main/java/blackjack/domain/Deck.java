package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Deck {

    private final Stack<Card> cards;
    private final CardsShuffler cardsShuffler;

    public Deck(Stack<Card> cards, CardsShuffler cardsShuffler) {
        this.cards = cards;
        this.cardsShuffler = cardsShuffler;
    }

    public Stack<Card> getCards() {
        return cards;
    }

    public Card draw() {
        return cards.pop();
    }

    public void shuffleCards() {
        List<Card> shuffled = new ArrayList<>();
        while (!cards.isEmpty()) {
            shuffled.add(cards.pop());
        }
        cardsShuffler.shuffle(cards);
        cards.addAll(shuffled);
    }
}
