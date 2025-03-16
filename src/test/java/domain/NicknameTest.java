package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NicknameTest {
    @DisplayName("닉네임은 빈 문자열로 설정할 수 없다.")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    void couldNotEmptyValue(String value) {
        // given

        // when & then
        assertThatThrownBy(() -> {
            new Nickname(value);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
