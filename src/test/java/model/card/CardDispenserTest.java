package model.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDispenserTest {

    CardDispenser cardDispenser = CardDispenser.getCardDispenser();

    @DisplayName("랜덤으로 숫자와 모양을 뽑아 카드 1장을 지급한다")
    @Test
    void testDispenseCard() {
        Card card = cardDispenser.dispenseCard();
        assertThat(card.getNumber()).isNotNull();
        assertThat(card.getShape()).isNotNull();
    }
}
