package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fixtures {
    public static final Card CLUB_ACE = Card.of(Suit.CLUB, Denomination.ACE);
    public static final Card CLUB_TWO = Card.of(Suit.CLUB, Denomination.TWO);
    public static final Card CLUB_TEN = Card.of(Suit.CLUB, Denomination.TEN);
    public static final Card CLUB_KING = Card.of(Suit.CLUB, Denomination.KING);
    public static final List<Card> HIT = new ArrayList<>(
            Arrays.asList(
                    Card.of(Suit.SPADE, Denomination.ACE),
                    Card.of(Suit.CLUB, Denomination.THREE)
            ));
    public static final List<Card> BLACKJACK = new ArrayList<>(
            Arrays.asList(
                    Card.of(Suit.SPADE, Denomination.ACE),
                    Card.of(Suit.CLUB, Denomination.TEN)
            ));
    public static final List<Card> BUST = new ArrayList<>(
            Arrays.asList(
                    Card.of(Suit.SPADE, Denomination.TEN),
                    Card.of(Suit.CLUB, Denomination.TEN)
            ));
}
