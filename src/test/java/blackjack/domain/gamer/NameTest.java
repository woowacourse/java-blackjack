package blackjack.domain.gamer;

import blackjack.domain.money.Chip;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("이륾")
public class NameTest {
    @ParameterizedTest
    @ValueSource(strings = {"", "   "})
    @DisplayName("이름이 공백일 경우 예외가 발생한다.")
    void validateEmptyTest(String name) {
        // given & when & then
        assertThatCode(() -> new Player(new Name(name), new Chip(0L)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("공백이 아닌 이름을 입력해 주세요.");
    }
}
