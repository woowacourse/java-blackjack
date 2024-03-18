package domain.blackjack;


import static domain.card.TestCards.ACE_HEART;
import static domain.card.TestCards.JACK_HEART;
import static domain.card.TestCards.JACK_SPADE;
import static domain.card.TestCards.NINE_HEART;
import static domain.card.TestCards.QUEEN_HEART;
import static domain.card.TestCards.SEVEN_HEART;
import static domain.card.TestCards.SIX_DIAMOND;
import static domain.card.TestCards.SIX_HEART;
import static domain.card.TestCards.TWO_HEART;

public class TestHoldingCards {
    static final HoldingCards ONLY_SIX_HEART = HoldingCards.of(SIX_HEART);
    static final HoldingCards ONLY_SEVEN_HEART = HoldingCards.of(SEVEN_HEART);
    static final HoldingCards DEAD_CARDS = HoldingCards.of(TWO_HEART, JACK_HEART, QUEEN_HEART);
    static final HoldingCards WIN_CARDS_WITH_ACE = HoldingCards.of(ACE_HEART, JACK_HEART, JACK_SPADE);
    static final HoldingCards WIN_CARDS_WITHOUT_ACE = HoldingCards.of(TWO_HEART, JACK_HEART, NINE_HEART);
    static final HoldingCards BLACK_JACK = HoldingCards.of(ACE_HEART, JACK_HEART);

    static final HoldingCards TWO_SIX_CARDS = HoldingCards.of(SIX_HEART, SIX_DIAMOND);
}
