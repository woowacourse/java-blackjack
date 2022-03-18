package utils;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Number;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.BettingAmount;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TestUtil {

    public static final BettingAmount BETTING_1000 = new BettingAmount(1000L);
    public static final Card CLOVER_ACE = Card.of(Number.ACE, Suit.CLOVER);
    public static final Card CLOVER_TWO = Card.of(Number.TWO, Suit.CLOVER);
    public static final Card CLOVER_THREE = Card.of(Number.THREE, Suit.CLOVER);
    public static final Card CLOVER_FOUR = Card.of(Number.FOUR, Suit.CLOVER);
    public static final Card CLOVER_FIVE = Card.of(Number.FIVE, Suit.CLOVER);
    public static final Card CLOVER_SIX = Card.of(Number.SIX, Suit.CLOVER);
    public static final Card CLOVER_SEVEN = Card.of(Number.SEVEN, Suit.CLOVER);
    public static final Card CLOVER_EIGHT = Card.of(Number.EIGHT, Suit.CLOVER);
    public static final Card CLOVER_NINE = Card.of(Number.NINE, Suit.CLOVER);
    public static final Card CLOVER_TEN = Card.of(Number.TEN, Suit.CLOVER);
    public static final Card CLOVER_JACK = Card.of(Number.JACK, Suit.CLOVER);
    public static final Card CLOVER_QUEEN = Card.of(Number.QUEEN, Suit.CLOVER);
    public static final Card CLOVER_KING = Card.of(Number.KING, Suit.CLOVER);

    public static Cards getCards(Card... arguments) {
        return new Cards(Arrays.stream(arguments)
                .collect(Collectors.toList()));
    }

}
