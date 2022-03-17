package domain;

import domain.card.Denomination;
import domain.card.PlayingCard;
import domain.card.Suit;

public class CardFixtures {
    public static final PlayingCard ACE_SPADES = PlayingCard.of(Suit.SPADES, Denomination.ACE);
    public static final PlayingCard TWO_SPADES = PlayingCard.of(Suit.SPADES, Denomination.TWO);
    public static final PlayingCard THREE_DIAMONDS = PlayingCard.of(Suit.DIAMONDS, Denomination.THREE);
    public static final PlayingCard FOUR_SPADES = PlayingCard.of(Suit.SPADES, Denomination.FOUR);
    public static final PlayingCard FIVE_SPADES = PlayingCard.of(Suit.SPADES, Denomination.FIVE);
    public static final PlayingCard SIX_HEARTS = PlayingCard.of(Suit.HEARTS, Denomination.SIX);
    public static final PlayingCard SEVEN_CLUBS = PlayingCard.of(Suit.CLUBS, Denomination.SEVEN);
    public static final PlayingCard TEN_HEARTS = PlayingCard.of(Suit.HEARTS, Denomination.TEN);
    public static final PlayingCard KING_HEARTS = PlayingCard.of(Suit.HEARTS, Denomination.KING);
}
