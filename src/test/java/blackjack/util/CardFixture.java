package blackjack.util;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Shape;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class CardFixture {
    public static final Card ACE_SPADE = new Card(Rank.ACE, Shape.SPADE);
    public static final Card TWO_SPADE = new Card(Rank.TWO, Shape.SPADE);
    public static final Card THREE_SPADE = new Card(Rank.THREE, Shape.SPADE);
    public static final Card FOUR_SPADE = new Card(Rank.FOUR, Shape.SPADE);
    public static final Card FIVE_SPADE = new Card(Rank.FIVE, Shape.SPADE);
    public static final Card SIX_SPADE = new Card(Rank.SIX, Shape.SPADE);
    public static final Card SEVEN_SPADE = new Card(Rank.SEVEN, Shape.SPADE);
    public static final Card EIGHT_SPADE = new Card(Rank.EIGHT, Shape.SPADE);
    public static final Card NINE_SPADE = new Card(Rank.NINE, Shape.SPADE);
    public static final Card TEN_SPADE = new Card(Rank.TEN, Shape.SPADE);
    public static final Card JACK_SPADE = new Card(Rank.JACK, Shape.SPADE);
    public static final Card QUEEN_SPADE = new Card(Rank.QUEEN, Shape.SPADE);
    public static final Card KING_SPADE = new Card(Rank.KING, Shape.SPADE);

    public static final Card ACE_HEART = new Card(Rank.ACE, Shape.HEART);
    public static final Card TWO_HEART = new Card(Rank.TWO, Shape.HEART);
    public static final Card SIX_HEART = new Card(Rank.SIX, Shape.HEART);
    public static final Card NINE_HEART = new Card(Rank.NINE, Shape.HEART);
    public static final Card JACK_HEART = new Card(Rank.JACK, Shape.HEART);
    public static final Card KING_HEART = new Card(Rank.KING, Shape.HEART);

    public static final Card ACE_CLOVER = new Card(Rank.ACE, Shape.CLOVER);
    public static final Card NINE_CLOVER = new Card(Rank.NINE, Shape.CLOVER);
    public static final Card JACK_CLOVER = new Card(Rank.JACK, Shape.CLOVER);

    public static final Card ACE_DIAMOND = new Card(Rank.ACE, Shape.DIAMOND);

    public static final List<Card> BLACKJACK = List.of(ACE_SPADE, KING_SPADE);
    public static final List<Card> BUST = List.of(KING_HEART, KING_SPADE, TWO_SPADE);

    private static final Map<Integer, List<Card>> CARDS = Map.of(
            12, List.of(TEN_SPADE, TWO_SPADE),
            13, List.of(TEN_SPADE, THREE_SPADE),
            14, List.of(TEN_SPADE, FOUR_SPADE),
            15, List.of(TEN_SPADE, FIVE_SPADE),
            16, List.of(TEN_SPADE, SIX_SPADE),
            17, List.of(TEN_SPADE, SEVEN_SPADE),
            18, List.of(TEN_SPADE, EIGHT_SPADE),
            19, List.of(TEN_SPADE, NINE_SPADE),
            20, List.of(TEN_SPADE, SIX_SPADE, FOUR_SPADE),
            21, List.of(TEN_SPADE, EIGHT_SPADE, THREE_SPADE)
    );

    public static List<Card> valueOf(final int score) {
        if (score <= 11) {
            throw new NoSuchElementException();
        }
        if (score >= 22) {
            return BUST;
        }
        return CARDS.get(score);
    }
}
