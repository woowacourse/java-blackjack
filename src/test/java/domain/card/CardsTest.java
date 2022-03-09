package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    @Test
    @DisplayName("List에 들어있는 Card의 총합을 계산하여 확인한다.")
    void CalculateSumTest() {
        Cards cards = new Cards(List.of(new Card(Symbol.SPADE, Denomination.EIGHT),
            new Card(Symbol.SPADE, Denomination.NINE), new Card(Symbol.SPADE, Denomination.QUEEN)));

        assertThat(cards.calculateSum()).isEqualTo(27);
    }
}