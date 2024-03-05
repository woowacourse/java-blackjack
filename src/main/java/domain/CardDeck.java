package domain;

import java.util.Collections;
import java.util.Stack;

public class CardDeck {
    private static final int DECK_SIZE = 6;

    private Stack<Card> cards;

    public CardDeck(final Stack<Card> cards) {
        this.cards = cards;
    }

    public static CardDeck generate() {
        Stack<Card> result = new Stack<>();

        for (int i = 0; i < DECK_SIZE; i++) {
            result.addAll(generateOneCardDeck());
        }

        return new CardDeck(result);
    }

    private static Stack<Card> generateOneCardDeck() { //TODO 인덴트 줄이기
        Stack<Card> cards = new Stack<>();

        for (CardShape cardShape : CardShape.values()) {
            for (CardNumber cardNumber : CardNumber.values()) {
                cards.add(new Card(cardNumber, cardShape));
            }
        }

        return cards;
    }

    public Card pop() {
        return cards.pop();
    }

    public int size() {
        return cards.size();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }
}
