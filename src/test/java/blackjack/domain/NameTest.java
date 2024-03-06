package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class NameTest {
    @DisplayName("")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   "})
    @NullSource
    void validateBlank(String input) {
        assertThatThrownBy(() -> new Name(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("참여자 이름에 공백을 입력할 수 없습니다.");
    }
}