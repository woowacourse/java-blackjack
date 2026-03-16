package domain.card;

import domain.enums.Rank;
import domain.enums.Suit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ShuffledCardGenerator implements CardGenerator {
    public List<Card> generate() {
        List<Card> cards = new ArrayList<>(Arrays.stream(Suit.values())
                .flatMap(suit -> Arrays.stream(Rank.values())
                        .map(rank -> new Card(rank, suit)))
                .toList());

        Collections.shuffle(cards);
        return cards;
    }
}
