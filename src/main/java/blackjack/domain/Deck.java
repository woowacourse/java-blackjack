package blackjack.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Deck {

    private final Stack<Card> cards;

    private Deck(final Stack<Card> cards) {
        this.cards = cards;
    }

    public static Deck init() {
        Stack<Card> cards = new Stack<>();
        for (Suit suit : Suit.values()) {
            pushCard(cards, suit);
        }
        Collections.shuffle(cards);
        return new Deck(cards);
    }

    private static void pushCard(Stack<Card> cards, final Suit suit) {
        for (Denomination denomination : Denomination.values()) {
            cards.push(new Card(suit, denomination));
        }
    }

    public Card draw() {
        checkCardSize();
        return cards.pop();
    }

    private void checkCardSize() {
        if (size() == 0) {
            throw new IllegalStateException("[ERROR] 더이상 카드를 뽑을 수 없습니다.");
        }
    }

    public int size() {
        return cards.size();
    }
}
