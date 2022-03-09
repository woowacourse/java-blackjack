package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.ParameterizedTest.ARGUMENTS_PLACEHOLDER;
import static org.junit.jupiter.params.ParameterizedTest.DISPLAY_NAME_PLACEHOLDER;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class NameTest {

    @DisplayName("플레이어 이름 생성자 테스트")
    @Test
    void create() {
        Name name = new Name("Pobi");
        assertThat(name).isNotNull();
    }

    @DisplayName("플레이어 이름 빈칸일시 예외 반환 테스트")
    @ParameterizedTest(name = DISPLAY_NAME_PLACEHOLDER + " [" + ARGUMENTS_PLACEHOLDER + "]")
    @ValueSource(strings = {"", "   "})
    void validateName(String value) {
        assertThatThrownBy(() -> new Name(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 빈 칸일 수 없습니다.");
    }
}
