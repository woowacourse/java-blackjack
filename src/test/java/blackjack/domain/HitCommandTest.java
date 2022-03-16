package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class HitCommandTest {

    @Test
    void null이_입력일_때_예외발생() {
        assertThatThrownBy(() -> HitCommand.from(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("커맨드에는 null이 들어올 수 없습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"y,YES", "n,NO)"})
    void 원하는_커맨드를_생성(final String input, final HitCommand expected) {
        assertThat(HitCommand.from(input)).isEqualTo(expected);
    }

    @Test
    void 없는_커맨드를_입력할_때_예외발생() {
        assertThatThrownBy(() -> HitCommand.from("yes?"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("없는 커맨드입니다.");
    }
}
