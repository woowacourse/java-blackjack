package domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Name 은")
class NameTest {

    @Test
    void 문자열을_받아_생성된다() {
        // given
        final String input = "문자열";
        final Name name = Name.of(input);

        // when
        final String value = name.value();

        // then
        assertThat(value).isEqualTo(input);
    }

    @ParameterizedTest(name = "빈 문자열인 경우 예외가 발생한다")
    @NullAndEmptySource
    void 빈_문자열인_경우_예외가_발생한다(final String input) {
        // when & then
        assertThatThrownBy(() -> Name.of(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
