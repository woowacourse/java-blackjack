import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import common.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CardSuitTest {

    @DisplayName("카드 값이 다르다면 에러를 반환한다.")
    @Test
    void test2() {
        // given

        // when & then
        assertThatThrownBy(() -> CardSuit.ACE.validate(8))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_CARD_VALUE.getMessage());
    }

    @DisplayName("ACE는 1과 11을 가져야 한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 11})
    void test1(int value) {
        // given
        CardSuit ace = CardSuit.ACE;

        // when && then
        assertThatCode(() -> ace.validate(value))
                .doesNotThrowAnyException();
    }
}
