package model;

import static model.CardNumber.FIVE;
import static model.CardNumber.JACK;
import static model.CardNumber.ONE;
import static model.CardShape.CLOVER;
import static model.CardShape.DIAMOND;
import static model.CardShape.HEART;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    @DisplayName("카드 숫자 합 계산한다")
    @Test
    void testCalculateTotalCardNumbers() {
        Cards cards = new Cards(
            List.of(new Card(JACK, DIAMOND), new Card(FIVE, CLOVER), new Card(ONE, HEART))
        );
        assertThat(cards.calculateTotalNumbers()).isEqualTo(16);
    }
}
