package blackjack.fixtures;

import static blackjack.domain.card.CardNumber.ACE;
import static blackjack.domain.card.CardNumber.EIGHT;
import static blackjack.domain.card.CardNumber.FOUR;
import static blackjack.domain.card.CardNumber.KING;
import static blackjack.domain.card.CardNumber.NINE;
import static blackjack.domain.card.CardNumber.SEVEN;
import static blackjack.domain.card.CardNumber.TEN;
import static blackjack.domain.card.CardNumber.THREE;
import static blackjack.domain.card.CardNumber.TWO;
import static blackjack.domain.card.Suit.CLUB;
import static blackjack.domain.card.Suit.SPADE;
import blackjack.domain.card.Card;
import blackjack.domain.card.HoldCards;
import blackjack.domain.entry.Dealer;

public class BlackjackFixtures {

    public static final Card SPADE_ACE = Card.valueOf(SPADE, ACE);
    public static final Card SPADE_TWO = Card.valueOf(SPADE, TWO);
    public static final Card SPADE_THREE = Card.valueOf(SPADE, THREE);
    public static final Card SPADE_FOUR = Card.valueOf(SPADE, FOUR);
    public static final Card SPADE_SEVEN = Card.valueOf(SPADE, SEVEN);
    public static final Card SPADE_EIGHT = Card.valueOf(SPADE, EIGHT);
    public static final Card SPADE_NINE = Card.valueOf(SPADE, NINE);
    public static final Card SPADE_TEN = Card.valueOf(SPADE, TEN);
    public static final Card SPADE_KING = Card.valueOf(SPADE, KING);

    public static final Card CLUB_ACE = Card.valueOf(CLUB, ACE);
    public static final Card CLUB_TWO = Card.valueOf(CLUB, TWO);
    public static final Card CLUB_EIGHT = Card.valueOf(CLUB, EIGHT);
    public static final Card CLUB_KING = Card.valueOf(CLUB, KING);

    public static final Dealer BLACKJACK_DEALER = new Dealer(HoldCards.initTwoCards(CLUB_ACE, CLUB_KING));
    public static final Dealer TEN_DEALER = new Dealer(HoldCards.initTwoCards(CLUB_TWO, CLUB_EIGHT));
}
