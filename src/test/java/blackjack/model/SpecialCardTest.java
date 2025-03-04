package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class SpecialCardTest {

    @Test
    void 스페셜_카드는_문양을_가지고_있다() {
        // given
        SpecialCard card = new SpecialCard('J', CardShape.CLOVER);

        // when
        CardShape cardShape = card.getShape();

        // then
        assertThat(cardShape).isEqualTo(CardShape.CLOVER);
    }
}
