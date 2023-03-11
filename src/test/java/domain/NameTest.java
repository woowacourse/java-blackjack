package domain;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.message.ExceptionMessage;
import domain.participants.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class NameTest {

    @DisplayName("요구사항에 맞게 이름의 조건을 충족하면 정상적으로 객체를 생성한다.")
    @Test
    void create_success() {
        // when && then
        assertThatNoException()
                .isThrownBy(() -> new Name("pobi"));
    }

    @DisplayName("이름이 Null이거나 공백이면 예외를 반환한다.")
    @ParameterizedTest
    @NullAndEmptySource
    void create_fail_by_blank_value(String input) {
        // when & then
        assertThatThrownBy(() -> new Name(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ExceptionMessage.NAME_IS_EMPTY.getMessage());
    }

    @DisplayName("이름의 길이는 10자를 초과하면 예외를 반환한다.")
    @Test
    void create_fail_by_wrong_name_length() {
        // given
        String givenName = "1234567890a";

        // when & then
        assertThatThrownBy(() -> new Name(givenName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ExceptionMessage.NAME_LENGTH_OVER.getMessage());
    }
}
