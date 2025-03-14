package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NicknameTest {
    @DisplayName("닉네임은 빈 문자열로 설정할 수 없다.")
    @Test
    void couldNotEmptyValue() {
        // given
        String value = "";

        // when & then
        assertThatThrownBy(() -> {
            new Nickname(value);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
