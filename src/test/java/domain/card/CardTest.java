package domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    void 숫자와_문양으로_카드를_생성한다() {
        // given
        final TrumpNumber number = TrumpNumber.ACE;
        final TrumpShape shape = TrumpShape.CLUB;

        // when & then
        Assertions.assertThatCode(() -> Card.of(number, shape))
                .doesNotThrowAnyException();
    }
}
