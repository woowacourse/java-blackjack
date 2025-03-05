package blackjack;

import java.util.Arrays;
import java.util.List;

public class DeckGenerator {

    public List<Card> generate() {
        return Arrays.stream(CardSuit.values())
                .flatMap(suit -> Arrays.stream(CardRank.values())
                        .map(rank -> new Card(suit, rank)))
                .toList();
    }

}
