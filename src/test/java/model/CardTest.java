package model;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @DisplayName("숫자와 모양을 가진 카드를 생성한다")
    @Test
    void shouldCardHaveNumberAndShape() {
        assertThatCode(() -> new Card(CardNumber.ONE, CardShape.CLOVER))
            .doesNotThrowAnyException();
    }
}
