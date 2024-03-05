package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HoldingCardsTest {

    @Test
    void add() {

    }

    @Test
    @DisplayName("포함된 카드의 포인트 합계가 올바른지 검증")
    void calculateTotalPoint() {
        HoldingCards holdingCards = HoldingCards.of(
                new Card(CardName.ACE, CardType.HEART),
                new Card(CardName.SIX, CardType.HEART),
                new Card(CardName.JACK, CardType.HEART),
                new Card(CardName.QUEEN, CardType.HEART),
                new Card(CardName.KING, CardType.HEART)
        );

        SummationCardPoint actual = holdingCards.calculateTotalPoint();
        SummationCardPoint expected = new SummationCardPoint(37);

        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
