package blackjack.domain.fixture;

import blackjack.domain.card.Card;
import blackjack.domain.card.Shape;
import blackjack.domain.card.Value;
import java.util.List;

public class CardsFixture {
    public static final List<Card> CARDS_SCORE_4 = List.of(
            new Card(Value.TWO, Shape.HEART),
            new Card(Value.TWO, Shape.SPADE)
    );
    public static final List<Card> TWO_ACE = List.of(
            new Card(Value.ACE, Shape.HEART),
            new Card(Value.ACE, Shape.SPADE)
    );
    public static final List<Card> SCORE_13_WITH_ACE = List.of(
            new Card(Value.ACE, Shape.HEART),
            new Card(Value.KING, Shape.HEART),
            new Card(Value.TWO, Shape.HEART)
    );
    public static final List<Card> CARDS_SCORE_16 = List.of(
            new Card(Value.JACK, Shape.HEART),
            new Card(Value.SIX, Shape.HEART)
    );
    public static final List<Card> CARDS_SCORE_17 = List.of(
            new Card(Value.JACK, Shape.HEART),
            new Card(Value.SEVEN, Shape.HEART)
    );
    public static final List<Card> CARDS_SCORE_21 = List.of(
            new Card(Value.JACK, Shape.HEART),
            new Card(Value.EIGHT, Shape.HEART),
            new Card(Value.THREE, Shape.HEART)
    );
    public static final List<Card> BLACKJACK = List.of(
            new Card(Value.ACE, Shape.HEART),
            new Card(Value.KING, Shape.HEART)
    );
    public static final List<Card> CARDS_SCORE_22 = List.of(
            new Card(Value.JACK, Shape.HEART),
            new Card(Value.SEVEN, Shape.HEART),
            new Card(Value.FIVE, Shape.HEART)
    );
    public static final List<Card> BUSTED = List.of(
            new Card(Value.KING, Shape.DIAMOND),
            new Card(Value.QUEEN, Shape.DIAMOND),
            new Card(Value.JACK, Shape.DIAMOND)
    );
}
