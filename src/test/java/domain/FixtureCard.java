package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;

public class FixtureCard {
    public static final Card TWO_HEARTS = Card.of(Rank.TWO, Suit.HEARTS);
    public static final Card SIX_HEARTS = Card.of(Rank.SIX, Suit.HEARTS);
    public static final Card SEVEN_HEARTS = Card.of(Rank.SEVEN, Suit.HEARTS);
    public static final Card TEN_HEARTS = Card.of(Rank.TEN, Suit.HEARTS);
    public static final Card ACE_HEARTS = Card.of(Rank.ACE, Suit.HEARTS);
}

