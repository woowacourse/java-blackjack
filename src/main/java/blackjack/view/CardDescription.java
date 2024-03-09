package blackjack.view;

import static blackjack.domain.card.CardNumber.ACE;
import static blackjack.domain.card.CardNumber.EIGHT;
import static blackjack.domain.card.CardNumber.FIVE;
import static blackjack.domain.card.CardNumber.FOUR;
import static blackjack.domain.card.CardNumber.JACK;
import static blackjack.domain.card.CardNumber.KING;
import static blackjack.domain.card.CardNumber.NINE;
import static blackjack.domain.card.CardNumber.QUEEN;
import static blackjack.domain.card.CardNumber.SEVEN;
import static blackjack.domain.card.CardNumber.SIX;
import static blackjack.domain.card.CardNumber.TEN;
import static blackjack.domain.card.CardNumber.THREE;
import static blackjack.domain.card.CardNumber.TWO;
import static blackjack.domain.card.CardShape.CLUB;
import static blackjack.domain.card.CardShape.DIAMOND;
import static blackjack.domain.card.CardShape.HEART;
import static blackjack.domain.card.CardShape.SPADE;

import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import java.util.Map;

public class CardDescription {

    private CardDescription() {
    }

    public static final Map<CardShape, String> SHAPE_NAME = Map.of(
            HEART, "하트",
            SPADE, "스페이드",
            DIAMOND, "다이아몬드",
            CLUB, "클로버"
    );
    public static final Map<CardNumber, String> NUMBER_NAME = Map.ofEntries(
            Map.entry(ACE, "A"), Map.entry(TWO, "2"),
            Map.entry(THREE, "3"), Map.entry(FOUR, "4"),
            Map.entry(FIVE, "5"), Map.entry(SIX, "6"),
            Map.entry(SEVEN, "7"), Map.entry(EIGHT, "8"),
            Map.entry(NINE, "9"), Map.entry(TEN, "10"),
            Map.entry(JACK, "J"), Map.entry(QUEEN, "Q"),
            Map.entry(KING, "K")
    );
}
