package blackjack.test_util;

import static blackjack.domain.card.CardShape.*;

import blackjack.domain.card.Card;
import blackjack.domain.Player;

import java.util.List;

public class TestConstants {
    
    private static final List<Card> CARDS = Card.createTrumpCards();

    public static final Card HEART_1 = CARDS.get(0);
    public static final Card HEART_2 = CARDS.get(1);
    public static final Card HEART_3 = CARDS.get(2);
    public static final Card HEART_4 = CARDS.get(3);
    public static final Card HEART_5 = CARDS.get(4);
    public static final Card HEART_6 = CARDS.get(5);
    public static final Card HEART_7 = CARDS.get(6);
    public static final Card HEART_8 = CARDS.get(7);
    public static final Card HEART_9 = CARDS.get(8);
    public static final Card HEART_10 = CARDS.get(9);
    public static final Card HEART_11 = CARDS.get(10);
    public static final Card HEART_12 = CARDS.get(11);
    public static final Card HEART_13 = CARDS.get(12);
    
    public static final Card DIAMOND_1 = CARDS.get(13);
    public static final Card DIAMOND_5 = CARDS.get(17);
    public static final Card DIAMOND_9 = CARDS.get(21);
    public static final Card DIAMOND_10 = CARDS.get(22);
    public static final Card CLOVER_10 = CARDS.get(49);
    
    public static final Player DEFAULT_PLAYER = new Player("dompoo");
}
