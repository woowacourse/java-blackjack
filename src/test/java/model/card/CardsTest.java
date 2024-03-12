package model.card;

import static model.card.CardNumber.FIVE;
import static model.card.CardNumber.JACK;
import static model.card.CardNumber.ACE;
import static model.card.CardShape.CLOVER;
import static model.card.CardShape.DIAMOND;
import static model.card.CardShape.HEART;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    @DisplayName("카드 숫자 합 계산한다")
    @Test
    void testCalculateTotalCardNumbers() {
        Cards cards = new Cards(
            List.of(new Card(JACK, DIAMOND), new Card(FIVE, CLOVER), new Card(ACE, HEART))
        );
        assertThat(cards.calculateTotalNumbers()).isEqualTo(16);
    }
}
