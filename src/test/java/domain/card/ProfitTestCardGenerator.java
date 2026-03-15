package domain.card;

import domain.enums.Rank;
import domain.enums.Suit;
import java.util.List;

public class ProfitTestCardGenerator implements CardGenerator {
    public List<Card> generate() {
        return List.of(new Card(Rank.SIX, Suit.CLOVER), new Card(Rank.QUEEN, Suit.HEART),
                new Card(Rank.ACE, Suit.CLOVER), new Card(Rank.TEN, Suit.CLOVER),
                new Card(Rank.TEN, Suit.SPADE), new Card(Rank.SEVEN, Suit.HEART),
                new Card(Rank.QUEEN, Suit.SPADE), new Card(Rank.KING, Suit.HEART),
                new Card(Rank.NINE, Suit.SPADE), new Card(Rank.NINE, Suit.HEART));
    }
}
