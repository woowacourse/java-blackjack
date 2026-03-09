package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    @DisplayName("카드가 올바르게 생성하는지에 대한 테스트")
    void createCard() {
        // given
        CardValue value = CardValue.ACE;
        CardShape cardShape = CardShape.DIAMOND;

        // when
        Card card = new Card(value, cardShape);

        // then
        assertThat("A다이아몬드").isEqualTo(card.getName());
    }

}
