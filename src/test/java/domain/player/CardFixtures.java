package domain.player;

import domain.card.Denomination;
import domain.card.PlayingCard;
import domain.card.Suit;

public class CardFixtures {
    static final PlayingCard A_SPADES = PlayingCard.of(Suit.SPADES, Denomination.ACE);
    static final PlayingCard TEN_HEARTS = PlayingCard.of(Suit.HEARTS, Denomination.TEN);
    static final PlayingCard SEVEN_CLUBS = PlayingCard.of(Suit.CLUBS, Denomination.SEVEN);
}
