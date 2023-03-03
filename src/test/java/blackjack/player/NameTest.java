package blackjack.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import player.Name;

class NameTest {
    @Test
    @DisplayName("Name은 이름 문자열을 받아 생성된다.")
    void create() {
        assertThatCode(() -> new Name("폴로"))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest(name = "이름이 1자 이상 5자 이하가 아닌 경우 예외가 발생한다. 입력값 = {0}")
    @ValueSource(strings = {"폴로랑로지다", ""})
    void createNameFailTest(String value) {
        assertThatThrownBy(() -> new Name(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 1글자 이상 5글자 이하만 가능합니다.");
    }

    @ParameterizedTest(name = "생성자에 넣은 값과 이름의 밸류가 동일하다")
    @ValueSource(strings = {"폴로", "로지"})
    void nameEqualTest(String value) {
        Name name = new Name(value);

        assertThat(name.getValue()).isEqualTo(value);
    }

    @ParameterizedTest(name = "입력받은 밸류에서 공백을 제거 후 생성한다. 입력값 = {0}, 기대값 = {1}")
    @CsvSource({"'   폴   로',폴로", "'로  지  ',로지"})
    void removeBlankTest(String value, String expect) {
        Name name = new Name(value);

        assertThat(name.getValue()).isEqualTo(expect);
    }
}
