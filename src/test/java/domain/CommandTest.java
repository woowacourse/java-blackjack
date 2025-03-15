package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CommandTest {

    @DisplayName("입력에 대한 커맨드가 반환된다.")
    @ParameterizedTest
    @CsvSource(value = {
            "Y, YES", "N, NO"
    })
    void 입력에_대한_커맨드가_반환된다(final String input, final Command command) {

        //when & then
        assertThat(Command.find(input)).isEqualTo(command);
    }

    @DisplayName("Y가 입력되면 true 아니면 false를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "Y, true", "N, false"
    })
    void Y가_입력되면_true_아니면_false를_반환한다(final String input, final boolean expected) {

        //when & then
        assertThat(Command.isYes(input)).isEqualTo(expected);
    }

    @DisplayName("N이 입력되면 true 아니면 false를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "N, true", "Y, false"
    })
    void N이_입력되면_true_아니면_false를_반환한다(final String input, final boolean expected) {

        //when & then
        assertThat(Command.isNo(input)).isEqualTo(expected);
    }
}

