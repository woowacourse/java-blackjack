package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class NormalCardTest {

    @Test
    void 일반_카드는_문양을_가지고_있다() {
        // given
        NormalCard card = new NormalCard(2, CardShape.CLOVER);

        // when
        CardShape cardShape = card.getShape();

        // then
        assertThat(cardShape).isEqualTo(CardShape.CLOVER);
    }
}
