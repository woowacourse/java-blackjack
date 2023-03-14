package blackjack.domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Deck {

    private static final Stack<Card> TRUMP;

    static {
        final Stack<Card> pack = new Stack<>();
        final List<Card> cards = Arrays.stream(Suit.values())
                .flatMap(suit -> Arrays.stream(Denomination.values()).map(number -> new Card(number, suit)))
                .collect(Collectors.toList());
        pack.addAll(cards);
        TRUMP = pack;
    }

    private final Stack<Card> cards;

    public Deck(final Stack<Card> cards) {
        Collections.shuffle(cards);
        this.cards = cards;
    }

    public static Deck createUsingTrump(final int count) {
        final Stack<Card> pack = new Stack<>();
        for (int i = 0; i < count; i++) {
            pack.addAll(TRUMP);
        }
        return new Deck(pack);
    }

    public Card draw() {
        if (cards.empty()) {
            throw new IllegalStateException("덱에 더 이상의 카드가 없습니다.");
        }
        return cards.pop();
    }
}
