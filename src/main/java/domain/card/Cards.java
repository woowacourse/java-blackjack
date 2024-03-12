package domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Cards {
    private static final List<Card> DEFAULT_PACK;
    private static final int DECK_SIZE = 6;

    private final List<Card> value;

    static {
        DEFAULT_PACK = new ArrayList<>();
        Arrays.stream(Rank.values())
                .forEach(rank -> Arrays.stream(Suit.values())
                        .map(symbol -> new Card(rank, symbol))
                        .forEach(DEFAULT_PACK::add));
    }

    public Cards(final List<Card> value) {
        this.value = value;
    }

    public static Cards makeEmptyDeck() {
        return new Cards(new ArrayList<>());
    }


    public static Cards makeDecks() {
        final List<Card> cards = new ArrayList<>();
        IntStream.range(0, DECK_SIZE).forEach(round -> cards.addAll(DEFAULT_PACK));
        Collections.shuffle(cards);
        return new Cards(cards);
    }

    public Card draw() {
        if (value.isEmpty()) {
            throw new IllegalStateException("카드가 모두 소진되었습니다.");
        }
        return value.remove(value.size() - 1);
    }

    public void add(final Card card) {
        value.add(card);
    }

    public List<Card> getValue() {
        return value;
    }
}
