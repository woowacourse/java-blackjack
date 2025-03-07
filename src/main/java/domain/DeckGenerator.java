package domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class DeckGenerator {

    public List<Card> generate() {
        return Arrays.stream(Shape.values())
                .flatMap(shape -> Arrays.stream(Rank.values())
                        .map(rank -> new Card(rank, shape)))
                .collect(Collectors.toList());
    }

    public void shuffle(final List<Card> cards, final Random random) {
        Collections.shuffle(cards, random);
    }
}
