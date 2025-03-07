package domain.deck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DeckGenerator {

    public List<Card> generate() {
        return Arrays.stream(Shape.values())
                .flatMap(shape -> Arrays.stream(Rank.values())
                        .map(rank -> new Card(rank, shape)))
                .toList();
    }

    public List<Card> shuffle(final List<Card> cards, final Random random) {
        final List<Card> cardGroup = new ArrayList<>(cards);
        Collections.shuffle(cardGroup, random);
        return cardGroup;
    }
}
