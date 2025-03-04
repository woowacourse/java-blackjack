package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class CardShapeTest {

    @Test
    void 카드_모양을_확인한다() {
        // given
        CardShape cardShape1 = CardShape.SPADE;
        CardShape cardShape2 = CardShape.DIAMOND;
        CardShape cardShape3 = CardShape.HEART;
        CardShape cardShape4 = CardShape.CLOVER;

        // then
        assertThat(cardShape1).isEqualTo(CardShape.SPADE);
        assertThat(cardShape2).isEqualTo(CardShape.DIAMOND);
        assertThat(cardShape3).isEqualTo(CardShape.HEART);
        assertThat(cardShape4).isEqualTo(CardShape.CLOVER);
    }
}
