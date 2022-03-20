package domain.card;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InitCards {

    public static final int INIT_CARDS_SIZE = 2;

    private final List<Card> cards;

    public InitCards(Deck deck) {
        this.cards = drawCards(deck);
    }

    private List<Card> drawCards(Deck deck) {
        return IntStream.range(0, INIT_CARDS_SIZE)
                .mapToObj(i -> deck.draw())
                .collect(Collectors.toList());
    }

    public List<Card> getInitCards() {
        return Collections.unmodifiableList(cards);
    }
}
