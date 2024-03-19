package domain.participant.name;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NameValidatorTest {
    @ParameterizedTest
    @ValueSource(strings = {"프", "프린프린프린"})
    void 이름의_길이가_범위를_벗어나면_예외가_발생한다(String name) {
        assertThatThrownBy(() -> NameValidator.validateName(name))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이름은 2 ~ 5 글자여야 합니다");
    }

    @Test
    void 이름에_공백이_포함되어_있으면_예외가_발생한다() {
        String name = "프 린";
        assertThatThrownBy(() -> NameValidator.validateName(name))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이름에 공백이 포함되어 있습니다");
    }

    @ParameterizedTest
    @ValueSource(strings = {"프린1", "뽀@로로"})
    void 이름에_특수문자_또는_숫자가_포함되어_있으면_예외가_발생한다(String name) {
        assertThatThrownBy(() -> NameValidator.validateName(name))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이름에 특수문자와 숫자는 허용하지 않습니다.");
    }

    @Test
    void 이름이_없을_경우_예외가_발생한다() {
        List<String> rawNames = Collections.emptyList();

        assertThatThrownBy(() -> NameValidator.validateNames(rawNames))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이름 개수는 1 ~ 6개여야 합니다.");
    }

    @Test
    void 이름_개수가_최댓값을_넘을_경우_예외가_발생한다() {
        List<String> rawNames = List.of("뽀로로", "프린", "포비", "네오", "토미", "영이", "수달");

        assertThatThrownBy(() -> NameValidator.validateNames(rawNames))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이름 개수는 1 ~ 6개여야 합니다.");
    }

    @Test
    void 이름이_중복될_경우_예외가_발생한다() {
        String roro = "뽀로로";
        String prin = "프린";
        List<String> rawNames = List.of(roro, prin, prin);

        assertThatThrownBy(() -> NameValidator.validateNames(rawNames))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복된 이름이 존재합니다.");
    }

    @Test
    void 올바른_이름_리스트일_경우_예외가_발생하지_않는다() {
        List<String> rawNames = List.of("뽀로로", "프린", "포비", "네오", "토미", "영이");

        assertThatCode(() -> NameValidator.validateNames(rawNames))
                .doesNotThrowAnyException();
    }
}
