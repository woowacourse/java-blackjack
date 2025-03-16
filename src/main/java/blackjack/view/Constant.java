package blackjack.view;

import blackjack.card.Denomination;
import blackjack.card.Shape;
import java.util.Map;

public class Constant {

    public static final Map<Shape, String> SHAPE_KOREAN = Map.of(
            Shape.SPADE, "스페이드",
            Shape.DIAMOND, "다이아몬드",
            Shape.HEART, "하트",
            Shape.CLOB, "클로버"
    );
    public static final Map<Denomination, String> DENOMINATION_KOREAN = Map.ofEntries(
            Map.entry(Denomination.ACE, "A"),
            Map.entry(Denomination.TWO, "2"),
            Map.entry(Denomination.THREE, "3"),
            Map.entry(Denomination.FOUR, "4"),
            Map.entry(Denomination.FIVE, "5"),
            Map.entry(Denomination.SIX, "6"),
            Map.entry(Denomination.SEVEN, "7"),
            Map.entry(Denomination.EIGHT, "8"),
            Map.entry(Denomination.NINE, "9"),
            Map.entry(Denomination.TEN, "10"),
            Map.entry(Denomination.KING, "K"),
            Map.entry(Denomination.QUEEN, "Q"),
            Map.entry(Denomination.JACK, "J")
    );
}
