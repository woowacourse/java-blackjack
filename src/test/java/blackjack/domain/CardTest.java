package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    @DisplayName("카드_생성_테스트")
    void 카드_생성_테스트() {
        // given
        CardValue value = CardValue.A;
        Shape shape = Shape.DIAMOND;

        // when & then
        assertThatCode(() -> new Card(value, shape))
                .doesNotThrowAnyException();
    }

}
