package blackjack.util;

import static blackjack.util.CardFixture.ACE_SPADE;
import static blackjack.util.CardFixture.EIGHT_SPADE;
import static blackjack.util.CardFixture.FIVE_SPADE;
import static blackjack.util.CardFixture.FOUR_SPADE;
import static blackjack.util.CardFixture.KING_HEART;
import static blackjack.util.CardFixture.KING_SPADE;
import static blackjack.util.CardFixture.NINE_SPADE;
import static blackjack.util.CardFixture.SEVEN_SPADE;
import static blackjack.util.CardFixture.SIX_SPADE;
import static blackjack.util.CardFixture.TEN_SPADE;
import static blackjack.util.CardFixture.THREE_SPADE;
import static blackjack.util.CardFixture.TWO_SPADE;

import blackjack.domain.card.Card;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class CardsFixture {
    public static final List<Card> BLACKJACK = List.of(ACE_SPADE, KING_SPADE);
    public static final List<Card> BUST = List.of(KING_HEART, KING_SPADE, TWO_SPADE);

    private static final Map<Integer, List<Card>> CARDS = Map.of(
            12, List.of(TEN_SPADE, TWO_SPADE),
            13, List.of(TEN_SPADE, THREE_SPADE),
            14, List.of(TEN_SPADE, FOUR_SPADE),
            15, List.of(TEN_SPADE, FIVE_SPADE),
            16, List.of(TEN_SPADE, SIX_SPADE),
            17, List.of(TEN_SPADE, SEVEN_SPADE),
            18, List.of(TEN_SPADE, EIGHT_SPADE),
            19, List.of(TEN_SPADE, NINE_SPADE),
            20, List.of(TEN_SPADE, SIX_SPADE, FOUR_SPADE),
            21, List.of(TEN_SPADE, EIGHT_SPADE, THREE_SPADE)
    );

    public static List<Card> valueOf(final int score) {
        if (score <= 11) {
            throw new NoSuchElementException();
        }
        if (score >= 22) {
            return BUST;
        }
        return CARDS.get(score);
    }
}
