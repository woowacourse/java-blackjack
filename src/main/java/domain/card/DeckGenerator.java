package domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DeckGenerator {

    public static Deck generateDeck() {
        List<Card> cards = Arrays.stream(Suit.values())
                .flatMap(suit -> Arrays.stream(Rank.values())
                        .map(rank -> new Card(rank, suit)))
                .collect(Collectors.toList());
        Collections.shuffle(cards);
        return new Deck(cards);
    }
}

