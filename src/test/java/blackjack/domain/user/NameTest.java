package blackjack.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class NameTest {

    @DisplayName("이름 정상 입력 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"pobi", "brown", "jason", "woni"})
    void name_gerate_test(String name) {
        assertDoesNotThrow(() -> new Name(name));
    }

    @DisplayName("이름 비정상 입력 테스트(한글, 영문 이외의 문자)")
    @ParameterizedTest
    @ValueSource(strings = {"pob i", "#brown", "ja!son", "won22i"})
    void name_gerate_test2(String name) {
        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("이름을 잘못 입력하였습니다. (입력값 : %s)", name));
    }
}