package domain.fixture;

import domain.money.BettingMoney;
import domain.card.Card;
import domain.card.CardInfo;
import domain.card.Cards;
import domain.participant.Name;
import domain.participant.Player;
import domain.card.Shape;
import java.util.List;

/**
 * @author 우가
 * @version 1.0.0
 * @Created by 우가 on 2023/03/08
 */
public class PlayerFixture {
    public static final Player 우가 = new Player(new Name("우가"),
            new Cards(List.of(new Card(Shape.HEART, CardInfo.A), new Card(Shape.HEART, CardInfo.TWO))),
            new BettingMoney(1000));

    public static final Player 빙봉 = new Player(new Name("빙봉"),
            new Cards(List.of(new Card(Shape.HEART, CardInfo.A), new Card(Shape.HEART, CardInfo.TEN))),
            new BettingMoney(2000));

    public static final Player 하마드 = new Player(new Name("하마드"),
            new Cards(List.of(new Card(Shape.HEART, CardInfo.NINE), new Card(Shape.HEART, CardInfo.THREE))),
            new BettingMoney(10000));
}
