package blackjack.test_util;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.player.Player;
import com.google.common.collect.Table;

import static blackjack.domain.card.CardNumber.*;
import static blackjack.domain.card.CardShape.다이아몬드;
import static blackjack.domain.card.CardShape.하트;

public class TestConstants {
    
    private static final Table<CardNumber, CardShape, Card> CARDS = Card.createTrumpCards();
    
    public static final Card DIAMOND_1 = CARDS.get(NUMBER_A, 다이아몬드);
    public static final Card DIAMOND_5 = CARDS.get(NUMBER_5, 다이아몬드);
    public static final Card DIAMOND_9 = CARDS.get(NUMBER_9, 다이아몬드);
    public static final Card DIAMOND_10 = CARDS.get(NUMBER_10, 다이아몬드);
    public static final Card HEART_1 = CARDS.get(NUMBER_A, 하트);
    public static final Card HEART_2 = CARDS.get(NUMBER_2, 하트);
    public static final Card HEART_3 = CARDS.get(NUMBER_3, 하트);
    public static final Card HEART_4 = CARDS.get(NUMBER_4, 하트);
    public static final Card HEART_5 = CARDS.get(NUMBER_5, 하트);
    public static final Card HEART_6 = CARDS.get(NUMBER_6, 하트);
    public static final Card HEART_7 = CARDS.get(NUMBER_7, 하트);
    public static final Card HEART_8 = CARDS.get(NUMBER_8, 하트);
    public static final Card HEART_9 = CARDS.get(NUMBER_9, 하트);
    public static final Card HEART_10 = CARDS.get(NUMBER_10, 하트);
    public static final Card HEART_11 = CARDS.get(NUMBER_J, 하트);
    public static final Card HEART_12 = CARDS.get(NUMBER_Q, 하트);
    public static final Card HEART_13 = CARDS.get(NUMBER_K, 하트);
    
    public static final Player DEFAULT_PLAYER = new Player("dompoo");
}
