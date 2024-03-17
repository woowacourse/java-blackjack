package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class NameTest {

    @DisplayName("한글 또는 영어가 아닌 이름을 입력하면 에러가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"123", "이상1", "제이미짱!"})
    public void createFailByInvalidCharacter(String name) {
        assertThatCode(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("이름은 한글 또는 영어만 가능합니다. 입력값: %s", name));
    }

    @DisplayName("이름이 비어있거나 5자 초과면 에러가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "123456"})
    public void createFailByNameLength(String name) {
        assertThatCode(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format(String.format("이름은 %d글자 이하만 가능합니다.", 5)));
    }
}
