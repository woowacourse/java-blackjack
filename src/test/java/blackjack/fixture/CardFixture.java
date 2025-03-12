package blackjack.fixture;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;

public class CardFixture {
    public static final Card DIAMOND_ACE = new Card(Suit.DIAMOND, Rank.ACE);
    public static final Card DIAMOND_ONE = new Card(Suit.DIAMOND, Rank.ONE);
    public static final Card DIAMOND_TWO = new Card(Suit.DIAMOND, Rank.TWO);
    public static final Card DIAMOND_THREE = new Card(Suit.DIAMOND, Rank.THREE);
    public static final Card DIAMOND_FOUR = new Card(Suit.DIAMOND, Rank.FOUR);
    public static final Card DIAMOND_FIVE = new Card(Suit.DIAMOND, Rank.FIVE);
    public static final Card DIAMOND_EIGHT = new Card(Suit.DIAMOND, Rank.EIGHT);
    public static final Card DIAMOND_NINE = new Card(Suit.DIAMOND, Rank.NINE);
    public static final Card DIAMOND_TEN = new Card(Suit.DIAMOND, Rank.TEN);
    public static final Card DIAMOND_KING = new Card(Suit.DIAMOND, Rank.KING);

    public static final Card CLUB_ONE = new Card(Suit.CLUB, Rank.ONE);
    public static final Card CLUB_FOUR = new Card(Suit.CLUB, Rank.FOUR);
    public static final Card CLUB_FIVE = new Card(Suit.CLUB, Rank.FIVE);
    public static final Card CLUB_NINE = new Card(Suit.CLUB, Rank.NINE);
    public static final Card CLUB_TEN = new Card(Suit.CLUB, Rank.TEN);

    public static final Card HEART_ONE = new Card(Suit.HEART, Rank.ONE);
    public static final Card HEART_THREE = new Card(Suit.HEART, Rank.THREE);
    public static final Card HEART_NINE = new Card(Suit.HEART, Rank.NINE);
    public static final Card HEART_TEN = new Card(Suit.HEART, Rank.TEN);

    public static final Card SPADE_ONE = new Card(Suit.SPADE, Rank.ONE);
}
