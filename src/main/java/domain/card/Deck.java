package domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class Deck {
    private static final Deque<Card> DEFAULT_PACK;
    private static final int DECK_SIZE = 6;

    private final Deque<Card> value;

    static {
        DEFAULT_PACK = new LinkedList<>();
        Arrays.stream(Rank.values())
                .forEach(rank -> Arrays.stream(Suit.values())
                        .map(symbol -> new Card(rank, symbol))
                        .forEach(DEFAULT_PACK::add));
    }

    public Deck(final LinkedList<Card> value) {
        this.value = value;
    }

    public static Deck makeDecks() {
        final LinkedList<Card> cards = new LinkedList<>();
        IntStream.range(0, DECK_SIZE).forEach(round -> cards.addAll(DEFAULT_PACK));
        Collections.shuffle(cards);
        return new Deck(cards);
    }

    public Card draw() {
        validateEmptiness();
        return value.removeLast();
    }

    private void validateEmptiness() {
        if (value.isEmpty()) {
            throw new IllegalStateException("카드가 모두 소진되어 더이상 카드를 뽑을 수 없습니다");
        }
    }

    public void add(final Card card) {
        value.add(card);
    }

    public List<Card> getValue() {
        return value.stream().toList();
    }
}
