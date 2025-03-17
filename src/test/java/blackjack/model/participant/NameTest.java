package blackjack.model.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.HashSet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("이름 테스트")
class NameTest {

    @DisplayName("null 또는 빈 문자열로 이름을 만드는 경우 예외가 발생한다.")
    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowException_WhenNullOrEmptyName(String name) {
        assertThatCode(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 비어 있을 수 없습니다.");
    }

    @DisplayName("이름은 2자 이상 10자 이하로만 가능하다.")
    @ParameterizedTest
    @ValueSource(strings = {
            "벨로", "도기20", "1234567890", "난 벨로다", "공백 가능"
    })
    void nameValidLengthTest(String name) {
        assertThatCode(() -> new Name(name))
                .doesNotThrowAnyException();
    }

    @DisplayName("이름이 2자 미만 또는 10자 초과인 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {
            "김", "12345678901", "너무나 긴 이름이라서"
    })
    void nameInvalidLengthTest(String name) {
        assertThatCode(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 한글, 숫자로 이루어져야 하며 2자 이상 10자 이하로만 가능합니다. 입력: %s".formatted(name));
    }

    @DisplayName("한글, 숫자, 공백 이외의 문자가 포함된 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {
            "hello", "네오!", "name123@", "벨~로", "Test123"
    })
    void shouldThrowException_WhenContainsInvalidCharacters(String name) {
        assertThatCode(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 한글, 숫자로 이루어져야 하며 2자 이상 10자 이하로만 가능합니다. 입력: %s".formatted(name));
    }

    @DisplayName("같은 이름을 가진 경우 동일하다고 간주한다.")
    @Test
    void equalsTest() {
        // given
        Name name1 = new Name("벨로");
        Name name2 = new Name("벨로");

        // when
        HashSet<Name> names = new HashSet<>();
        names.add(name1);
        names.add(name2);

        // then
        assertThat(names)
                .hasSize(1);
    }

    @DisplayName("이름을 확인할 수 있다.")
    @Test
    void getValueTest() {
        // given
        Name name = new Name("벨로");
        String value = name.value();

        // when, then
        assertThat(value)
                .isEqualTo("벨로");
    }
}
