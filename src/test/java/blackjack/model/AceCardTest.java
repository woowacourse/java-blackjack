package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class AceCardTest {

    @Test
    void 에이스_카드는_문양을_가지고_있다() {
        // given
        AceCard card = new AceCard(CardShape.CLOVER);

        // when
        CardShape cardShape = card.getShape();

        // then
        assertThat(cardShape).isEqualTo(CardShape.CLOVER);
    }
}
