package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NicknameTest {

    @DisplayName("네이밍 실패 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"23", "aaaaaa", "ad23", "뭘봐", "d"})
    void 네이밍_실패_테스트(String input) {

        // given
        // when & then
        assertThatThrownBy(() -> new Nickname(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
