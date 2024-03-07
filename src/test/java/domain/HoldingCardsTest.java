package domain;

import static domain.CardName.ACE;
import static domain.CardName.JACK;
import static domain.CardName.KING;
import static domain.CardName.QUEEN;
import static domain.CardName.SIX;
import static domain.CardType.HEART;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HoldingCardsTest {

    @Test
    @DisplayName("포함된 카드의 포인트 합계가 올바른지 검증")
    void calculateTotalPoint() {
        HoldingCards holdingCards = HoldingCards.of(
                new Card(ACE, HEART), new Card(SIX, HEART), new Card(JACK, HEART),
                new Card(QUEEN, HEART), new Card(KING, HEART)
        );

        SummationCardPoint actual = holdingCards.calculateTotalPoint();
        SummationCardPoint expected = new SummationCardPoint(37);

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Ace가 포함되었는지 여부 검증")
    void hasAce() {
        HoldingCards holdingCards = HoldingCards.of(new Card(ACE, HEART));
        Assertions.assertThat(holdingCards.hasAce()).isTrue();
    }
}
