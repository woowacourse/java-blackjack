package blackjack.domain.card;

import java.util.Collections;
import java.util.Stack;

public class Deck {

    public static final Stack<Card> TRUMP;

    static {
        final Stack<Card> pack = new Stack<>();
        for (final Suit suit : Suit.values()) {
            for (final Number number : Number.values()) {
                pack.add(new Card(number, suit));
            }
        }
        TRUMP = pack;
    }

    private final Stack<Card> cards;

    public Deck(final Stack<Card> cards) {
        this.cards = cards;
    }

    public Card draw() {
        if (cards.empty()) {
            throw new IllegalStateException("덱에 더 이상의 카드가 없습니다.");
        }
        return cards.pop();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }
}
