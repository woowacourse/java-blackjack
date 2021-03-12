package blackjack.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;


public class NameTest {

    @ParameterizedTest
    @ValueSource(strings = {"croffle", "corgi", "pobi"})
    @DisplayName("이름 생성 테스트")
    void testName(String name) {
        assertThat(new Name(name)).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("공백 입력시 예외 테스트")
    void testInvalidName(String name) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Name(name))
                .withMessage("이름은 공백일 수 없습니다.");
    }
}
