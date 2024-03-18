package blackjack.domain.fixture;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;

public class FixtureCard {
    public static final Card TWO_HEART = Card.of(Rank.TWO, Suit.HEARTS);
    public static final Card SIX_HEART = Card.of(Rank.SIX, Suit.HEARTS);
    public static final Card SEVEN_HEART = Card.of(Rank.SEVEN, Suit.HEARTS);
    public static final Card TEN_HEART = Card.of(Rank.TEN, Suit.HEARTS);
    public static final Card ACE_HEART = Card.of(Rank.ACE, Suit.HEARTS);
}
