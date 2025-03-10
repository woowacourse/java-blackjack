package blackjack.model.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.HashSet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("이름 테스트")
class NameTest {

    @DisplayName("null 또는 빈 문자열로 이름을 만드는 경우 예외가 발생한다.")
    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowException_WhenNullOrEmptyName(String name) {
        // when, then
        assertThatCode(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 비어 있을 수 없으며, 공백만 포함할 수 없습니다.");
    }

    @DisplayName("공백으로만 구성된 문자열로 이름을 만드는 경우 예외가 발생한다.")
    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowException_WhenBlankName(String name) {
        // when, then
        assertThatCode(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 비어 있을 수 없으며, 공백만 포함할 수 없습니다.");
    }

    @DisplayName("이름은 2자 이상 5자 이하로만 가능하다.")
    @ParameterizedTest
    @ValueSource(strings = {"벨로", "슬링키", "카페라떼", "아메리카노"})
    void nameLengthTest(String name) {
        // when, then
        assertThatCode(() -> new Name(name))
                .doesNotThrowAnyException();
    }

    @DisplayName("이름이 2자 미만 5자 초과인 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"나", "우아한형제들"})
    void nameInvalidLengthTest(String name) {
        // when, then
        assertThatCode(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 2자 이상 5자 이하로만 가능합니다. 입력: %s".formatted(name));
    }

    @DisplayName("같은 이름을 가진 경우 동일하다고 간주한다.")
    @Test
    void equalsTest() {
        // given
        Name bello1 = new Name("벨로");
        Name bello2 = new Name("벨로");

        // when
        HashSet<Name> names = new HashSet<>();
        names.add(bello1);
        names.add(bello2);

        // then
        assertThat(names)
                .hasSize(1);
    }

    @DisplayName("한글 이름이 아닌 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"me", "neo", "pobi", "bello"})
    void shouldThrowException_WhenNotKoreanName(String englishName) {
        // when, then
        assertThatCode(() -> new Name(englishName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 한글로만 입력 가능합니다. 입력: %s".formatted(englishName));
    }

    @DisplayName("이름을 확인할 수 있다.")
    @Test
    void getValueTest() {
        // given
        Name bello = new Name("벨로");
        String belloNameValue = bello.value();

        // when, then
        assertThat(belloNameValue)
                .isEqualTo("벨로");
    }
}
