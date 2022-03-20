package blackjack.domain;

import blackjack.domain.card.Denomination;
import blackjack.domain.card.PlayingCard;
import blackjack.domain.card.PlayingCards;
import blackjack.domain.card.Suit;
import blackjack.domain.player.Guest;

public class Fixtures {

    public static final PlayingCard SPADE_ACE = new PlayingCard(Suit.SPADE, Denomination.ACE);
    public static final PlayingCard CLUB_ACE = new PlayingCard(Suit.CLUB, Denomination.ACE);
    public static final PlayingCard DIAMOND_ACE = new PlayingCard(Suit.DIAMOND, Denomination.ACE);
    public static final PlayingCard HEART_ACE = new PlayingCard(Suit.HEART, Denomination.ACE);

    public static final PlayingCard SPADE_TWO = new PlayingCard(Suit.SPADE, Denomination.TWO);
    public static final PlayingCard SPADE_NINE = new PlayingCard(Suit.SPADE, Denomination.NINE);
    public static final PlayingCard SPADE_EIGHT = new PlayingCard(Suit.SPADE, Denomination.EIGHT);
    public static final PlayingCard SPADE_JACK = new PlayingCard(Suit.SPADE, Denomination.JACK);

    public static final Guest guest = new Guest("guest", new PlayingCards(), 100);
}
