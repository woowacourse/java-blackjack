package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;

public class Fixture {
    public final static Card FIXTURE_ACE = Card.of(Denomination.ACE, Shape.CLUBS);
    public final static Card FIXTURE_THREE = Card.of(Denomination.THREE, Shape.CLUBS);
    public final static Card FIXTURE_NINE = Card.of(Denomination.NINE, Shape.CLUBS);
    public final static Card FIXTURE_KING = Card.of(Denomination.KING, Shape.CLUBS);
}
