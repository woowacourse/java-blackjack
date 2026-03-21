package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class NameTest {
    @ParameterizedTest
    @DisplayName("한글 또는 영문으로 이름을 생성할 수 있다.")
    @ValueSource(strings = {"pobi", "terry", "류시", "검프"})
    void shouldReturnNameForKoreanOrEnglishName(String name) {
        // when
        Name result = new Name(name);

        // then
        assertThat(result.name()).isEqualTo(name);
    }

    @ParameterizedTest
    @DisplayName("이름이 공백이거나 공백이나 특수문자가 포함된 경우 오류가 발생한다.")
    @NullAndEmptySource
    @ValueSource(strings = {"", " ", "공 백문자포함", "특수문자포함!", "\n"})
    void shouldThrowExceptionForInvalidName(String name) {
        // when & then
        assertThatThrownBy(
                () -> new Name(name)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
