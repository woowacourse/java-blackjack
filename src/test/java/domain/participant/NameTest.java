package domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NameTest {
    @ParameterizedTest
    @ValueSource(strings = {"프", "프린프린프린"})
    void 이름의_길이가_범위를_벗어나면_예외가_발생한다(String name) {
        assertThatThrownBy(() -> new Name(name))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이름은 2 ~ 5 글자여야 합니다");
    }

    @Test
    void 이름에_공백이_포함되어_있으면_예외가_발생한다() {
        String name = "프 린";
        assertThatThrownBy(() -> new Name(name))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이름에 공백이 포함되어 있습니다");
    }

    @ParameterizedTest
    @ValueSource(strings = {"프린1", "뽀@로로"})
    void 이름에_특수문자_또는_숫자가_포함되어_있으면_예외가_발생한다(String name) {
        assertThatThrownBy(() -> new Name(name))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이름에 특수문자와 숫자는 허용하지 않습니다.");
    }
}
