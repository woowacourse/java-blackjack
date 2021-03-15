package blackjack.domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NameTest {

    @Test
    @DisplayName("이름 생성된다.")
    void testCreateName() {
        String nameString = "pobi";
        Name name = new Name(nameString);
        assertThat(name).isEqualTo(new Name(nameString));
    }

    @ParameterizedTest(name = "이름 입력값이 null 이라면 예외가 발생한다.")
    @NullSource
    void testValidateNull(String name) {
        assertThatThrownBy(() -> new Name(name)).isInstanceOf(NullPointerException.class)
                .hasMessage("이름은 null 일 수 없습니다.");
    }

    @ParameterizedTest(name = "이름 입력값에 공백만 있거나 빈값이라면 예외가 발생한다.")
    @ValueSource(strings = {"", " ", "  "})
    void testCreateName(String name) {
        assertThatThrownBy(() -> new Name(name)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 빈값 일 수 없습니다.");
    }

    @Test
    @DisplayName("딜러 라는 이름은 플레이어의 이름으로 사용할 수 없습니다.")
    void validateNameTest() {
        assertThatThrownBy(() -> new Name("딜러")).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름으로 \"딜러\" 는 사용할 수 없습니다.");
    }

    @Test
    @DisplayName("이름 반환된다.")
    void getValueTest() {
        String nameString = "pobi";

        Name name = new Name(nameString);

        assertThat(name.getValue()).isEqualTo("pobi");
    }
}
