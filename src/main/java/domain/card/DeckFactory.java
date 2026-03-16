package domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class DeckFactory {

    private DeckFactory() {
    }

    public static Deck create() {
        List<Card> cards = IntStream.range(0, 52)
                .mapToObj(Card::new)
                .toList();

        return new Deck(new ArrayList<>(cards));
    }
}
