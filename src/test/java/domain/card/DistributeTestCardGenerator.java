package domain.card;

import domain.enums.Rank;
import domain.enums.Suit;
import java.util.List;

public class DistributeTestCardGenerator implements CardGenerator {
    public List<Card> generate() {
        return List.of(new Card(Rank.FIVE, Suit.CLOVER), new Card(Rank.FIVE, Suit.HEART),
                new Card(Rank.SIX, Suit.CLOVER), new Card(Rank.SEVEN, Suit.CLOVER),
                new Card(Rank.FOUR, Suit.SPADE), new Card(Rank.SEVEN, Suit.HEART));
    }
}
