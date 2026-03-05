package blackjack.model;

import blackjack.exception.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameTest {

    @Test
    @DisplayName("이름 길이는 2 이상 5이하여야 합니다")
    void playNameLengthTest() {
        // given
        String nameEx = "123";

        // when
        Name name = new Name(nameEx);

        // then
        assertThat(name.getName()).isEqualTo(nameEx);
    }

    @ParameterizedTest()
    @ValueSource(strings = {"123456", "1"})
    @DisplayName("이름 길이가 2 이상 5이하가 아닌 경우 예외가 발생한다")
    void playerNameLengthErrorTest(String name) {
        // given & when & then
        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorCode.OUT_OF_NAME_LENGTH);
    }
}