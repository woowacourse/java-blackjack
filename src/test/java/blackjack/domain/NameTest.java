package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class NameTest {

    @DisplayName("문자열을 받아서 Name을 생성한다.")
    @Test
    void createNameWhenGivenString() {
        // given
        String input = "test";

        // when & then
        assertDoesNotThrow(() -> new Name(input));
    }

    @ParameterizedTest(name = "이름의 길이는 1글자 이상 5글자 이하가 아니면 예외를 던진다.")
    @ValueSource(strings = {"", "abcdef"})
    void validateLengthOfName(String input) {
        // when & then
        assertThatThrownBy(() -> new Name(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이름의 길이는 1이상 5이하만 가능합니다.");
    }
}
