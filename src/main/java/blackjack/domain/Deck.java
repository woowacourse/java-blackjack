package blackjack.domain;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Deck {

    private final Deque<Card> cards;

    private Deck(Deque<Card> cards) {
        this.cards = cards;
    }

    public static Deck createSuffledDeck() {
        Deque<Card> cards = new ArrayDeque<>();
        makeSuffledOrder().stream()
                .map(Card::create)
                .forEach(cards::add);
        return new Deck(cards);
    }

    private static List<Integer> makeSuffledOrder() {
        List<Integer> cardOrder = IntStream.range(0, 52)
                .boxed()
                .collect(Collectors.toList());
        Collections.shuffle(cardOrder);
        return cardOrder;
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("덱에 카드가 더이상 없습니다");
        }
        return cards.pop();
    }
}
