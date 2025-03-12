package blackjack.test_util;

import static blackjack.domain.card.CardShape.*;

import blackjack.domain.card.Card;
import blackjack.domain.Player;
import blackjack.domain.card.CardNumber;

public class TestConstants {
    
    public static final Card HEART_1 = new Card(CardNumber.ACE, HEART);
    public static final Card HEART_2 = new Card(CardNumber.TWO, HEART);
    public static final Card HEART_3 = new Card(CardNumber.THREE, HEART);
    public static final Card HEART_4 = new Card(CardNumber.FOUR, HEART);
    public static final Card HEART_5 = new Card(CardNumber.FIVE, HEART);
    public static final Card HEART_6 = new Card(CardNumber.SIX, HEART);
    public static final Card HEART_7 = new Card(CardNumber.SEVEN, HEART);
    public static final Card HEART_8 = new Card(CardNumber.EIGHT, HEART);
    public static final Card HEART_9 = new Card(CardNumber.NINE, HEART);
    public static final Card HEART_10 = new Card(CardNumber.TEN, HEART);
    public static final Card HEART_11 = new Card(CardNumber.JACK, HEART);
    public static final Card HEART_12 = new Card(CardNumber.QUEEN, HEART);
    public static final Card HEART_13 = new Card(CardNumber.KING, HEART);
    
    public static final Card DIAMOND_1 = new Card(CardNumber.ACE, DIAMOND);
    public static final Card DIAMOND_5 = new Card(CardNumber.FIVE, DIAMOND);
    public static final Card DIAMOND_9 = new Card(CardNumber.NINE, DIAMOND);
    public static final Card DIAMOND_10 = new Card(CardNumber.TEN, DIAMOND);
    public static final Card CLOVER_10 = new Card(CardNumber.TEN, CLOVER);
    
    public static final Player DEFAULT_PLAYER = new Player("dompoo");
}
