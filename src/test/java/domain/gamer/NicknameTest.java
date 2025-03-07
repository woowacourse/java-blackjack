package domain.gamer;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NicknameTest {

    @DisplayName("네이밍 실패 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"23", "aaaaaa", "ad23", "d"})
    void 네이밍_실패_테스트(final String input) {

        // given
        // when & then
        assertThatThrownBy(() -> new Nickname(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("네이밍 성공 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"와우", "도라me", "로스트아크"})
    void 네이밍_성공_테스트(final String input) {

        // given
        // when & then
        Assertions.assertThatCode(() -> new Nickname(input))
                .doesNotThrowAnyException();
    }


}
