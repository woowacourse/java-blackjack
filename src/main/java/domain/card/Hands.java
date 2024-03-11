package domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Hands {
    private static final List<Card> CASHED_CARDS;

    private final List<Card> value;

    static {
        CASHED_CARDS = new ArrayList<>();
        for (final Rank rank : Rank.values()) {
            Arrays.stream(Suit.values()).map(symbol -> new Card(rank, symbol)).forEach(CASHED_CARDS::add);
        }
    }

    public Hands(final List<Card> value) {
        this.value = value;
    }

    public static Hands makeOnePack() {
        final List<Card> onePack = new ArrayList<>(CASHED_CARDS);
        Collections.shuffle(onePack);
        return new Hands(onePack);
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

}
