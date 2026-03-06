package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    void 카드_생성_테스트() {
        // given
        CardValue value = CardValue.ACE;
        CardShape cardShape = CardShape.DIAMOND;

        // when & then
        assertThatCode(() -> new Card(value, cardShape))
                .doesNotThrowAnyException();
    }

}
