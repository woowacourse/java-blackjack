package fixture;

import domain.Card;
import domain.CardInfo;
import domain.Cards;
import domain.Shape;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 우가
 * @version 1.0.0
 * @Created by 우가 on 2023/03/04
 */
public class CardFixture {
    public static final Card 하트에이스 = new Card(Shape.HEART, CardInfo.A);
    public static final Card 하트2 = new Card(Shape.HEART, CardInfo.TWO);
    public static final Card 하트3 = new Card(Shape.HEART, CardInfo.THREE);
    public static final Card 하트4 = new Card(Shape.HEART, CardInfo.FOUR);
    public static final Card 하트5 = new Card(Shape.HEART, CardInfo.FIVE);
    public static final Card 하트6 = new Card(Shape.HEART, CardInfo.SIX);
    public static final Card 하트7 = new Card(Shape.HEART, CardInfo.SEVEN);
    public static final Card 하트8 = new Card(Shape.HEART, CardInfo.EIGHT);
    public static final Card 하트9 = new Card(Shape.HEART, CardInfo.NINE);
    public static final Card 하트10 = new Card(Shape.HEART, CardInfo.TEN);

    public static final Cards 하트에이스하트2 = new Cards(new ArrayList<>(List.of(하트에이스, 하트2)));
    public static final Cards 하트9하트3 = new Cards(new ArrayList<>(List.of(하트9, 하트3)));
    public static final Cards 하트10하트4 = new Cards(new ArrayList<>(List.of(하트10, 하트4)));
}
