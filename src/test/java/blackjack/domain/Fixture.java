package blackjack.domain;

import java.util.Arrays;
import java.util.List;

public class Fixture {
    public static final List<Card> CARDS_SCORE_19 = Arrays.asList(
            new Card(Symbol.ACE, Shape.HEART),
            new Card(Symbol.KING, Shape.HEART),
            new Card(Symbol.EIGHT, Shape.HEART)
    );
    public static final List<Card> CARDS_SCORE_20 = Arrays.asList(
            new Card(Symbol.ACE, Shape.HEART),
            new Card(Symbol.KING, Shape.HEART),
            new Card(Symbol.NINE, Shape.HEART)
    );
    public static final List<Card> CARDS_SCORE_21 = Arrays.asList(
            new Card(Symbol.ACE, Shape.HEART),
            new Card(Symbol.KING, Shape.HEART),
            new Card(Symbol.TEN, Shape.HEART)
    );
    public static final List<Card> CARDS_SCORE_22 = Arrays.asList(
            new Card(Symbol.JACK, Shape.HEART),
            new Card(Symbol.TEN, Shape.HEART),
            new Card(Symbol.TWO, Shape.HEART)
    );
    public static final List<Card> CARDS_SCORE_BLACKJACK = Arrays.asList(
            new Card(Symbol.ACE, Shape.HEART),
            new Card(Symbol.KING, Shape.HEART)
    );
}
