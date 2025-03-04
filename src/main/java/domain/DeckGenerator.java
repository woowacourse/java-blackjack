package domain;

import java.util.Arrays;
import java.util.List;

public class DeckGenerator {


    public List<Card> generate() {
        return Arrays.stream(Shape.values())
                .flatMap((shape) -> Arrays.stream(Rank.values())
                        .map(rank -> new Card(rank, shape)))
                .toList();
    }
}
