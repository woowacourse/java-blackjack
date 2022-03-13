package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    @Test
    @DisplayName("List에 들어있는 Card의 총합을 계산하여 확인한다.")
    void CalculateSumTest() {
        Cards cards = new Cards(List.of(Card.of(Symbol.SPADE, Denomination.EIGHT),
            Card.of(Symbol.SPADE, Denomination.NINE), Card.of(Symbol.SPADE, Denomination.QUEEN)));

        assertThat(cards.calculateSum()).isEqualTo(27);
    }

    @Test
    @DisplayName("List에 Ace가 들어있을 때, 가장 유리한 값으로 계산한다. ACE만 있는 경우")
    void calculateResultTest() {
        Cards cards = new Cards(List.of(Card.of(Symbol.SPADE, Denomination.ACE),
            Card.of(Symbol.CLOVER, Denomination.ACE), Card.of(Symbol.DIAMOND, Denomination.ACE)));

        assertThat(cards.calculateScore()).isEqualTo(13);
    }

    @Test
    @DisplayName("List에 Ace가 들어있을 때, 가장 유리한 값으로 계산한다. ACE 이외의 다른 점수가 포함된 경우")
    void calculateResultTest2() {
        Cards cards = new Cards(List.of(Card.of(Symbol.SPADE, Denomination.FIVE),
            Card.of(Symbol.CLOVER, Denomination.ACE), Card.of(Symbol.DIAMOND, Denomination.ACE),
            Card.of(Symbol.HEART, Denomination.ACE)));

        assertThat(cards.calculateScore()).isEqualTo(18);
    }
}