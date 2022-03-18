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
    public static final Card CLOVER_ACE = new Card(Number.ACE, Suit.CLOVER);
    public static final Card CLOVER_TWO = new Card(Number.TWO, Suit.CLOVER);
    public static final Card CLOVER_THREE = new Card(Number.THREE, Suit.CLOVER);
    public static final Card CLOVER_FOUR = new Card(Number.FOUR, Suit.CLOVER);
    public static final Card CLOVER_FIVE = new Card(Number.FIVE, Suit.CLOVER);
    public static final Card CLOVER_SIX = new Card(Number.SIX, Suit.CLOVER);
    public static final Card CLOVER_SEVEN = new Card(Number.SEVEN, Suit.CLOVER);
    public static final Card CLOVER_EIGHT = new Card(Number.EIGHT, Suit.CLOVER);
    public static final Card CLOVER_NINE = new Card(Number.NINE, Suit.CLOVER);
    public static final Card CLOVER_TEN = new Card(Number.TEN, Suit.CLOVER);
    public static final Card CLOVER_JACK = new Card(Number.JACK, Suit.CLOVER);
    public static final Card CLOVER_QUEEN = new Card(Number.QUEEN, Suit.CLOVER);
    public static final Card CLOVER_KING = new Card(Number.KING, Suit.CLOVER);

    public static Cards getCards(Card... arguments) {
        return new Cards(Arrays.stream(arguments)
                .collect(Collectors.toList()));
    }

}
