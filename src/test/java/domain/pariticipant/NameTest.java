package domain.pariticipant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static exception.ErrorMessage.PLAYER_NAME_LENGTH_ERROR;
import static org.assertj.core.api.Assertions.*;


class NameTest {

    @ParameterizedTest
    @ValueSource(strings = {"ab", "abcdefghij"})
    @DisplayName("이름은 2글자 이상 10글자 이하여야 한다.")
    public void name_success(String n) throws Exception {
        // given // when // then
        assertThatCode(() -> new Name(n))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "abcdefghijk"})
    @DisplayName("이름은 2글자 미만이거나 10글자 초과면 예외가 발생한다.")
    public void name_fail(String n) throws Exception {
        // given // when // then
        assertThatThrownBy(() -> new Name(n))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PLAYER_NAME_LENGTH_ERROR.getMessage());
    }

}
