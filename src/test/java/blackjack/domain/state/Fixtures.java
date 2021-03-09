package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;

public class Fixtures {
    public static final Card CLUB_ACE = Card.of(Suit.CLUB, Denomination.ACE);
    public static final Card CLUB_TWO = Card.of(Suit.CLUB, Denomination.TWO);
    public static final Card CLUB_TEN = Card.of(Suit.CLUB, Denomination.TEN);
    public static final Card CLUB_KING = Card.of(Suit.CLUB, Denomination.KING);
}
