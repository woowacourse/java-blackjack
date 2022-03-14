package blackjack.domain;

import java.util.Collections;
import java.util.Stack;
import java.util.stream.Stream;


public class Deck {

    private final static String RUN_OUT_OF_CARDS_ERROR_MESSAGE = "[ERROR] 카드를 모두 사용하였습니다.";

    private final Stack<Card> cards = new Stack<>();

    public Deck() {
        for (Suit suit : Suit.values()) {
            Stream.of(Denomination.values())
                .forEach(denomination -> cards.push(new Card(suit, denomination)));
        }
        Collections.shuffle(cards);
    }

    public Card pickCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException(RUN_OUT_OF_CARDS_ERROR_MESSAGE);
        }
        return cards.pop();
    }
}
