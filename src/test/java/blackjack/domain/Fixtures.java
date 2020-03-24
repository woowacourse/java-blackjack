package blackjack.domain;

import blackjack.domain.card.PlayingCard;

import static blackjack.domain.card.Denomination.*;
import static blackjack.domain.card.Suit.CLUBS;

public class Fixtures {
    public static final PlayingCard CLUBS_ACE = PlayingCard.of(CLUBS, ACE);
    public static final PlayingCard CLUBS_TWO = PlayingCard.of(CLUBS, TWO);
    public static final PlayingCard CLUBS_TEN = PlayingCard.of(CLUBS, TEN);
    public static final PlayingCard CLUBS_KING = PlayingCard.of(CLUBS, KING);
}
