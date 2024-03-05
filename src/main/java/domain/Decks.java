package domain;

import java.util.List;
import java.util.stream.Stream;

public class Decks {
    private static final int DECK_COUNT = 6;
    private final List<Deck> decks;

    public Decks() {
        this.decks = Stream.generate(Deck::new)
                .limit(DECK_COUNT)
                .toList();
    }

    public List<Deck> getDecks() {
        return decks;
    }

    public Card draw(int index) {
        return decks.get(index).draw();
    }
}
