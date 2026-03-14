package domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.BlackjackException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NameTest {

    @ParameterizedTest
    @ValueSource(strings = {"ab", "player", "abcdefghi"})
    @DisplayName("정상 이름은 생성된다.")
    void 정상_이름_생성(String name) {
        // given
        Name result = new Name(name);

        // when & then
        assertThat(result.name()).isEqualTo(name);
    }

    @ParameterizedTest
    @ValueSource(strings = {"12345", "1", "999999"})
    @DisplayName("이름이 숫자면 예외가 발생한다.")
    void 이름이_숫자일_시(String name) {
        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(BlackjackException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "abcdefghijkl"})
    @DisplayName("이름은 두글자 이상 열글자 미만이어야 한다.")
    void 이름_길이_검증(String name) {
        // when & then
        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(BlackjackException.class);

    }
}