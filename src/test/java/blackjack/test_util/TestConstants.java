package blackjack.test_util;

import blackjack.domain.card.Card;
import blackjack.domain.player.Player;

import static blackjack.domain.card.CardNumber.*;
import static blackjack.domain.card.CardShape.다이아몬드;
import static blackjack.domain.card.CardShape.하트;

public class TestConstants {
    
    public static final Card DIAMOND_1 = new Card(NUMBER_A, 다이아몬드);
    public static final Card DIAMOND_5 = new Card(NUMBER_5, 다이아몬드);
    public static final Card DIAMOND_9 = new Card(NUMBER_9, 다이아몬드);
    public static final Card DIAMOND_10 = new Card(NUMBER_10, 다이아몬드);
    public static final Card HEART_1 = new Card(NUMBER_A, 하트);
    public static final Card HEART_2 = new Card(NUMBER_2, 하트);
    public static final Card HEART_3 = new Card(NUMBER_3, 하트);
    public static final Card HEART_4 = new Card(NUMBER_4, 하트);
    public static final Card HEART_5 = new Card(NUMBER_5, 하트);
    public static final Card HEART_6 = new Card(NUMBER_6, 하트);
    public static final Card HEART_7 = new Card(NUMBER_7, 하트);
    public static final Card HEART_8 = new Card(NUMBER_8, 하트);
    public static final Card HEART_9 = new Card(NUMBER_9, 하트);
    public static final Card HEART_10 = new Card(NUMBER_10, 하트);
    public static final Card HEART_11 = new Card(NUMBER_J, 하트);
    public static final Card HEART_12 = new Card(NUMBER_Q, 하트);
    public static final Card HEART_13 = new Card(NUMBER_K, 하트);
    
    public static final Player DEFAULT_PLAYER = new Player("dompoo");
}
